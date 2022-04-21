package kz.botcs.chatbot;


import kz.botcs.chatbot.outmessage.OutMessage;

import java.util.List;

public interface Chatbot<I> {

    String getId();

    InMessage toInMessage(I chatbotInMessage);

    void send(String id, List<OutMessage> outMessages);

}
