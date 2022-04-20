package kz.botcs.chatbot;

public class CallbackInMessage extends InMessage {
    private final String keyword;
    private final String text;
    private final String callbackMessageId;

    public CallbackInMessage(ChatBotUser from, String keyword, String text, String callbackMessageId) {
        super(from);
        this.keyword = keyword;
        this.text = text;
        this.callbackMessageId = callbackMessageId;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getText() {
        return text;
    }

    public String getCallbackMessageId() {
        return callbackMessageId;
    }
}
