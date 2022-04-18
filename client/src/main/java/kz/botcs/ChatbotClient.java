package kz.botcs;


public interface ChatbotClient<C extends Chatbot<I, O>, I, O> {
    String clientId();

    C getChatBot();
}
