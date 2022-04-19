package kz.botcs;

import kz.botcs.client.Chatbot;
import kz.botcs.client.inmessage.CallbackInMessage;
import kz.botcs.client.inmessage.InMessage;
import kz.botcs.client.inmessage.TextInMessage;
import kz.botcs.point.Point;
import kz.botcs.point.PointArgs;
import kz.botcs.point.PointContainer;
import kz.botcs.userdata.UserDataContainer;
import org.springframework.stereotype.Component;

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
    public <C extends Chatbot<I, O>, I, O> InMessageHandler<C, I, O> createHandler(String clientId, C chatbot) {
        return chatbotInMessage -> {
            InMessage inMessage = chatbot.toInMessage(chatbotInMessage);
            UserData userData = userDataContainer.get(clientId, inMessage.getFrom().getId());
            String stage = userData.get(USER_DATA_STAGE_KEY, String.class);

            Point point;
            if (inMessage instanceof TextInMessage) {
                TextInMessage textInMessage = (TextInMessage) inMessage;
                point = pointContainer.get(clientId, textInMessage.getText(), PointType.COMMAND);
                if (point != null) {
                    point.execute(new PointArgs(null, inMessage));
                    return;
                }
                if (stage != null) {
                    point = pointContainer.get(clientId, stage, PointType.STAGE);
                    if (point != null) {
                        point.execute(new PointArgs(textInMessage.getText(), inMessage));
                        return;
                    }
                }
            }
            if (inMessage instanceof CallbackInMessage) {
                CallbackInMessage callbackInMessage = (CallbackInMessage) inMessage;
                point = pointContainer.get(clientId, callbackInMessage.getKeyword(), PointType.CALLBACK);
                point.execute(new PointArgs(callbackInMessage.getText(), inMessage));
            }
        };
    }
}
