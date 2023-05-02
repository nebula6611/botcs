package kz.botcs.point;


import kz.botcs.chatbot.InMessage;

import java.io.Serializable;

public class PointArgs {
    private final String chatbotId;
    private final Object data;
    private final InMessage inMessage;

    public PointArgs(String chatbotId, Object data, InMessage inMessage) {
        this.chatbotId = chatbotId;
        this.data = data;
        this.inMessage = inMessage;
    }

    public String getChatbotId() {
        return chatbotId;
    }

    public Object getData() {
        return data;
    }

    public InMessage getInMessage() {
        return inMessage;
    }
}
