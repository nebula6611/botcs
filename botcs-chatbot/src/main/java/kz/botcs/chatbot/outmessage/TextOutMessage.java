package kz.botcs.chatbot.outmessage;

public class TextOutMessage implements OutMessage {
    private final String id;
    private final String text;
    private final String photoId;
    private final InlineButtonMarkup inlineButtonMarkup;

    public TextOutMessage(String id, String text, String photoId, InlineButtonMarkup inlineButtonMarkup) {
        this.id = id;
        this.text = text;
        this.photoId = photoId;
        this.inlineButtonMarkup = inlineButtonMarkup;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getPhotoId() {
        return photoId;
    }

    public InlineButtonMarkup getInlineButtonMarkup() {
        return inlineButtonMarkup;
    }
}
