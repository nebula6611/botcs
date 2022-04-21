package kz.botcs.builder;

public class MessageBuilder {
    public static OutResponseBuilder ofOutResponse() {
        return new OutResponseBuilder();
    }

    public static TextOutMessageBuilder ofTextOutMessage() {
        return new TextOutMessageBuilder();
    }
}
