package kz.botcs.builder;

import kz.botcs.chatbot.outmessage.InlineButton;
import kz.botcs.chatbot.outmessage.InlineButtonMarkup;
import kz.botcs.chatbot.outmessage.TextOutMessage;

import java.util.ArrayList;
import java.util.List;

public class TextOutMessageBuilder {
    private String id;
    private String text;
    private InlineButtonMarkup inlineButtonMarkup;

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
        return new TextOutMessage(id, text, inlineButtonMarkup);
    }


    public class ButtonsBuilder {
        private final List<List<InlineButton>> map;
        private List<InlineButton> line;

        public ButtonsBuilder() {
            this.map = new ArrayList<>();
            this.line = new ArrayList<>();
        }

        public ButtonsBuilder add(InlineButton button) {
            line.add(button);
            return this;
        }

        public ButtonsBuilder add(String title, String keyword, String text) {
            add(new InlineButton(title, keyword, text));
            return this;
        }

        public ButtonsBuilder addLineBrake() {
            if (!line.isEmpty()) {
                map.add(line);
                line = new ArrayList<>();
            }
            return this;
        }

        public TextOutMessageBuilder build() {
            if (!line.isEmpty()) {
                map.add(line);
            }
            TextOutMessageBuilder.this.inlineButtonMarkup = new InlineButtonMarkup(map);
            return TextOutMessageBuilder.this;
        }
    }
}
