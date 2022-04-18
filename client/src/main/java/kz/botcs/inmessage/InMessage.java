package kz.botcs.inmessage;

import kz.botcs.ChatBotUser;

public abstract class InMessage {
    private final ChatBotUser from;
    private final String id;

    protected InMessage(ChatBotUser from, String id) {
        this.from = from;
        this.id = id;
    }

    public ChatBotUser getFrom() {
        return from;
    }

    public String getId() {
        return id;
    }
}
