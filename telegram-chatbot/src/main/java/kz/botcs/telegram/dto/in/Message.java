package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;
import kz.botcs.telegram.dto.User;

import java.util.List;

public class Message implements RestDTO {
    private Integer messageId;
    private User from;
    private String text;
    private List<PhotoSize> photo;
    private Message replyToMessage;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PhotoSize> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoSize> photo) {
        this.photo = photo;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public void setReplyToMessage(Message replyToMessage) {
        this.replyToMessage = replyToMessage;
    }
}
