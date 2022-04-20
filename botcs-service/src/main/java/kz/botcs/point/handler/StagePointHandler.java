package kz.botcs.point.handler;

import kz.botcs.SystemUserData;
import kz.botcs.UserData;
import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.TextInMessage;
import kz.botcs.point.Pair;
import kz.botcs.point.PointHandler;
import kz.botcs.userdata.UserDataContainer;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class StagePointHandler implements PointHandler {

    private final UserDataContainer userDataContainer;

    public StagePointHandler(UserDataContainer userDataContainer) {
        this.userDataContainer = userDataContainer;
    }

    @Override
    public Integer getOrder() {
        return 102;
    }

    @Override
    public Class<? extends Annotation> getType() {
        return StagePoint.class;
    }

    @Override
    public Pair<String, String> keywordAndText(String chatbotId, InMessage inMessage) {
        if (!(inMessage instanceof TextInMessage)) return null;
        TextInMessage textInMessage = (TextInMessage) inMessage;
        UserData userData = userDataContainer.get(chatbotId, inMessage.getFrom().getId());
        SystemUserData systemUserData = userData.get(SystemUserData.class);
        String stage = systemUserData.getStage();
        if (stage == null) return null;
        return new Pair<>(systemUserData.getStage(), textInMessage.getText());
    }
}

