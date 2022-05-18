import kz.botcs.OutResponse;
import kz.botcs.builder.ResponseBuilder;
import kz.botcs.point.PointController;
import kz.botcs.point.handler.CallbackPoint;
import kz.botcs.point.handler.CommandPoint;
import kz.botcs.point.para.CallbackMessageId;

@PointController(chatbotId = DaimClient.ID)
public class WordPoint {

    @CommandPoint(DaimClient.Word.Command.LIST)
    @CallbackPoint(DaimClient.Word.Callback.LIST)
    public OutResponse list(String data, @CallbackMessageId String callbackMessageId) {
        return ResponseBuilder.ofText("hello").build();
    }
}
