package kz.botcs.chatbot;


import kz.botcs.chatbot.outmessage.OutMessage;

public interface Chatbot<I> {

    String getId();

    InMessage toInMessage(I chatbotInMessage);

    void send(String id, OutMessage outMessage);

}
