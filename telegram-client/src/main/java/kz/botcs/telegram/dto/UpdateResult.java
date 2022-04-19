package kz.botcs.telegram.dto;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable

public interface UpdateResult extends RestDTO {
    Boolean getOk();

    List<Update> getResult();
}
