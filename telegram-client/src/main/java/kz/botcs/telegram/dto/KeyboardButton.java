package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface KeyboardButton extends RestDTO {
    String getText();
}
