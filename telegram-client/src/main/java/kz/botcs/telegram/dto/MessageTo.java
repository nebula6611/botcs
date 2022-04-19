package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface MessageTo extends RestDTO {
    Integer getChatId();

    String getText();

    String getParseMode();

    ReplyMarkup getReplyMarkup();
}
