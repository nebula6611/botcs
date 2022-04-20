package kz.botcs.telegram;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import kz.botcs.client.ChatBotUser;
import kz.botcs.client.Chatbot;
import kz.botcs.client.inmessage.CallbackInMessage;
import kz.botcs.client.inmessage.InMessage;
import kz.botcs.client.inmessage.TextInMessage;
import kz.botcs.client.outmessage.OutMessage;
import kz.botcs.client.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.*;

public class TelegramChatbot implements Chatbot<Update> {
    private static final String CALLBACK_DATA_SEPARATOR = "#SEPARATOR#";

    private final String id;
    private final FeignTarget feignTarget;

    public TelegramChatbot(String id, String url) {
        this.id = id;
        this.feignTarget = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(FeignTarget.class, url);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public InMessage toInMessage(Update update) {
        if (update.getCallbackQuery() != null) {
            return toCallbackInMessage(update);
        } else {
            return toTextInMessage(update);
        }
    }

    @Override
    public void send(String userId, OutMessage outMessage) {
        Integer userIdInt = Integer.parseInt(userId);
        if (outMessage instanceof TextOutMessage) {
            sendTextOutMessage(userIdInt, (TextOutMessage) outMessage);
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

    private void sendTextOutMessage(Integer userId, TextOutMessage textOutMessage) {
        MessageTo messageTo = ImmutableMessageTo.builder()
                .chatId(userId)
                .text(textOutMessage.getText())
                .build();
        feignTarget.sendMessage(messageTo);
    }
}
