package kz.botcs.client;


import kz.botcs.client.inmessage.InMessage;
import kz.botcs.client.outmessage.OutMessage;

public interface Chatbot<I> {

    String getId();

    InMessage toInMessage(I chatbotInMessage);

    void send(String id, OutMessage outMessage);

}
