package kz.botcs.telegram;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import kz.botcs.chatbot.CallbackInMessage;
import kz.botcs.chatbot.Chatbot;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.in.InUpdate;
import kz.botcs.telegram.dto.out.*;
import kz.botcs.telegram.mapper.DefaultTelegramMapper;
import kz.botcs.telegram.mapper.TelegramMapper;

import java.util.List;

public class TelegramChatbot implements Chatbot<InUpdate> {

    private final String id;
    private final TeleFeignTarget feignTarget;
    private final TelegramMapper mapper;

    public TelegramChatbot(String id, String token) {
        this.id = id;
        this.feignTarget = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(TeleFeignTarget.class, "https://api.telegram.org/bot" + token);
        this.mapper = new DefaultTelegramMapper();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public InMessage toInMessage(InUpdate update) {
        InMessage inMessage = mapper.toInMessage(update);
        if (inMessage instanceof CallbackInMessage) {
            CallbackInMessage callbackInMessage = (CallbackInMessage) inMessage;
            OutAnswerCallbackQuery answerCallbackQuery = ImmutableOutAnswerCallbackQuery
                    .builder()
                    .callbackQueryId(callbackInMessage.getId())
                    .build();
            feignTarget.answerCallbackQuery(answerCallbackQuery);
        }
        return inMessage;
    }

    @Override
    public void send(String userId, List<OutMessage> outMessages) {
        Integer userIdInt = Integer.parseInt(userId);

        final OutReplyKeyboardMarkup replyKeyboardMarkup = outMessages.stream()
                .filter(outMessage -> outMessage instanceof BottomMenuOutMessage)
                .map(outMessage -> (BottomMenuOutMessage) outMessage)
                .map(mapper::toReplyKeyboardMarkup)
                .reduce((x, y) -> y).orElse(null);

        for (OutMessage outMessage : outMessages) {
            if (!(outMessage instanceof TextOutMessage)) continue;
            TextOutMessage textOutMessage = (TextOutMessage) outMessage;
            if (textOutMessage.getPhotoId() != null) {
                OutPhotoMessage outTeleMessage = mapper.toPhoto(userIdInt, textOutMessage, replyKeyboardMarkup);
                if (outTeleMessage.getMessageId() == null) feignTarget.sendPhoto(outTeleMessage);
                else feignTarget.editMessageCaption(outTeleMessage);
            } else {
                OutTextMessage outTeleMessage = mapper.toMessageTo(userIdInt, textOutMessage, replyKeyboardMarkup);
                if (outTeleMessage.getMessageId() == null) feignTarget.sendMessage(outTeleMessage);
                else feignTarget.editMessageText(outTeleMessage);
            }
        }
    }

    public List<InUpdate> getUpdates(Long offset) {
        return feignTarget.getUpdates(offset).getResult();
    }

    public void setWebhook(String url) {
        this.feignTarget.setWebhook(mapper.toWebhook(url));
    }

    public void deleteWebhook() {
        feignTarget.deleteWebhook();
    }


}
