package kz.botcs.telegram.dto;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface BotCommands extends RestDTO {
    List<BotCommand> getCommands();
}
