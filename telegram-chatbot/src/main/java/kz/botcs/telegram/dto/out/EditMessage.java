package kz.botcs.telegram.dto.out;

public class EditMessage extends TextMessage {
    private Integer messageId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
