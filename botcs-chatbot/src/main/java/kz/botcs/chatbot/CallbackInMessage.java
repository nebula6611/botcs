package kz.botcs.chatbot;

public class CallbackInMessage extends InMessage {
    private final String id;
    private final String keyword;
    private final String text;
    private final String callbackMessageId;

    public CallbackInMessage(ChatBotUser from, String id, String keyword, String text, String callbackMessageId) {
        super(from);
        this.id = id;
        this.keyword = keyword;
        this.text = text;
        this.callbackMessageId = callbackMessageId;
    }

    public String getId() {
        return id;
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
