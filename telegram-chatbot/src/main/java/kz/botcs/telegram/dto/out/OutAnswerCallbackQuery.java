package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;

@Value.Immutable
public interface OutAnswerCallbackQuery extends RestDTO {
    String getCallbackQueryId();

    String getText();
}
