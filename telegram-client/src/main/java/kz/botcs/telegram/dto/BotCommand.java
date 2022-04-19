package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface BotCommand extends RestDTO {
    String getCommand();

    String getDescription();
}
