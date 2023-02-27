package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
public interface OutInlineKeyboardButton extends RestDTO {
    String getText();

    String getCallbackData();
}
