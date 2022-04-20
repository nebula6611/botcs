package kz.botcs.point.handler;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import org.springframework.stereotype.Component;

@Component
public class DefaultTextPointHandler implements PointHandler<DefaultTextPoint> {

    private static final String KEYWORD = "keyword";

    @Override
    public Integer getOrder() {
        return 103;
    }

    @Override
    public Class<DefaultTextPoint> getType() {
        return DefaultTextPoint.class;
    }

    @Override
    public String getKeyword(DefaultTextPoint annotation) {
        return KEYWORD;
    }

    @Override
    public Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof TextInMessage)) return null;
        TextInMessage textInMessage = (TextInMessage) inMessage;
        return new Pair<>(KEYWORD, textInMessage.getText());
    }
}

