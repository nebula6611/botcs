package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdateResult.Builder.class)
public interface UpdateResult extends RestDTO {
    Boolean getOk();

    List<Update> getResult();
}
