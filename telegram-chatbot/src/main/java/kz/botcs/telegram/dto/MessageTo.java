package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
public interface MessageTo extends RestDTO {
    @Nullable
    @JsonProperty("chat_id") Integer getChatId();

    String getText();

    @Nullable
    @JsonProperty("parse_mode") String getParseMode();

    @Nullable
    @JsonProperty("reply_markup") ReplyMarkup getReplyMarkup();
}
