package kz.botcs.point;

import kz.botcs.chatbot.InMessage;

import java.lang.annotation.Annotation;

public interface PointHandler<A extends Annotation> {
    Integer getOrder();

    Class<A> getType();

    String getKeyword(A annotation);

    Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage);
}
