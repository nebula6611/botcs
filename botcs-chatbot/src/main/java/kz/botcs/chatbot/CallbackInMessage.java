package kz.botcs.chatbot;

import java.io.Serializable;

public class CallbackInMessage extends InMessage {
    private final String id;
    private final String keyword;
    private final Object data;
    private final String callbackMessageId;

    public CallbackInMessage(ChatBotUser from, String id, String keyword, Object data, String callbackMessageId) {
        super(from);
        this.id = id;
        this.keyword = keyword;
        this.data = data;
        this.callbackMessageId = callbackMessageId;
    }

    public String getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public Object getData() {
        return data;
    }

    public String getCallbackMessageId() {
        return callbackMessageId;
    }
}
