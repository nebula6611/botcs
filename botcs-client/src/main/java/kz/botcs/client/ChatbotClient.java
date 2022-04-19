package kz.botcs.client;


public interface ChatbotClient<C extends Chatbot<I>, I> {
    String clientId();

    C getChatBot();
}
