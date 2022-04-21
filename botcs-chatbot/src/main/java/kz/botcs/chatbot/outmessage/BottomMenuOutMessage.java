package kz.botcs.chatbot.outmessage;

import java.util.List;

public class BottomMenuOutMessage implements OutMessage {
    private final List<List<String>> buttons;
    private final boolean resize;
    private final boolean oneTime;

    public BottomMenuOutMessage(List<List<String>> buttons, boolean resize, boolean oneTime) {
        this.buttons = buttons;
        this.resize = resize;
        this.oneTime = oneTime;
    }

    public List<List<String>> getButtons() {
        return buttons;
    }

    public boolean isResize() {
        return resize;
    }

    public boolean isOneTime() {
        return oneTime;
    }
}
