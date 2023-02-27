package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public interface OutTeleMessage extends RestDTO {
    Integer getChatId();

    Integer getMessageId();

    String getParseMode();

    OutReplyMarkup getReplyMarkup();
}