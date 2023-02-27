package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

import java.util.List;

import org.immutables.value.Value;

@Value.Immutable
public interface InUpdateResult extends RestDTO {
    Boolean getOk();

    List<InUpdate> getResult();
}
