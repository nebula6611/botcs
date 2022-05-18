package kz.botcs.point;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("point")
public class UserData {
    public final static String STAGE_DEFAULT = "STAGE_DEFAULT";

    private String stage;

    public UserData() {
        this.stage = STAGE_DEFAULT;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = Objects.requireNonNullElse(stage, STAGE_DEFAULT);
    }
}
