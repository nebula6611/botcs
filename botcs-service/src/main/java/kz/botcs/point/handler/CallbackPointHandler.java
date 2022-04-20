package kz.botcs.point.handler;

import kz.botcs.chatbot.CallbackInMessage;
import kz.botcs.chatbot.InMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import org.springframework.stereotype.Component;

@Component
public class CallbackPointHandler implements PointHandler<CallbackPoint> {

    @Override
    public Integer getOrder() {
        return 100;
    }

    @Override
    public Class<CallbackPoint> getType() {
        return CallbackPoint.class;
    }

    @Override
    public String getKeyword(CallbackPoint callbackPoint) {
        return callbackPoint.value();
    }

    @Override
    public Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof CallbackInMessage)) return null;
        CallbackInMessage callbackInMessage = (CallbackInMessage) inMessage;
        return new Pair<>(callbackInMessage.getKeyword(), callbackInMessage.getText());
    }

}
