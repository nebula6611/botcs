package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUser.Builder.class)
public interface User {
    Integer getId();

    String getFirstName();

    String getLastName();

    String getUsername();
}
