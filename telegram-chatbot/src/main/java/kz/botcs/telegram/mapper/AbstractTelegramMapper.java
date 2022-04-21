package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.*;
import kz.botcs.chatbot.outmessage.*;
import kz.botcs.telegram.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(target = "text", source = "textOutMessage.text")
    @Mapping(target = "replyMarkup", source = "textOutMessage.inlineButtonMarkup")
    public abstract MessageTo toMessageTo(Integer userId, TextOutMessage textOutMessage);

    @Override
    @Mapping(target = "chatId", source = "userId")
    @Mapping(target = "messageId", source = "textOutMessage.id")
    @Mapping(target = "text", source = "textOutMessage.text")
    @Mapping(target = "replyMarkup", source = "textOutMessage.inlineButtonMarkup")
    public abstract EditMessage toEditMessage(Integer userId, TextOutMessage textOutMessage);

    @Override
    @Mapping(target = "chatId", source = "userId")
    @Mapping(target = "photoId", source = "photoOutMessage.photoId")
    @Mapping(target = "replyMarkup", source = "photoOutMessage.inlineButtonMarkup")
    public abstract Photo toPhoto(Integer userId, PhotoOutMessage photoOutMessage);

    @Override
    @Mapping(target = "resizeKeyboard", constant = "true")
    @Mapping(target = "oneTimeKeyboard", source = "oneTime")
    @Mapping(target = "keyboard", expression = "java(map(outMessage.getMap(),this::toKeyboardButton))")
    public abstract ReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

    protected abstract TextInMessage toTextInMessage(Message message);

    @Mapping(target = "keyword", expression = "java(split(callbackQuery.getData())[0])")
    @Mapping(target = "text", expression = "java(split(callbackQuery.getData())[1])")
    @Mapping(target = "callbackMessageId", source = "message.messageId")
    protected abstract CallbackInMessage toCallbackInMessage(CallbackQuery callbackQuery);
    
    @Mapping(target = "inlineKeyboard", expression = "java(map(inlineButtonMarkup.getMap(),this::toInlineKeyboardButton))")
    protected abstract InlineKeyboardMarkup toInlineKeyboardMarkup(InlineButtonMarkup inlineButtonMarkup);

    @Mapping(target = "text", source = ".")
    protected abstract KeyboardButton toKeyboardButton(String title);

    @Mapping(target = "text", source = "title")
    @Mapping(target = "callbackData", expression = "java(button.getKeyword()+CALLBACK_DATA_SEPARATOR+button.getText())")
    protected abstract InlineKeyboardButton toInlineKeyboardButton(InlineButton button);

    protected String[] split(String data) {
        return data.split(CALLBACK_DATA_SEPARATOR);
    }

    protected <V, T> List<List<T>> map(List<List<V>> vs, Function<V, T> converter) {
        if (vs == null) return null;
        return vs.stream()
                .map(x -> x.stream().map(converter).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
