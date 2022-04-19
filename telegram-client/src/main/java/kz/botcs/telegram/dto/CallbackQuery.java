package kz.botcs.telegram.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableCallbackQuery.Builder.class)
public interface CallbackQuery extends RestDTO {
    String getId();

    User getFrom();

    Message getMessage();

    String getData();
}
