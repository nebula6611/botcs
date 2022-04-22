package kz.botcs.builder;

import kz.botcs.chatbot.outmessage.InlineButton;
import kz.botcs.chatbot.outmessage.InlineButtonMarkup;
import kz.botcs.chatbot.outmessage.TextOutMessage;

import java.util.ArrayList;
import java.util.List;

public class TextOutMessageBuilder {
    private String id;
    private String text;
    private String photoId;
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

    public TextOutMessageBuilder photoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public TextOutMessageBuilder.ButtonsBuilder buttons() {
        return new TextOutMessageBuilder.ButtonsBuilder();
    }

    public TextOutMessage build() {
        return new TextOutMessage(id, text, photoId, inlineButtonMarkup);
    }

    public class ButtonsBuilder {
        private final List<List<InlineButton>> map;
        private List<InlineButton> line;

        public ButtonsBuilder() {
            this.map = new ArrayList<>();
            this.line = new ArrayList<>();
        }

        public TextOutMessageBuilder.ButtonsBuilder add(InlineButton button) {
            line.add(button);
            return this;
        }

        public TextOutMessageBuilder.ButtonsBuilder add(String title, String keyword, String text) {
            add(new InlineButton(title, keyword, text));
            return this;
        }

        public TextOutMessageBuilder.ButtonsBuilder add(String title, String keyword) {
            return add(title, keyword, null);
        }


        public TextOutMessageBuilder.ButtonsBuilder addLineBrake() {
            if (!line.isEmpty()) {
                map.add(line);
                line = new ArrayList<>();
            }
            return this;
        }

        public TextOutMessageBuilder and() {
            if (!line.isEmpty()) {
                map.add(line);
            }
            TextOutMessageBuilder.this.inlineButtonMarkup = new InlineButtonMarkup(map);
            return TextOutMessageBuilder.this;
        }
    }
}