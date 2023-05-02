package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kz.botcs.telegram.dto.RestDTO;
import kz.botcs.telegram.dto.TeleUser;

import java.util.List;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@JsonDeserialize(as=ImmutableInTeleMessage.class)
public interface InTeleMessage extends RestDTO {
    @JsonProperty("message_id")
    Integer getMessageId();

    @JsonProperty("from")
    TeleUser getFromVal();

    String getText();

    List<InPhotoSize> getPhoto();

    @Nullable
    @JsonProperty("reply_to_message")
    InTeleMessage getReplyToMessage();
}
