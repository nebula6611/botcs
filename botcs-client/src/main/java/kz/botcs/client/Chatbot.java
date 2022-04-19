package kz.botcs.client;


import kz.botcs.client.inmessage.InMessage;

public interface Chatbot<I, O> {

    Class<I> getInMessageClass();

    InMessage toInMessage(I chatbotInMessage);

    ChatBotUser toUser(I chatbotInMessage);

    O toChatbotOutMessage(OutMessage outMessage);

}
