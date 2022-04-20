package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMessage.Builder.class)
public interface Message extends RestDTO {
    @JsonProperty("message_id")
    Integer getMessageId();

    User getFrom();

    String getText();

    List<PhotoSize> getPhoto();

    @Nullable
    @JsonProperty("replyToMassage") Message getReplyToMessage();
}
