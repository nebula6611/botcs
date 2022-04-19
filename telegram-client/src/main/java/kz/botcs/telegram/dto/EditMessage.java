package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface EditMessage extends RestDTO {
    Integer getChatId();

    Integer getMessageId();

    String getText();

    String getParseMode();

    ReplyMarkup getReplyMarkup();
}
