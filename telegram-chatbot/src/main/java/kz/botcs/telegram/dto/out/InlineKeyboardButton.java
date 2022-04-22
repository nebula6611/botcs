package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class InlineKeyboardButton implements RestDTO {
    private String text;
    private String callbackData;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
}
