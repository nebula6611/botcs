package kz.botcs.point;

import kz.botcs.*;
import kz.botcs.inmessage.InMessage;
import org.springframework.stereotype.Component;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


@Component
public class PointScanner {

    private final PointContainer pointContainer;

    public PointScanner(PointContainer pointContainer) {
        this.pointContainer = pointContainer;
    }

    public void scan(Collection<Object> controllers) {
        for (Object controller : controllers) {
            Class<?> controllerType = controller.getClass();
            ChatbotController controllerAnnotation = controllerType.getAnnotation(ChatbotController.class);
            String clientId = controllerAnnotation.clientId();
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

                CommandMapping commandMappingAnnotation = method.getAnnotation(CommandMapping.class);
                if (commandMappingAnnotation != null) {
                    pointContainer.put(clientId, commandMappingAnnotation.value(), PointType.COMMAND, point);
                }
                StageMapping stageMappingAnnotation = method.getAnnotation(StageMapping.class);
                if (stageMappingAnnotation != null) {
                    pointContainer.put(clientId, stageMappingAnnotation.value(), PointType.COMMAND, point);
                }
                CallbackMapping callbackMappingAnnotation = method.getAnnotation(CallbackMapping.class);
                if (callbackMappingAnnotation != null) {
                    pointContainer.put(clientId, callbackMappingAnnotation.value(), PointType.COMMAND, point);
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
        }
        throw new IllegalStateException("return type is not correct");
    }

    private Map<PointType, Class<? extends Annotation>> getAnnotationContainer() {
        return Map.of(PointType.COMMAND, CommandMapping.class,
                PointType.STAGE, StageMapping.class,
                PointType.CALLBACK, CallbackMapping.class);
    }
}
