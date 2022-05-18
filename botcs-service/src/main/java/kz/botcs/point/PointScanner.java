package kz.botcs.point;

import kz.botcs.ChatBotUser;
import kz.botcs.OutResponse;
import kz.botcs.builder.ResponseBuilder;
import kz.botcs.chatbot.CallbackInMessage;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.para.CallbackMessageId;
import kz.botcs.point.para.PhotoId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;


@Component
public class PointScanner {

    private final PointContainer pointContainer;
    private final PointHandlerContainer pointHandlerContainer;
    private final ApplicationContext context;

    public PointScanner(
            PointContainer pointContainer,
            PointHandlerContainer pointHandlerContainer,
            ConfigurableApplicationContext context) {
        this.pointContainer = pointContainer;
        this.pointHandlerContainer = pointHandlerContainer;
        this.context = context;
    }

    public void scan(Collection<Class<?>> controllerTypes) {
        for (Class<?> controllerType : controllerTypes) {
            PointController controllerAnnotation = controllerType.getAnnotation(PointController.class);
            String chatbotId = controllerAnnotation.chatbotId();
            for (Method method : controllerType.getMethods()) {
                Point point = args -> {
                    int parameterCount = method.getParameterCount();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Annotation[][] annotations = method.getParameterAnnotations();
                    Object[] parameters = new Object[parameterCount];
                    for (int i = 0; i < parameterCount; i++) {
                        parameters[i] = getParameterForType(
                                parameterTypes[i],
                                Arrays.stream(annotations[i]).findFirst().orElse(null),
                                args);
                    }
                    try {
                        Object controller = context.getBean(controllerType);
                        return (OutResponse) method.invoke(controller, parameters);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        return errorOutResponse(e);
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

    private OutResponse errorOutResponse(Exception e) {
        e.printStackTrace();
        return ResponseBuilder.ofText("something went wrong").build();
    }

    private Object getParameterForType(
            Class<?> parameterType,
            Annotation annotation,
            PointArgs args) {
        if (parameterType.equals(ChatBotUser.class)) {
            return args.getInMessage().getFrom();
        }
        if (parameterType.equals(InMessage.class)) {
            return args.getInMessage();
        }
        if (parameterType.equals(String.class)) {
            if (annotation instanceof CallbackMessageId) {
                if (args.getInMessage() instanceof CallbackInMessage) {
                    CallbackInMessage callbackInMessage = (CallbackInMessage) args.getInMessage();
                    return callbackInMessage.getCallbackMessageId();
                }
                return null;
            }
            if (annotation instanceof PhotoId) {
                if (args.getInMessage() instanceof TextInMessage) {
                    TextInMessage textInMessage = (TextInMessage) args.getInMessage();
                    return textInMessage.getPhotoId();
                }
                return null;
            }
            return args.getText();
        }
        throw new IllegalArgumentException("unknown type: " + parameterType.getName());
    }
}
