package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
public interface AnswerCallbackQuery extends RestDTO {
    @JsonProperty("callback_query_id") String getCallbackQueryId();

    @Nullable String getText();
}
