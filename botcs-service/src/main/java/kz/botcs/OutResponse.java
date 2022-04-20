package kz.botcs;


import kz.botcs.chatbot.OutMessage;

import java.util.Collections;
import java.util.List;

public class OutResponse {
    private final String stage;
    private final List<OutMessage> outMessages;

    public OutResponse(String stage, List<OutMessage> outMessages) {
        this.stage = stage;
        this.outMessages = Collections.unmodifiableList(outMessages);
    }

    public String getStage() {
        return stage;
    }

    public List<OutMessage> getOutMessages() {
        return outMessages;
    }
}
