package kz.botcs;


public interface InMessageHandler<I> {
    void handle(I chatbotMessage);
}
