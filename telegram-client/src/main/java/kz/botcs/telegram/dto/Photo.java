package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface Photo extends RestDTO {
    Integer getChatId();

    String getPhotoId();

    String getText();

    String getParseMode();

    ReplyMarkup getReplyMarkup();
}
