package kz.botcs.builder;


public class ResponseBuilder {
    public static OutResponseBuilder of() {
        return new OutResponseBuilder();
    }

    public static TextOutMessageBuilder ofMessage() {
        return new TextOutMessageBuilder();
    }

    public static OutResponseBuilder ofText(String text) {
        return of().newMessage().text(text).add();
    }
}
