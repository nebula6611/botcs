package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface InlineKeyboardButton extends RestDTO {
    String getText();

    String getCallbackData();
}
