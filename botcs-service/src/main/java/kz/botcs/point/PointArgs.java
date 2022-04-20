package kz.botcs.point;


import kz.botcs.chatbot.InMessage;

public class PointArgs {
    private final String text;
    private final InMessage inMessage;

    public PointArgs(String text, InMessage inMessage) {
        this.text = text;
        this.inMessage = inMessage;
    }

    public String getText() {
        return text;
    }

    public InMessage getInMessage() {
        return inMessage;
    }
}
