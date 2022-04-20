package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface InlineKeyboardMarkup extends ReplyMarkup {
    @JsonProperty("inline_keyboard") List<List<InlineKeyboardButton>> getInlineKeyboard();
}
