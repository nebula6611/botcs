package kz.botcs.chatbot;

import java.util.List;

public class TextOutMessage implements OutMessage {
    private final String id;
    private final String text;
    private final List<List<Button>> buttons;
    private final BottomMenu bottomMenu;

    public TextOutMessage(String id, String text, List<List<Button>> buttons, BottomMenu bottomMenu) {
        this.id = id;
        this.text = text;
        this.buttons = buttons;
        this.bottomMenu = bottomMenu;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<List<Button>> getButtons() {
        return buttons;
    }

    public BottomMenu getBottomMenu() {
        return bottomMenu;
    }
}
