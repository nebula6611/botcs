package kz.botcs.point;


import kz.botcs.chatbot.InMessage;

public class PointArgs {
    private final String chatbotId;
    private final String text;
    private final InMessage inMessage;

    public PointArgs(String chatbotId, String text, InMessage inMessage) {
        this.chatbotId = chatbotId;
        this.text = text;
        this.inMessage = inMessage;
    }

    public String getChatbotId() {
        return chatbotId;
    }

    public String getText() {
        return text;
    }

    public InMessage getInMessage() {
        return inMessage;
    }
}
