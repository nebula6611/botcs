package kz.nebula.daim;

import kz.botcs.OutResponse;
import kz.botcs.builder.ResponseBuilder;
import kz.botcs.point.PointController;
import kz.botcs.point.handler.CommandPoint;
import kz.botcs.point.handler.DefaultTextPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@PointController(DaimClient.ID)
public class HelloPoint {

    @CommandPoint(DaimClient.Command.START)
    public OutResponse start() {
//        List<Word> words = new ArrayList<>(wordService.findAll());
//        if (words.size() < 4) {
//            return ResponseBuilder.ofText("too few words").build();
//        }
//        Collections.shuffle(words, RANDOM);
//        Word word = words.get(RANDOM.nextInt(4));
//        userData.put("WORD_ID", word.getId());
//
//        return ResponseBuilder.of()
//                .newMessage()
//                .add()
//                .bottomMenu().add("Archived").addLineBrake()
//                .add()
//                .forwardCommand("sadasdas")
//                .build();
        return null;
    }

    @DefaultTextPoint
    public String defaultHandler() {
        return "????????????????";
    }
}
