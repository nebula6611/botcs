package kz.botcs.telegram.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface Webhook {
    String getUrl();
}
