package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
public interface OutDeleteMessage extends RestDTO {
    Integer getChatId();

    Integer getMessageId();
}
