package kz.botcs.builder;

import kz.botcs.Forward;
import kz.botcs.OutResponse;
import kz.botcs.chatbot.outmessage.*;
import kz.botcs.point.handler.CallbackPoint;
import kz.botcs.point.handler.CommandPoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OutResponseBuilder {
    private String stage;
    private final List<OutMessage> outMessages;
    private BottomMenuOutMessage bottomMenuOutMessage;
    private Forward forward;

    public OutResponseBuilder() {
        this.outMessages = new ArrayList<>();
    }

    public OutResponseBuilder stage(String stage) {
        this.stage = stage;
        return this;
    }

    public TextOutMessageBuilder newMessage() {
        return new TextOutMessageBuilder();
    }

    public OutResponseBuilder addMessage(String text) {
        return newMessage().text(text).add();
    }

    public OutResponseBuilder addMessage(TextOutMessage message) {
        outMessages.add(message);
        return this;
    }

    public OutResponseBuilder addMessage(Collection<TextOutMessage> messages) {
        outMessages.addAll(messages);
        return this;
    }

    public BottomMenuBuilder bottomMenu() {
        return new BottomMenuBuilder();
    }

    public OutResponse build() {
        return new OutResponse(stage, outMessages, bottomMenuOutMessage, forward);
    }

    public OutResponseBuilder forwardCommand(String command) {
        forward = new Forward(CommandPoint.class, command, null);
        return this;
    }

    public OutResponseBuilder forwardCallback(String callback, String data) {
        forward = new Forward(CallbackPoint.class, callback, data);
        return this;
    }

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

        public ButtonsBuilder buttons() {
            return new ButtonsBuilder();
        }

        public OutResponseBuilder add() {
            return addMessage(new TextOutMessage(id, text, photoId, inlineButtonMarkup));
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

            public ButtonsBuilder add(String title, String keyword) {
                return add(title, keyword, null);
            }

            public ButtonsBuilder addLineBrake() {
                if (!line.isEmpty()) {
                    map.add(line);
                    line = new ArrayList<>();
                }
                return this;
            }

            public TextOutMessageBuilder end() {
                if (!line.isEmpty()) {
                    map.add(line);
                }
                TextOutMessageBuilder.this.inlineButtonMarkup = new InlineButtonMarkup(map);
                return TextOutMessageBuilder.this;
            }
        }
    }


    public class BottomMenuBuilder {
        private final List<List<String>> map;
        private List<String> line;
        private boolean resize;
        private boolean oneTime;

        public BottomMenuBuilder() {
            this.map = new ArrayList<>();
            this.line = new ArrayList<>();
        }

        public BottomMenuBuilder add(String title) {
            line.add(title);
            return this;
        }

        public BottomMenuBuilder addLineBrake() {
            if (!line.isEmpty()) {
                map.add(line);
                line = new ArrayList<>();
            }
            return this;
        }

        public BottomMenuBuilder resize(boolean resize) {
            this.resize = resize;
            return this;
        }

        public BottomMenuBuilder oneTime(boolean oneTime) {
            this.oneTime = oneTime;
            return this;
        }

        public OutResponseBuilder add() {
            if (!line.isEmpty()) {
                map.add(line);
            }
            OutResponseBuilder.this.bottomMenuOutMessage = new BottomMenuOutMessage(map, oneTime);
            return OutResponseBuilder.this;
        }
    }

}
