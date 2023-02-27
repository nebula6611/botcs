package kz.botcs.telegram.dto.out;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public interface OutInlineKeyboardMarkup extends OutReplyMarkup {
    List<List<OutInlineKeyboardButton>> getInlineKeyboard();
}
