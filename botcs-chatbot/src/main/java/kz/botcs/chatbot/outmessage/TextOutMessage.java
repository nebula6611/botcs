package kz.botcs.chatbot.outmessage;

public class TextOutMessage implements OutMessage {
    private final String id;
    private final String text;
    private final InlineButtonMarkup inlineButtonMarkup;

    public TextOutMessage(String id, String text, InlineButtonMarkup inlineButtonMarkup) {
        this.id = id;
        this.text = text;
        this.inlineButtonMarkup = inlineButtonMarkup;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public InlineButtonMarkup getInlineButtonMarkup() {
        return inlineButtonMarkup;
    }
}
