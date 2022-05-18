package kz.botcs.point;

public class UserIdThreadLocal {
    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static String get() {
        return userId.get();
    }

    public static void set(String userId) {
        UserIdThreadLocal.userId.set(userId);
    }

    public static void clear() {
        userId.remove();
    }

    private UserIdThreadLocal() {
    }
}
