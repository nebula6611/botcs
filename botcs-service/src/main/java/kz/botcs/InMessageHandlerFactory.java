package kz.botcs;


import kz.botcs.chatbot.Chatbot;

public interface InMessageHandlerFactory {

    <C extends Chatbot<I>, I> InMessageHandler<C, I> createHandler(C chatbot);
}
