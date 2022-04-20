package kz.botcs.point;

import kz.botcs.*;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.OutMessage;
import kz.botcs.chatbot.TextOutMessage;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


@Component
public class PointScanner {

    private final PointContainer pointContainer;
    private final PointHandlerContainer pointHandlerContainer;

    public PointScanner(
            PointContainer pointContainer,
            PointHandlerContainer pointHandlerContainer) {
        this.pointContainer = pointContainer;
        this.pointHandlerContainer = pointHandlerContainer;
    }

    public void scan(Collection<Object> controllers) {
        for (Object controller : controllers) {
            Class<?> controllerType = controller.getClass();
            PointController controllerAnnotation = controllerType.getAnnotation(PointController.class);
            String chatbotId = controllerAnnotation.value();
            for (Method method : controllerType.getMethods()) {
                Point point = args -> {
                    int parameterCount = method.getParameterCount();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Object[] parameters = new Object[parameterCount];
                    for (int i = 0; i < parameterCount; i++) {
                        parameters[i] = getParameterForType(parameterTypes[i], args);
                    }
                    try {
                        Object result = method.invoke(controller, parameters);
                        return toOutResponse(result);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        return errorOutResponse();
                    }
                };
                for (PointHandler pointHandler : pointHandlerContainer.getPointHandlers()) {
                    Annotation annotation = method.getAnnotation(pointHandler.getType());
                    if (annotation != null) {
                        String keyword = pointHandler.getKeyword(annotation);
                        pointContainer.put(chatbotId, keyword, pointHandler.getType(), point);
                    }
                }
            }
        }
    }

    private OutResponse errorOutResponse() {
        return null;
    }

    private Object getParameterForType(Class<?> parameterType, PointArgs args) {
        if (parameterType.equals(ChatBotUser.class)) {
            return args.getInMessage().getFrom();
        }
        if (parameterType.equals(InMessage.class)) {
            return args.getInMessage();
        }
        if (parameterType.equals(String.class)) {
            return args.getText();
        }
        throw new IllegalArgumentException("unknown type");
    }

    private OutResponse toOutResponse(Object result) {
        if (result instanceof OutResponse) {
            return (OutResponse) result;
        } else if (result instanceof List) {
            List<OutMessage> outMessages = new ArrayList<>();
            List<?> list = (List<?>) result;
            for (Object element : list) {
                if (!(element instanceof OutMessage)) {
                    throw new IllegalStateException("return type is not correct");
                }
                outMessages.add((OutMessage) element);
            }
            return new OutResponse(null, outMessages);
        } else if (result instanceof OutMessage) {
            return new OutResponse(null, Collections.singletonList((OutMessage) result));
        } else if (result instanceof String) {
            return new OutResponse(null, Collections.singletonList(new TextOutMessage(null, (String) result)));
        }
        throw new IllegalStateException("return type is not correct");
    }
}
