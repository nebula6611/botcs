package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
public interface InlineKeyboardButton extends RestDTO {
    String getText();

    @JsonProperty("callback_data") String getCallbackData();
}
