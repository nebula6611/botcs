package kz.botcs.point.handler;

import kz.botcs.point.UserData;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class StagePointHandler implements PointHandler<StagePoint> {

    private final ObjectProvider<UserData> userDataProvider;

    public StagePointHandler(ObjectProvider<UserData> userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    @Override
    public Integer getOrder() {
        return 102;
    }

    @Override
    public Class<StagePoint> getType() {
        return StagePoint.class;
    }

    @Override
    public String getKeyword(StagePoint stagePoint) {
        return stagePoint.value();
    }

    @Override
    public Pair<String, Object> keywordAndData(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof TextInMessage)) return null;
        TextInMessage textInMessage = (TextInMessage) inMessage;
        String stage = userDataProvider.getObject().getStage();
        if (stage == null) return null;
        return new Pair<>(stage, textInMessage.getText());
    }
}

