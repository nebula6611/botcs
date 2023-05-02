package kz.botcs.chatbot.outmessage;

import java.io.Serializable;

public class InlineButton {
    private final String title;
    private final String keyword;
    private final Object data;

    public InlineButton(String title, String keyword, Object data) {
        this.title = title;
        this.keyword = keyword;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyword() {
        return keyword;
    }

    public Object getData() {
        return data;
    }
}
