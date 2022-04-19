package kz.botcs;

public interface InMessageHandler<C extends Chatbot<I, O>, I, O> {
    void handle(I chatbotMessage);
}
