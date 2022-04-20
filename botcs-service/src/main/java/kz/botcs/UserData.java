package kz.botcs;

public interface UserData {
    <T> T get(String key, Class<T> tClass);

    void put(String key, Object value);

    default <T> T get(Class<T> tClass) {
        return get(tClass.getName(), tClass);
    }

    default void put(Object value) {
        put(value.getClass().getName(), value);
    }
}
