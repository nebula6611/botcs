package kz.botcs;

public interface InMessageHandlerFactory {

    <C extends Chatbot<I, O>, I, O> InMessageHandler<C, I, O> createHandler(String clientId, C chatbot);
}
