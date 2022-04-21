package kz.botcs.chatbot;

import java.util.List;

public class BottomMenu {
    private final List<List<String>> buttons;
    private final boolean resize;
    private final boolean oneTime;

    public BottomMenu(List<List<String>> buttons, boolean resize, boolean oneTime) {
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