package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class KeyboardButton implements RestDTO {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
