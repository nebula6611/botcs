package kz.botcs.builder;

import kz.botcs.OutResponse;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;

import java.util.ArrayList;
import java.util.List;

public class OutResponseBuilder {
    private String stage;
    private final List<OutMessage> outMessages;
    private BottomMenuOutMessage bottomMenuOutMessage;

    public OutResponseBuilder() {
        this.outMessages = new ArrayList<>();
    }

    public OutResponseBuilder stage(String stage) {
        this.stage = stage;
        return this;
    }

    public OutResponseBuilder addMessage(TextOutMessage message) {
        outMessages.add(message);
        return this;
    }

    public BottomMenuBuilder bottomMenu() {
        return new BottomMenuBuilder();
    }

    public OutResponse build() {
        return new OutResponse(stage, outMessages, bottomMenuOutMessage);
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

        public OutResponseBuilder build() {
            if (!line.isEmpty()) {
                map.add(line);
            }
            OutResponseBuilder.this.bottomMenuOutMessage = new BottomMenuOutMessage(map, oneTime);
            return OutResponseBuilder.this;
        }
    }

}
