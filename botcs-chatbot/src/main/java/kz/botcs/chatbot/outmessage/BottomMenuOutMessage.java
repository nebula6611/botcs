package kz.botcs.chatbot.outmessage;

import java.util.List;

public class BottomMenuOutMessage implements OutMessage {
    private final List<List<String>> map;
    private final boolean oneTime;

    public BottomMenuOutMessage(List<List<String>> map, boolean oneTime) {
        this.map = map;
        this.oneTime = oneTime;
    }

    public List<List<String>> getMap() {
        return map;
    }

    public boolean isOneTime() {
        return oneTime;
    }
}
