package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

public class Update implements RestDTO {
    private Integer updateId;
    private Message message;
    private CallbackQuery callbackQuery;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public void setCallbackQuery(CallbackQuery callbackQuery) {
        this.callbackQuery = callbackQuery;
    }
}
