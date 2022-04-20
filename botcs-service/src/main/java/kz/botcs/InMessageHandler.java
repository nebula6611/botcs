package kz.botcs;


import kz.botcs.chatbot.Chatbot;

public interface InMessageHandler<C extends Chatbot<I>, I> {
    void handle(I chatbotMessage);
}
