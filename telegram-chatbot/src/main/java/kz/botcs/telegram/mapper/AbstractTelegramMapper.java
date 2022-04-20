package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.*;
import kz.botcs.telegram.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
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
    @Mapping(target = "replyMarkup", source = "textOutMessage.buttons")
    public abstract MessageTo toMessageTo(Integer userId, TextOutMessage textOutMessage);

    protected abstract TextInMessage toTextInMessage(Message message);

    @Mapping(target = "keyword", expression = "java(split(callbackQuery.getData())[0])")
    @Mapping(target = "text", expression = "java(split(callbackQuery.getData())[1])")
    @Mapping(target = "callbackMessageId", source = "message.messageId")
    protected abstract CallbackInMessage toCallbackInMessage(CallbackQuery callbackQuery);

    protected String[] split(String data) {
        return data.split(CALLBACK_DATA_SEPARATOR);
    }

    protected InlineKeyboardMarkup toInlineKeyboardMarkup(List<List<Button>> buttons) {
        if (buttons == null) return null;
        return ImmutableInlineKeyboardMarkup.builder().inlineKeyboard(buttons.stream()
                .map(x -> x.stream().map(this::toInlineKeyboardButton).collect(Collectors.toList()))
                .collect(Collectors.toList())).build();
    }

    @Mapping(target = "text", source = "title")
    @Mapping(target = "callbackData", expression = "java(button.getKeyword()+CALLBACK_DATA_SEPARATOR+button.getText())")
    protected abstract InlineKeyboardButton toInlineKeyboardButton(Button button);
}
