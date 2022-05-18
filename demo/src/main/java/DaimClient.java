public interface DaimClient {
    String ID = "daim";

    interface Command {
        String START = "/start";
    }

    interface Word {
        interface Command {
            String LIST = "/words";
            String ADD = "/add_word";
        }

        interface Callback {
            String LIST = "Word/LIST";
            String FIND = "Word/FIND";
            String ADD = "Word/ADD";
            String GET = "Word/GET";
            String CHANGE_ARCHIVE = "Word/CHANGE_ARCHIVE";
            String DELETE = "Word/DELETE_WORD";
        }

        interface State {
            String FIND = "Word/FIND";
            String ADD = "Word/AND";
            String ADD2 = "Word/AND2";
            String ADD3 = "Word/AND3";
        }
    }

    interface WordGame {
        interface Command {
            String ASK = "/word_game";
        }

        interface Callback {
            String ASK = "WordGame/LIST_WORDS";
            String ANSWER = "WordGame/ANSWER";
        }
    }

}
