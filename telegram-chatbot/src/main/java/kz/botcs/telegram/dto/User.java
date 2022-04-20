package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUser.Builder.class)
public interface User extends RestDTO {
    Integer getId();

    @JsonProperty("first_name") String getFirstName();

    @Nullable
    @JsonProperty("last_name") String getLastName();

    @Nullable
    String getUsername();
}
