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
        private final List<List<String>> buttons;
        private List<String> buttonLine;
        private boolean resize;
        private boolean oneTime;

        public BottomMenuBuilder() {
            this.buttons = new ArrayList<>();
            this.buttonLine = new ArrayList<>();
        }

        public BottomMenuBuilder add(String title) {
            buttonLine.add(title);
            return this;
        }

        public BottomMenuBuilder addLineBrake() {
            if (!buttonLine.isEmpty()) {
                buttons.add(buttonLine);
                buttonLine = new ArrayList<>();
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
            if (!buttonLine.isEmpty()) {
                buttons.add(buttonLine);
            }
            OutResponseBuilder.this.bottomMenuOutMessage = new BottomMenuOutMessage(buttons, resize, oneTime);
            return OutResponseBuilder.this;
        }
    }

}
