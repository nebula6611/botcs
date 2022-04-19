package kz.botcs.client.inmessage;

import kz.botcs.client.ChatBotUser;

public class CallbackInMessage extends InMessage {
    private final String keyword;
    private final String text;
    private final String messageId;

    protected CallbackInMessage(ChatBotUser from, String keyword, String text, String messageId, String id) {
        super(from, id);
        this.keyword = keyword;
        this.text = text;
        this.messageId = messageId;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getText() {
        return text;
    }

    public String getMessageId() {
        return messageId;
    }
}
