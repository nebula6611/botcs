package kz.botcs.telegram.dto.out;

public class TextMessage extends OutMessage {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
