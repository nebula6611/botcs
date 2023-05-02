package kz.botcs;

import java.lang.annotation.Annotation;

public class Forward {
    private final Class<? extends Annotation> type;
    private final String keyword;
    private final Object data;

    public Forward(Class<? extends Annotation> type, String keyword, Object data) {
        this.type = type;
        this.keyword = keyword;
        this.data = data;
    }

    public Class<? extends Annotation> getType() {
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public Object getData() {
        return data;
    }
}
