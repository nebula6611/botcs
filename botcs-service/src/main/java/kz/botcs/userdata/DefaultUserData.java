package kz.botcs.userdata;


import kz.botcs.UserData;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultUserData implements UserData {
    private final Map<String, Object> map = new ConcurrentHashMap<>();

    public <T> T get(String key, Class<T> tClass) {
        T value = (T) map.get(key);
        if (value == null) {
            try {
                Constructor<T> ctor = tClass.getConstructor();
                value = ctor.newInstance();
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("needs empty constructor", e);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            map.put(key, value);
        }
        return value;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }
}
