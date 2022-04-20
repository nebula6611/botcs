package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdate.Builder.class)
public interface Update extends RestDTO {
    @JsonProperty("update_id") Integer getUpdateId();

    Message getMessage();

    @Nullable
    @JsonProperty("callback_query") CallbackQuery getCallbackQuery();
}
