package kz.botcs.telegram.dto.out;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public interface OutReplyKeyboardMarkup extends OutReplyMarkup {
    List<List<OutKeyboardButton>> getKeyboard();

    Boolean getResizeKeyboard();

    Boolean getOneTimeKeyboard();
}
