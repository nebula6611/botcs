package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.*;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.Button;
import kz.botcs.chatbot.outmessage.TextOutMessage;
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
    @Mapping(target = "replyMarkup", source = "textOutMessage")
    public abstract MessageTo toMessageTo(Integer userId, TextOutMessage textOutMessage);

    @Override
    @Mapping(target = "chatId", source = "userId")
    @Mapping(target = "text", constant = "")
    @Mapping(target = "replyMarkup", source = "bottomMenuOutMessage")
    public abstract MessageTo toMessageTo(Integer userId, BottomMenuOutMessage bottomMenuOutMessage);

    protected abstract TextInMessage toTextInMessage(Message message);

    @Mapping(target = "keyword", expression = "java(split(callbackQuery.getData())[0])")
    @Mapping(target = "text", expression = "java(split(callbackQuery.getData())[1])")
    @Mapping(target = "callbackMessageId", source = "message.messageId")
    protected abstract CallbackInMessage toCallbackInMessage(CallbackQuery callbackQuery);

    @Mapping(target = "inlineKeyboard", expression = "java(map(textOutMessage.getButtons(),this::toInlineKeyboardButton))")
    protected abstract InlineKeyboardMarkup toInlineKeyboardMarkup(TextOutMessage textOutMessage);

    @Mapping(target = "resizeKeyboard", source = "resize")
    @Mapping(target = "oneTimeKeyboard", source = "oneTime")
    @Mapping(target = "keyboard", expression = "java(map(outMessage.getButtons(),this::toKeyboardButton))")
    protected abstract ReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

    @Mapping(target = "text", source = ".")
    protected abstract KeyboardButton toKeyboardButton(String title);

    @Mapping(target = "text", source = "title")
    @Mapping(target = "callbackData", expression = "java(button.getKeyword()+CALLBACK_DATA_SEPARATOR+button.getText())")
    protected abstract InlineKeyboardButton toInlineKeyboardButton(Button button);

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
