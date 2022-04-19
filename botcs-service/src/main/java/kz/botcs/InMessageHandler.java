package kz.botcs;

import kz.botcs.client.Chatbot;

public interface InMessageHandler<C extends Chatbot<I>, I> {
    void handle(I chatbotMessage);
}
