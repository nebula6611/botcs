package kz.botcs.point;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PointContainer {
    private final Map<Key, Point> map = new ConcurrentHashMap<>();

    public Point get(String chatbotId, String keyword, Class<? extends Annotation> type) {
        return map.get(new Key(chatbotId, keyword, type));
    }

    public void put(String chatbotId, String keyword, Class<? extends Annotation> type, Point point) {
        Key key = new Key(chatbotId, keyword, type);
        if (map.containsKey(key)) {
            throw new IllegalStateException("Point already exist: " + keyword + " " + type.getSimpleName());
        }
        map.put(key, point);
    }

    public static class Key {
        private final String chatbotId;
        private final String keyword;
        private final Class<? extends Annotation> type;

        public Key(String chatbotId, String keyword, Class<? extends Annotation> type) {
            this.chatbotId = chatbotId;
            this.keyword = keyword;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return chatbotId.equals(key.chatbotId) && keyword.equals(key.keyword) && type == key.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(chatbotId, keyword, type);
        }
    }
}
