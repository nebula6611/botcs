package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class DeleteMessage implements RestDTO {
    private Integer chatId;
    private Integer messageId;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
