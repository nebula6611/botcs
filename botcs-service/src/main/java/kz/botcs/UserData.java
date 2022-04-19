package kz.botcs;

public interface UserData {
    <T> T get(String key, Class<T> tClass);

    void put(String key, Object value);
}
