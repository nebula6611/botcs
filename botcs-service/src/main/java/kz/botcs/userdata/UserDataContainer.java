package kz.botcs.userdata;

import kz.botcs.UserData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserDataContainer {
    private final Map<Key, UserData> map = new ConcurrentHashMap<>();

    public UserData get(String chatbotId, String userId) {
        Key key = new Key(chatbotId, userId);
        UserData userData = map.get(key);
        if (userData == null) {
            userData = new DefaultUserData();
            map.put(key, userData);
        }
        return userData;
    }

    private static class Key {
        private final String chatbotId;
        private final String userId;

        private Key(String chatbotId, String userId) {
            this.chatbotId = chatbotId;
            this.userId = userId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return chatbotId.equals(key.chatbotId) && userId.equals(key.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(chatbotId, userId);
        }
    }
}
