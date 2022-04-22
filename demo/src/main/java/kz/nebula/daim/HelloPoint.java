package kz.nebula.daim;

import kz.botcs.OutResponse;
import kz.botcs.builder.ResponseBuilder;
import kz.botcs.point.PointController;
import kz.botcs.point.handler.CommandPoint;
import kz.botcs.point.handler.DefaultTextPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PointController(chatbotId = DaimClient.ID)
public class HelloPoint {

    @CommandPoint(DaimClient.Command.START)
    public OutResponse start() {
        return ResponseBuilder.of().newMessage()
                .text("Hello World").buttons()
                .add("dasdas", "sadasdas", "dasdas")
                .end().add().build();
    }

    @DefaultTextPoint
    public String defaultHandler() {
        return "????????????????";
    }
}
