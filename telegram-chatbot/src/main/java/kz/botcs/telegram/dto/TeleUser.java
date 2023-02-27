package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface TeleUser extends RestDTO {
    String getId();

    String getFirstName();

    String getLastName();

    String getUsername();
}
