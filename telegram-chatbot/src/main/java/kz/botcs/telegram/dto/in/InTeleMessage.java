package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import kz.botcs.telegram.dto.RestDTO;
import kz.botcs.telegram.dto.TeleUser;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public interface InTeleMessage extends RestDTO {
    Integer getMessageId();

    @JsonProperty("from")
    TeleUser getFromVal();

    String getText();

    List<InPhotoSize> getPhoto();

    InTeleMessage getReplyToMessage();
}
