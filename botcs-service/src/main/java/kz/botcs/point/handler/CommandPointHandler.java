package kz.botcs.point.handler;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class CommandPointHandler implements PointHandler {

    @Override
    public Integer getOrder() {
        return 101;
    }

    @Override
    public Class<? extends Annotation> getType() {
        return CommandPoint.class;
    }

    @Override
    public Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof TextInMessage)) return null;
        TextInMessage textInMessage = (TextInMessage) inMessage;
        return new Pair<>(textInMessage.getText(), null);
    }


}
