package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;
import org.jetbrains.annotations.Nullable;

public interface OutTeleMessage extends RestDTO {
    Integer getChatId();

    @Nullable
    Integer getMessageId();

    String getParseMode();

    @Nullable
    OutReplyMarkup getReplyMarkup();
}