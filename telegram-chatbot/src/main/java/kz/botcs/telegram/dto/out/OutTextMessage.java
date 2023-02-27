package kz.botcs.telegram.dto.out;

import org.immutables.value.Value;

@Value.Immutable
public interface OutTextMessage extends OutTeleMessage {

    public String getText();

}
