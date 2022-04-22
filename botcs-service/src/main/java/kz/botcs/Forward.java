package kz.botcs;

import java.lang.annotation.Annotation;

public class Forward {
    private final Class<? extends Annotation> type;
    private final String keyword;
    private final String text;

    public Forward(Class<? extends Annotation> type, String keyword, String text) {
        this.type = type;
        this.keyword = keyword;
        this.text = text;
    }

    public Class<? extends Annotation> getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getText() {
        return text;
    }
}
