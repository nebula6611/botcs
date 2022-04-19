package kz.botcs.point;

import kz.botcs.PointType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PointContainer {
    private final Map<Key, Point> map = new ConcurrentHashMap<>();

    public Point get(String clientId, String keyword, PointType type) {
        return map.get(new Key(clientId, keyword, type));
    }

    public void put(String clientId, String keyword, PointType type, Point point) {
        Key key = new Key(clientId, keyword, type);
        if (map.containsKey(key)) {
            throw new IllegalStateException("Point already exist");
        }
        map.put(key, point);
    }

    public static class Key {
        private final String clientId;
        private final String keyword;
        private final PointType type;

        public Key(String clientId, String keyword, PointType type) {
            this.clientId = clientId;
            this.keyword = keyword;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return clientId.equals(key.clientId) && keyword.equals(key.keyword) && type == key.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(clientId, keyword, type);
        }
    }
}
