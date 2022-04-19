package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdate.Builder.class)
public interface Update extends RestDTO {
    Integer getUpdateId();

    Message getMessage();

    CallbackQuery getCallbackQuery();
}
