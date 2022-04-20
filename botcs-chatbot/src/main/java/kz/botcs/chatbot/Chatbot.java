package kz.botcs.chatbot;


public interface Chatbot<I> {

    String getId();

    InMessage toInMessage(I chatbotInMessage);

    void send(String id, OutMessage outMessage);

}
