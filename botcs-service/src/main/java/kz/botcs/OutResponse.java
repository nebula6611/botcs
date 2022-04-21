package kz.botcs;


import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;

import java.util.Collections;
import java.util.List;

public class OutResponse {
    private final String stage;
    private final List<OutMessage> outMessages;
    private final BottomMenuOutMessage bottomMenuOutMessage;

    public OutResponse(String stage, List<OutMessage> outMessages, BottomMenuOutMessage bottomMenuOutMessage) {
        this.stage = stage;
        this.outMessages = Collections.unmodifiableList(outMessages);
        this.bottomMenuOutMessage = bottomMenuOutMessage;
    }

    public String getStage() {
        return stage;
    }

    public List<OutMessage> getOutMessages() {
        return outMessages;
    }

    public BottomMenuOutMessage getBottomMenu() {
        return bottomMenuOutMessage;
    }
}
