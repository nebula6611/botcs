package kz.botcs;

import kz.botcs.client.Chatbot;

public interface InMessageHandlerFactory {

    <C extends Chatbot<I>, I> InMessageHandler<C, I> createHandler(String clientId, C chatbot);
}
