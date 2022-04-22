package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;
import kz.botcs.telegram.dto.User;

public class CallbackQuery implements RestDTO {
    private String id;
    private User from;
    private Message message;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
