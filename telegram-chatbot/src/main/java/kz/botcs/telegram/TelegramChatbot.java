package kz.botcs.telegram;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import kz.botcs.chatbot.*;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.chatbot.outmessage.PhotoOutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.*;
import kz.botcs.telegram.mapper.DefaultTelegramMapper;
import kz.botcs.telegram.mapper.TelegramMapper;

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
            AnswerCallbackQuery answerCallbackQuery = ImmutableAnswerCallbackQuery.builder()
                    .callbackQueryId(callbackInMessage.getId()).build();
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

        List<MessageTo> messageTos = outMessages.stream()
                .filter(outMessage -> outMessage instanceof TextOutMessage)
                .map(outMessage -> (TextOutMessage) outMessage)
                .filter(x -> x.getId() == null)
                .map(x -> mapper.toMessageTo(userIdInt, x))
                .map(x -> {
                    if (x.getReplyMarkup() == null && replyKeyboardMarkup != null) {
                        return ImmutableMessageTo.builder()
                                .from(x).replyMarkup(replyKeyboardMarkup).build();
                    } else {
                        return x;
                    }
                }).collect(Collectors.toList());

        List<EditMessage> editMessages = outMessages.stream()
                .filter(outMessage -> outMessage instanceof TextOutMessage)
                .map(outMessage -> (TextOutMessage) outMessage)
                .filter(x -> x.getId() != null)
                .map(x -> mapper.toEditMessage(userIdInt, x))
                .collect(Collectors.toList());

        List<Photo> photos = outMessages.stream()
                .filter(outMessage -> outMessage instanceof PhotoOutMessage)
                .map(outMessage -> (PhotoOutMessage) outMessage)
                .map(x -> mapper.toPhoto(userIdInt, x))
                .collect(Collectors.toList());

        // ------------------------------------------------------

        for (MessageTo messageTo : messageTos) {
            feignTarget.sendMessage(messageTo);
        }

        for (EditMessage editMessage : editMessages) {
            feignTarget.editMessageText(editMessage);
        }

        for (Photo photo : photos) {
            feignTarget.sendPhoto(photo);
        }
    }

    public List<Update> getUpdates(Integer offset) {
        return feignTarget.getUpdates(offset).getResult();
    }

    public void deleteWebhook() {
        feignTarget.deleteWebhook();
    }

}
