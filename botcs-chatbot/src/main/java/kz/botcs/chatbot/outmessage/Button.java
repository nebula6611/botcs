package kz.botcs.chatbot.outmessage;

public class Button {
    private final String title;
    private final String keyword;
    private final String text;

    public Button(String title, String keyword, String text) {
        this.title = title;
        this.keyword = keyword;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getText() {
        return text;
    }
}
