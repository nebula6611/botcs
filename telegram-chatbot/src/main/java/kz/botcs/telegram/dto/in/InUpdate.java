package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
public interface InUpdate extends RestDTO {
    Integer getUpdateId();

    InTeleMessage getMessage();

    InCallbackQuery getCallbackQuery();
}
