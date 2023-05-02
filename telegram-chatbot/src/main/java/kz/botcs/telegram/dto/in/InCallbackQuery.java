package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kz.botcs.telegram.dto.RestDTO;
import kz.botcs.telegram.dto.TeleUser;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as=ImmutableInCallbackQuery.class)
public interface InCallbackQuery extends RestDTO {
    String getId();

    @JsonProperty("from")
    TeleUser getFromVal();

    InTeleMessage getMessage();

    String getData();

}
