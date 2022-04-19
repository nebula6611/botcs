package kz.botcs.client.inmessage;

import kz.botcs.client.ChatBotUser;

public class TextInMessage extends InMessage {
    private final String text;

    public TextInMessage(ChatBotUser from, String text, String id) {
        super(from, id);
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
