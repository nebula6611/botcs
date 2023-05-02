package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@JsonDeserialize(as=ImmutableInUpdate.class)
public interface InUpdate extends RestDTO {
    @JsonProperty("update_id")
    Long getUpdateId();

    @Nullable
    InTeleMessage getMessage();

    @Nullable
    @JsonProperty("callback_query")
    InCallbackQuery getCallbackQuery();
}
