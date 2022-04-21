package kz.botcs.chatbot.outmessage;

import java.util.List;

public class InlineButtonMarkup {
    private final List<List<InlineButton>> map;

    public InlineButtonMarkup(List<List<InlineButton>> map) {
        this.map = map;
    }

    public List<List<InlineButton>> getMap() {
        return map;
    }
}
