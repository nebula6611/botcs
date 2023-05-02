package kz.botcs.telegram.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as=ImmutableInPhotoSize.class)
public interface InPhotoSize extends RestDTO {
    @JsonProperty("file_id")
    String getFileId();
}
