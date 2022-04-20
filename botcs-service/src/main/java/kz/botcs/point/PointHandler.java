package kz.botcs.point;

import kz.botcs.chatbot.InMessage;

import java.lang.annotation.Annotation;

public interface PointHandler {
    Integer getOrder();

    Class<? extends Annotation> getType();

    Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage);
}
