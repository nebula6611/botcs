package kz.botcs.point;

import kz.botcs.ChatBotUser;
import kz.botcs.OutResponse;
import kz.botcs.builder.ResponseBuilder;
import kz.botcs.chatbot.InMessage;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;


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
                        return (OutResponse) method.invoke(controller, parameters);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        return errorOutResponse(e);
                    }
                };
                for (PointHandler<Annotation> pointHandler : pointHandlerContainer.getPointHandlers()) {
                    Annotation annotation = method.getAnnotation(pointHandler.getType());
                    if (annotation != null) {
                        String keyword = pointHandler.getKeyword(annotation);
                        pointContainer.put(chatbotId, keyword, pointHandler.getType(), point);
                    }
                }
            }
        }
    }

    private OutResponse errorOutResponse(Exception e) {
        e.printStackTrace();
        return ResponseBuilder.ofText("something went wrong").build();
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
}
