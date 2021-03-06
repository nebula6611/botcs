package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.CallbackInMessage;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.InlineButton;
import kz.botcs.chatbot.outmessage.InlineButtonMarkup;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.in.CallbackQuery;
import kz.botcs.telegram.dto.in.Message;
import kz.botcs.telegram.dto.in.PhotoSize;
import kz.botcs.telegram.dto.in.Update;
import kz.botcs.telegram.dto.out.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(implementationName = "DefaultTelegramMapper")
public abstract class AbstractTelegramMapper implements TelegramMapper {
    protected static final String CALLBACK_DATA_SEPARATOR = "#SEPARATOR#";

    @Override
    public InMessage toInMessage(Update update) {
        if (update.getMessage() != null) {
            return toTextInMessage(update.getMessage());
        }
        if (update.getCallbackQuery() != null) {
            return toCallbackInMessage(update.getCallbackQuery());
        }
        throw new RuntimeException();
    }

    @Override
    @Mapping(target = "chatId", source = "userId")
    @Mapping(target = "messageId", source = "textOutMessage.id")
    @Mapping(target = "replyMarkup", source = "textOutMessage.inlineButtonMarkup")
    @Mapping(target = "text", source = "textOutMessage.text")
    public abstract TextMessage toMessageTo(Integer userId, TextOutMessage textOutMessage);


    @Override
    @Mapping(target = "chatId", source = "userId")
    @Mapping(target = "messageId", source = "textOutMessage.id")
    @Mapping(target = "replyMarkup", source = "textOutMessage.inlineButtonMarkup")
    @Mapping(target = "photo", source = "textOutMessage.photoId")
    @Mapping(target = "caption", source = "textOutMessage.text")
    public abstract PhotoMessage toPhoto(Integer userId, TextOutMessage textOutMessage);

    @Override
    @Mapping(target = "resizeKeyboard", constant = "true")
    @Mapping(target = "oneTimeKeyboard", source = "oneTime")
    @Mapping(target = "keyboard", expression = "java(map(outMessage.getMap(),this::toKeyboardButton))")
    public abstract ReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

    @Mapping(target = "photoId", source = "photo")
    protected abstract TextInMessage toTextInMessage(Message message);

    protected String toPhotoId(List<PhotoSize> photo) {
        if (photo == null) return null;
        return photo.get(0).getFileId();
    }

    @Mapping(target = "keyword", source = "data", qualifiedByName = "toKeyword")
    @Mapping(target = "text", source = "data", qualifiedByName = "toText")
    @Mapping(target = "callbackMessageId", source = "message.messageId")
    protected abstract CallbackInMessage toCallbackInMessage(CallbackQuery callbackQuery);

    @Named("toKeyword")
    protected String toKeyword(String data) {
        return data.split(CALLBACK_DATA_SEPARATOR)[0];
    }

    @Named("toText")
    protected String toText(String data) {
        String[] array = data.split(CALLBACK_DATA_SEPARATOR);
        if (array.length < 2) return null;
        return array[1];
    }

    @Mapping(target = "inlineKeyboard", expression = "java(map(inlineButtonMarkup.getMap(),this::toInlineKeyboardButton))")
    protected abstract InlineKeyboardMarkup toInlineKeyboardMarkup(InlineButtonMarkup inlineButtonMarkup);

    @Mapping(target = "text", source = ".")
    protected abstract KeyboardButton toKeyboardButton(String title);

    @Mapping(target = "text", source = "title")
    @Mapping(target = "callbackData", expression = "java(button.getKeyword()+CALLBACK_DATA_SEPARATOR+button.getText())")
    protected abstract InlineKeyboardButton toInlineKeyboardButton(InlineButton button);

    protected <V, T> List<List<T>> map(List<List<V>> vs, Function<V, T> converter) {
        if (vs == null) return null;
        return vs.stream()
                .map(x -> x.stream().map(converter).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    @Mapping(target = "url", source = ".")
    public abstract Webhook toWebhook(String webhookUrl);

}
