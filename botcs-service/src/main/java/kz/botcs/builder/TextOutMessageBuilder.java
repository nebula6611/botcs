package kz.botcs.builder;

import kz.botcs.chatbot.outmessage.Button;
import kz.botcs.chatbot.outmessage.TextOutMessage;

import java.util.ArrayList;
import java.util.List;

public class TextOutMessageBuilder {
    private String id;
    private String text;
    private List<List<Button>> buttons;

    public TextOutMessageBuilder() {
    }

    public TextOutMessageBuilder id(String id) {
        this.id = id;
        return this;
    }

    public TextOutMessageBuilder text(String text) {
        this.text = text;
        return this;
    }

    public ButtonsBuilder buttons() {
        return new ButtonsBuilder();
    }

    public TextOutMessage build() {
        return new TextOutMessage(id, text, buttons);
    }


    public class ButtonsBuilder {
        private final List<List<Button>> buttons;
        private List<Button> buttonLine;

        public ButtonsBuilder() {
            this.buttons = new ArrayList<>();
            this.buttonLine = new ArrayList<>();
        }

        public ButtonsBuilder add(Button button) {
            buttonLine.add(button);
            return this;
        }

        public ButtonsBuilder add(String title, String keyword, String text) {
            add(new Button(title, keyword, text));
            return this;
        }

        public ButtonsBuilder addLineBrake() {
            if (!buttonLine.isEmpty()) {
                buttons.add(buttonLine);
                buttonLine = new ArrayList<>();
            }
            return this;
        }

        public TextOutMessageBuilder build() {
            if (!buttonLine.isEmpty()) {
                buttons.add(buttonLine);
            }
            TextOutMessageBuilder.this.buttons = buttons;
            return TextOutMessageBuilder.this;
        }
    }
}
