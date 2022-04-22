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
import kz.botcs.telegram.dto.in.Update;
import kz.botcs.telegram.dto.out.*;
import kz.botcs.telegram.mapper.DefaultTelegramMapper;
import kz.botcs.telegram.mapper.TelegramMapper;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
            answerCallbackQuery.setCallbackQueryId(callbackInMessage.getId());
            feignTarget.answerCallbackQuery(answerCallbackQuery);
        }
        return inMessage;
    }

    @Override
    public void send(String userId, List<OutMessage> outMessages) {
        Integer userIdInt = Integer.parseInt(userId);

        final ReplyKeyboardMarkup replyKeyboardMarkup = outMessages.stream()
                .filter(outMessage -> outMessage instanceof BottomMenuOutMessage)
                .map(outMessage -> (BottomMenuOutMessage) outMessage)
                .map(mapper::toReplyKeyboardMarkup)
                .reduce((x, y) -> y).orElse(null);

        List<TextMessage> textMessages = outMessages.stream()
                .filter(outMessage -> outMessage instanceof TextOutMessage)
                .map(outMessage -> (TextOutMessage) outMessage)
                .filter(x -> x.getPhotoId() == null)
                .map(x -> mapper.toMessageTo(userIdInt, x))
                .peek(x -> setReplyKeyboardMarkup(x, replyKeyboardMarkup))
                .collect(Collectors.toList());

        List<PhotoMessage> photoMessages = outMessages.stream()
                .filter(outMessage -> outMessage instanceof TextOutMessage)
                .map(outMessage -> (TextOutMessage) outMessage)
                .filter(x -> x.getPhotoId() != null)
                .map(x -> mapper.toPhoto(userIdInt, x))
                .peek(x -> setReplyKeyboardMarkup(x, replyKeyboardMarkup))
                .collect(Collectors.toList());

        // ------------------------------------------------------

        for (TextMessage textMessage : textMessages) {
            if (textMessage.getMessageId() == null) feignTarget.sendMessage(textMessage);
            else feignTarget.editMessageText(textMessage);
        }

        for (PhotoMessage photoMessage : photoMessages) {
            if (photoMessage.getMessageId() == null) feignTarget.sendPhoto(photoMessage);
            else feignTarget.editMessageCaption(photoMessage);
        }
    }

    private void setReplyKeyboardMarkup(kz.botcs.telegram.dto.out.OutMessage textMessage, ReplyKeyboardMarkup replyKeyboardMarkup) {
        if (textMessage.getReplyMarkup() == null) {
            textMessage.setReplyMarkup(replyKeyboardMarkup);
        }
    }

    public List<Update> getUpdates(Integer offset) {
        return feignTarget.getUpdates(offset).getResult();
    }

    public void deleteWebhook() {
        feignTarget.deleteWebhook();
    }

}
