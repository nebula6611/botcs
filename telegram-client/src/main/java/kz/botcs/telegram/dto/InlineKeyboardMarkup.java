package kz.botcs.telegram.dto;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface InlineKeyboardMarkup extends ReplyMarkup {
    List<List<InlineKeyboardButton>> getInlineKeyboard();
}
