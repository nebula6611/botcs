package kz.botcs;


import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;

import java.util.Collections;
import java.util.List;

public class OutResponse {
    private final String stage;
    private final List<OutMessage> outMessages;
    private final BottomMenuOutMessage bottomMenuOutMessage;
    private final Forward forward;

    public OutResponse(String stage, List<OutMessage> outMessages, BottomMenuOutMessage bottomMenuOutMessage, Forward forward) {
        this.stage = stage;
        this.outMessages = Collections.unmodifiableList(outMessages);
        this.bottomMenuOutMessage = bottomMenuOutMessage;
        this.forward = forward;
    }

    public String getStage() {
        return stage;
    }

    public List<OutMessage> getOutMessages() {
        return outMessages;
    }

    public BottomMenuOutMessage getBottomMenuOutMessage() {
        return bottomMenuOutMessage;
    }

    public Forward getForward() {
        return forward;
    }
}
