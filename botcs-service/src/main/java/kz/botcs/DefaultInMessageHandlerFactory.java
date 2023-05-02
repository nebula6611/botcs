package kz.botcs;

import kz.botcs.builder.ResponseBuilder;
import kz.botcs.chatbot.Chatbot;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.OutMessage;
import kz.botcs.point.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultInMessageHandlerFactory implements InMessageHandlerFactory {
    private final ObjectProvider<UserData> userDataProvider;
    private final PointContainer pointContainer;
    private final PointHandlerContainer pointHandlerContainer;

    public DefaultInMessageHandlerFactory(
            ObjectProvider<UserData> userDataProvider,
            PointContainer pointContainer,
            PointHandlerContainer pointHandlerContainer) {
        this.userDataProvider = userDataProvider;
        this.pointContainer = pointContainer;
        this.pointHandlerContainer = pointHandlerContainer;
    }

    @Override
    public <C extends Chatbot<I>, I> InMessageHandler<I> createHandler(C chatbot) {
        return chatbotInMessage -> {
            InMessage inMessage = chatbot.toInMessage(chatbotInMessage);
            UserIdThreadLocal.set(inMessage.getFrom().getId());

            UserData userData = userDataProvider.getObject();
            List<OutResponse> outResponses = getResponses(chatbot.getId(), inMessage);

            List<OutMessage> outMessages = new ArrayList<>();
            for (OutResponse outResponse : outResponses) {
                userData.setStage(outResponse.getStage());

                outMessages.addAll(outResponse.getOutMessages());
                if (outResponse.getBottomMenuOutMessage() != null) {
                    outMessages.add(outResponse.getBottomMenuOutMessage());
                }
            }

            chatbot.send(inMessage.getFrom().getId(), outMessages);
            UserIdThreadLocal.clear();
        };
    }

    private List<OutResponse> getResponses(String chatbotId, InMessage inMessage) {
        List<OutResponse> outResponses = new ArrayList<>();
        OutResponse outResponse = getResponse(chatbotId, inMessage);
        while (true) {
            outResponses.add(outResponse);
            if (outResponse.getForward() == null) break;
            outResponse = getResponse(chatbotId, inMessage, outResponse.getForward());
        }
        return outResponses;
    }

    private OutResponse getResponse(String chatbotId, InMessage inMessage) {

        for (PointHandler<?> pointHandler : pointHandlerContainer.getPointHandlers()) {
            Pair<String, Object> keywordAndText = pointHandler.keywordAndData(chatbotId, inMessage);
            if (keywordAndText == null) continue;
            String keyword = keywordAndText.getKey();
            Object data = keywordAndText.getValue();
            Point point = pointContainer.get(chatbotId, keyword, pointHandler.getType());
            if (point == null) continue;
            return point.execute(new PointArgs(chatbotId, data, inMessage));
        }

        return ResponseBuilder.ofText("point not found").build();
    }

    private OutResponse getResponse(String chatbotId, InMessage inMessage, Forward forward) {
        String keyword = forward.getKeyword();
        Object data = forward.getData();
        Point point = pointContainer.get(chatbotId, keyword, forward.getType());
        if (point == null) return ResponseBuilder.ofText("forward point not found").build();
        return point.execute(new PointArgs(chatbotId, data, inMessage));
    }


}
