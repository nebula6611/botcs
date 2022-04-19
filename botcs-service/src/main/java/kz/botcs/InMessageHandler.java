package kz.botcs;

import kz.botcs.client.Chatbot;

public interface InMessageHandler<C extends Chatbot<I, O>, I, O> {
    void handle(I chatbotMessage);
}
