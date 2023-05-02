package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kz.botcs.telegram.dto.RestDTO;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as=ImmutableInUpdateResult.class)
public interface InUpdateResult extends RestDTO {
    Boolean getOk();

    List<InUpdate> getResult();
}
