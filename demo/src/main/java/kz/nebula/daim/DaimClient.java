package kz.nebula.daim;

public interface DaimClient {
    String ID = "daim";

    interface Command {
        String START = "/start";
    }
}
