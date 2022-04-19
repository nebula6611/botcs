package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface AnswerCallbackQuery extends RestDTO {
    String getCallbackQueryId();

    String getText();
}
