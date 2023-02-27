package kz.botcs.telegram.dto.out;

import org.immutables.value.Value;

@Value.Immutable
public interface OutPhotoMessage extends OutTeleMessage {
    String getPhoto();

    String getCaption();
}

