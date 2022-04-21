package kz.botcs.builder;

import kz.botcs.OutResponse;
import kz.botcs.chatbot.OutMessage;
import kz.botcs.chatbot.TextOutMessage;

import java.util.ArrayList;
import java.util.List;

public class OutResponseBuilder {
    private String stage;
    private final List<OutMessage> outMessages;

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

    public OutResponse build() {
        return new OutResponse(stage, outMessages);
    }

}
