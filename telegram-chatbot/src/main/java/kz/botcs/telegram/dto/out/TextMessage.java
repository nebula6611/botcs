package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class TextMessage implements RestDTO {
    private Integer chatId;
    private String text;
    private String parseMode;
    private ReplyMarkup replyMarkup;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParseMode() {
        return parseMode;
    }

    public void setParseMode(String parseMode) {
        this.parseMode = parseMode;
    }

    public ReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(ReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }
}
