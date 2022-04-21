package kz.nebula.daim;

import kz.botcs.OutResponse;
import kz.botcs.builder.MessageBuilder;
import kz.botcs.point.PointController;
import kz.botcs.point.handler.CommandPoint;
import kz.botcs.point.handler.DefaultTextPoint;

@PointController(DaimClient.ID)
public class HelloPoint {

    @CommandPoint(DaimClient.Command.START)
    public OutResponse start() {
        return MessageBuilder.ofOutResponse()
                .addMessage(MessageBuilder.ofTextOutMessage()
                        .text("Hello World")
                        .build()
                ).addMessage(MessageBuilder.ofTextOutMessage()
                        .text("text2")
                        .buttons().add("asdasd", "dasdasd", "sfsdfsd").build()
                        .build()
                ).bottomMenu().add("1asdasd").add("2dasdas").add("3dasdasdas").build()
                .build();
    }

    @DefaultTextPoint
    public String defaultHandler() {
        return "????????????????";
    }
}
