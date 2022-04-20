package kz.botcs.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

@Value.Immutable
public interface AnswerCallbackQuery extends RestDTO {
    @JsonProperty("callback_query_id") String getCallbackQueryId();

    String getText();
}
