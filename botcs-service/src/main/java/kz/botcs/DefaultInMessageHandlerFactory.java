package kz.botcs;

import kz.botcs.client.Chatbot;
import kz.botcs.client.inmessage.CallbackInMessage;
import kz.botcs.client.inmessage.InMessage;
import kz.botcs.client.inmessage.TextInMessage;
import kz.botcs.client.outmessage.OutMessage;
import kz.botcs.client.outmessage.TextOutMessage;
import kz.botcs.point.Point;
import kz.botcs.point.PointArgs;
import kz.botcs.point.PointContainer;
import kz.botcs.userdata.UserDataContainer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultInMessageHandlerFactory implements InMessageHandlerFactory {
    private static final String USER_DATA_STAGE_KEY = "USER_DATA_STAGE";

    private final PointContainer pointContainer;
    private final UserDataContainer userDataContainer;

    public DefaultInMessageHandlerFactory(
            PointContainer pointContainer,
            UserDataContainer userDataContainer) {
        this.pointContainer = pointContainer;
        this.userDataContainer = userDataContainer;
    }

    @Override
    public <C extends Chatbot<I>, I> InMessageHandler<C, I> createHandler(String clientId, C chatbot) {
        return chatbotInMessage -> {
            InMessage inMessage = chatbot.toInMessage(chatbotInMessage);
            UserData userData = userDataContainer.get(clientId, inMessage.getFrom().getId());
            String stage = userData.get(USER_DATA_STAGE_KEY, String.class);
            OutResponse outResponse = getResponse(clientId, inMessage, stage);

            userData.put(USER_DATA_STAGE_KEY, outResponse.getStage());
            for (OutMessage outMessage : outResponse.getOutMessages()) {
                chatbot.send(outMessage);
            }
        };
    }

    private OutResponse getResponse(String clientId, InMessage inMessage, String stage) {

        if (inMessage instanceof TextInMessage) {
            TextInMessage textInMessage = (TextInMessage) inMessage;
            Point point = pointContainer.get(clientId, textInMessage.getText(), PointType.COMMAND);
            if (point != null) {
                return point.execute(new PointArgs(null, inMessage));
            }
            if (stage != null) {
                point = pointContainer.get(clientId, stage, PointType.STAGE);
                if (point != null) {
                    return point.execute(new PointArgs(textInMessage.getText(), inMessage));
                }
            }
        }
        if (inMessage instanceof CallbackInMessage) {
            CallbackInMessage callbackInMessage = (CallbackInMessage) inMessage;
            Point point = pointContainer.get(clientId, callbackInMessage.getKeyword(), PointType.CALLBACK);
            return point.execute(new PointArgs(callbackInMessage.getText(), inMessage));
        }
        return new OutResponse(null, List.of(new TextOutMessage(null, "???")));
    }
}
