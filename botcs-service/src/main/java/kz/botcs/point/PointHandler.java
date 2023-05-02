package kz.botcs.point;

import kz.botcs.chatbot.InMessage;

import java.io.Serializable;
import java.lang.annotation.Annotation;

public interface PointHandler<A extends Annotation> {
    Integer getOrder();

    Class<A> getType();

    String getKeyword(A annotation);

    Pair<String, Object> keywordAndData(String chatbotId, InMessage inMessage);
}
