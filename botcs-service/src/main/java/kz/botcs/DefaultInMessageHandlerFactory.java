package kz.botcs;

import kz.botcs.chatbot.Chatbot;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.point.*;
import kz.botcs.userdata.UserDataContainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultInMessageHandlerFactory implements InMessageHandlerFactory {
    private final PointContainer pointContainer;
    private final UserDataContainer userDataContainer;
    private final PointHandlerContainer pointHandlerContainer;

    public DefaultInMessageHandlerFactory(
            PointContainer pointContainer,
            UserDataContainer userDataContainer,
            PointHandlerContainer pointHandlerContainer) {
        this.pointContainer = pointContainer;
        this.userDataContainer = userDataContainer;
        this.pointHandlerContainer = pointHandlerContainer;
    }

    @Override
    public <C extends Chatbot<I>, I> InMessageHandler<I> createHandler(C chatbot) {
        return chatbotInMessage -> {
            InMessage inMessage = chatbot.toInMessage(chatbotInMessage);
            UserData userData = userDataContainer.get(chatbot.getId(), inMessage.getFrom().getId());
            OutResponse outResponse = getResponse(chatbot.getId(), inMessage);

            SystemUserData systemUserData = userData.get(SystemUserData.class);
            systemUserData.setStage(outResponse.getStage());

            List<OutMessage> outMessages = new ArrayList<>(outResponse.getOutMessages());
            if (outResponse.getBottomMenu() != null) {
                outMessages.add(outResponse.getBottomMenu());
            }
            chatbot.send(inMessage.getFrom().getId(), outMessages);
        };
    }

    private OutResponse getResponse(String chatbotId, InMessage inMessage) {

        for (PointHandler<?> pointHandler : pointHandlerContainer.getPointHandlers()) {
            Pair<String, String> keywordAndText = pointHandler.keywordAndText(chatbotId, inMessage);
            if (keywordAndText == null) continue;
            String keyword = keywordAndText.getKey();
            String text = keywordAndText.getValue();
            Point point = pointContainer.get(chatbotId, keyword, pointHandler.getType());
            if (point == null) continue;
            return point.execute(new PointArgs(text, inMessage));
        }

        return new OutResponse(SystemUserData.STAGE_DEFAULT,
                List.of(new TextOutMessage(null, "???", null)), null);
    }


}
