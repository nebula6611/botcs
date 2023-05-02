package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
public interface OutAnswerCallbackQuery extends RestDTO {
    String getCallbackQueryId();

    @Nullable
    String getText();
}
