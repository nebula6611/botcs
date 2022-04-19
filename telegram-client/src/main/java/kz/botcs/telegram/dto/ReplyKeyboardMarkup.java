package kz.botcs.telegram.dto;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface ReplyKeyboardMarkup extends ReplyMarkup {
    List<List<KeyboardButton>> getKeyboard();

    Boolean getResizeKeyboard();

    Boolean getOneTimeKeyboard();
}
