package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutablePhotoSize.Builder.class)
public interface PhotoSize {
    String getFileId();
}
