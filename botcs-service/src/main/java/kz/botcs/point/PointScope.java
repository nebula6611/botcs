package kz.botcs.point;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PointScope implements Scope {
    private final Map<Key, Object> instances = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object object = resolveContextualObject(name);
        if (object != null) {
            return object;
        }
        object = objectFactory.getObject();
        instances.put(new Key(getCurrentUserId(), name), object);
        return object;
    }

    @Override
    public Object remove(String name) {
        return instances.remove(new Key(getCurrentUserId(), name));
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object resolveContextualObject(String key) {
        return instances.get(new Key(getCurrentUserId(), key));
    }

    @Override
    public String getConversationId() {
        return "PointScope";
    }

    private String getCurrentUserId() {
        String userId = UserIdThreadLocal.get();
        return Objects.requireNonNull(userId);
    }

    private static class Key {
        private final String userId;
        private final String name;

        public Key(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return userId.equals(key.userId) && name.equals(key.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, name);
        }
    }
}
