package kz.botcs.client.outmessage;

public class TextOutMessage implements OutMessage {
    private final String id;
    private final String text;

    public TextOutMessage(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
