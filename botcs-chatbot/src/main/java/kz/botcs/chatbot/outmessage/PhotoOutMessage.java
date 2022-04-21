package kz.botcs.chatbot.outmessage;

public class PhotoOutMessage implements OutMessage {
    private final String photoId;
    private final InlineButtonMarkup inlineButtonMarkup;

    public PhotoOutMessage(String photoId, InlineButtonMarkup inlineButtonMarkup) {
        this.photoId = photoId;
        this.inlineButtonMarkup = inlineButtonMarkup;
    }

    public String getPhotoId() {
        return photoId;
    }

    public InlineButtonMarkup getInlineButtonMarkup() {
        return inlineButtonMarkup;
    }
}
