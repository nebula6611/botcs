package kz.botcs.chatbot;

public class TextInMessage extends InMessage {
    private final String text;

    public TextInMessage(ChatBotUser from, String text) {
        super(from);
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
