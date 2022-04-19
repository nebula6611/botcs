package kz.botcs.client.inmessage;

import kz.botcs.client.ChatBotUser;

public abstract class InMessage {
    private final ChatBotUser from;

    protected InMessage(ChatBotUser from) {
        this.from = from;
    }

    public ChatBotUser getFrom() {
        return from;
    }
}
