package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface ReplyKeyboardMarkup extends ReplyMarkup {
    List<List<KeyboardButton>> getKeyboard();

    @JsonProperty("resize_keyboard") Boolean getResizeKeyboard();

    @JsonProperty("one_time_keyboard") Boolean getOneTimeKeyboard();
}
