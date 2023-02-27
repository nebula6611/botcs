package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
public interface InPhotoSize extends RestDTO {
    String getFileId();
}
