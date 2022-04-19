package kz.botcs.telegram;

import kz.botcs.client.ChatBotUser;
import kz.botcs.client.Chatbot;
import kz.botcs.client.inmessage.CallbackInMessage;
import kz.botcs.client.inmessage.InMessage;
import kz.botcs.client.inmessage.TextInMessage;
import kz.botcs.client.outmessage.OutMessage;
import kz.botcs.telegram.dto.Update;
import kz.botcs.telegram.dto.User;

public class TelegramChatbot implements Chatbot<Update> {
    private static final String CALLBACK_DATA_SEPARATOR = "#SEPARATOR#";

    @Override
    public InMessage toInMessage(Update update) {
        if (update.getCallbackQuery() != null) {
            return toCallbackInMessage(update);
        } else {
            return toTextInMessage(update);
        }
    }

    private InMessage toTextInMessage(Update update) {
        ChatBotUser from = toChatbotUser(update.getMessage().getFrom());
        String text = update.getMessage().getText();
        return new TextInMessage(from, text);
    }

    private CallbackInMessage toCallbackInMessage(Update update) {
        ChatBotUser from = toChatbotUser(update.getCallbackQuery().getFrom());
        String[] data = update.getCallbackQuery().getData().split(CALLBACK_DATA_SEPARATOR);
        String keyword = data[0];
        String text = data[1];
        String callbackMessageId = update.getMessage().getMessageId().toString();
        return new CallbackInMessage(from, keyword, text, callbackMessageId);
    }

    private ChatBotUser toChatbotUser(User user) {
        String id = user.getId().toString();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUsername();
        return new ChatBotUser(id, firstName, lastName, username);
    }

    @Override
    public void send(OutMessage outMessage) {

    }
}
