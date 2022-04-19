package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(builder = ImmutableMessage.Builder.class)
public interface Message extends RestDTO {
    Integer getMessageId();

    User getFrom();

    String getText();

    List<PhotoSize> getPhoto();

    Message getReplyToMessage();
}
