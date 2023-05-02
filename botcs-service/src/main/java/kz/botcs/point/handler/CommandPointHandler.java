package kz.botcs.point.handler;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CommandPointHandler implements PointHandler<CommandPoint> {

    @Override
    public Integer getOrder() {
        return 101;
    }

    @Override
    public Class<CommandPoint> getType() {
        return CommandPoint.class;
    }

    @Override
    public String getKeyword(CommandPoint commandPoint) {
        return commandPoint.value();
    }

    @Override
    public Pair<String, Object> keywordAndData(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof TextInMessage)) return null;
        TextInMessage textInMessage = (TextInMessage) inMessage;
        return new Pair<>(textInMessage.getText(), null);
    }


}
