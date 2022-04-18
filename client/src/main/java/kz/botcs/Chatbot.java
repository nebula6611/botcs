package kz.botcs;

import kz.botcs.inmessage.InMessage;

public interface Chatbot<I, O> {

    Class<I> getInMessageClass();

    InMessage toInMessage(I chatbotInMessage);

    ChatBotUser toUser(I chatbotInMessage);

    O toChatbotOutMessage(OutMessage outMessage);

}
