package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
public interface EditMessage extends RestDTO {
    @JsonProperty("chat_id") Integer getChatId();

    @JsonProperty("message_id") Integer getMessageId();

    String getText();

    @JsonProperty("parse_mode") String getParseMode();

    @JsonProperty("reply_markup") ReplyMarkup getReplyMarkup();
}
