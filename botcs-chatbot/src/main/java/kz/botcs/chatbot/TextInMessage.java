package kz.botcs.chatbot;

public class TextInMessage extends InMessage {
    private final String text;
    private final String photoId;

    public TextInMessage(ChatBotUser from, String text, String photoId) {
        super(from);
        this.text = text;
        this.photoId = photoId;
    }

    public String getText() {
        return text;
    }

    public String getPhotoId() {
        return photoId;
    }
}
