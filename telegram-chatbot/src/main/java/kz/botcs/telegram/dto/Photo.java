package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
public interface Photo extends RestDTO {
    @JsonProperty("chat_id") Integer getChatId();

    @JsonProperty("photo_id") String getPhotoId();

    String getText();

    @JsonProperty("parse_mode") String getParseMode();

    @JsonProperty("reply_markup") ReplyMarkup getReplyMarkup();
}
