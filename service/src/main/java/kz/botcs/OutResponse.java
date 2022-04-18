package kz.botcs;

import java.util.Collections;
import java.util.List;

public class OutResponse {
    private final String stage;
    private final List<OutMessage> outMessages;

    public OutResponse(String stage, List<OutMessage> outMessages) {
        this.stage = stage;
        this.outMessages = Collections.unmodifiableList(outMessages);
    }
}
