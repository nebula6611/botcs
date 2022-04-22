package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class AnswerCallbackQuery implements RestDTO {
    private String callbackQueryId;
    private String text;

    public String getCallbackQueryId() {
        return callbackQueryId;
    }

    public void setCallbackQueryId(String callbackQueryId) {
        this.callbackQueryId = callbackQueryId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
