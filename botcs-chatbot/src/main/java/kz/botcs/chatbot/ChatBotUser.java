package kz.botcs.chatbot;

public class ChatBotUser {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String username;

    public ChatBotUser(String id, String firstName, String lastName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
