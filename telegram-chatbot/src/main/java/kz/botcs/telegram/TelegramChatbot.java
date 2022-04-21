package kz.botcs.telegram;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import kz.botcs.chatbot.*;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.*;
import kz.botcs.telegram.mapper.DefaultTelegramMapper;
import kz.botcs.telegram.mapper.TelegramMapper;

import java.util.List;

public class TelegramChatbot implements Chatbot<Update> {

    private final String id;
    private final FeignTarget feignTarget;
    private final TelegramMapper mapper;

    public TelegramChatbot(String id, String url) {
        this.id = id;
        this.feignTarget = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(FeignTarget.class, url);
        this.mapper = new DefaultTelegramMapper();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public InMessage toInMessage(Update update) {
        InMessage inMessage = mapper.toInMessage(update);
        if (inMessage instanceof CallbackInMessage) {
            CallbackInMessage callbackInMessage = (CallbackInMessage) inMessage;
            AnswerCallbackQuery answerCallbackQuery = ImmutableAnswerCallbackQuery.builder()
                    .callbackQueryId(callbackInMessage.getId()).build();
            feignTarget.answerCallbackQuery(answerCallbackQuery);
        }
        return inMessage;
    }

    @Override
    public void send(String userId, OutMessage outMessage) {
        Integer userIdInt = Integer.parseInt(userId);
        if (outMessage instanceof TextOutMessage) {
            MessageTo messageTo = mapper.toMessageTo(userIdInt, (TextOutMessage) outMessage);
            feignTarget.sendMessage(messageTo);
        }
        if (outMessage instanceof BottomMenuOutMessage){
            MessageTo messageTo = mapper.toMessageTo(userIdInt, (BottomMenuOutMessage) outMessage);
            feignTarget.sendMessage(messageTo);
        }
    }

    public List<Update> getUpdates(Integer offset) {
        return feignTarget.getUpdates(offset).getResult();
    }

    public void deleteWebhook() {
        feignTarget.deleteWebhook();
    }

}
