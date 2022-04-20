package kz.botcs;

import java.util.Objects;

public class SystemUserData {
    public final static String STAGE_DEFAULT = "STAGE_DEFAULT";

    private String stage;

    public SystemUserData() {
        this.stage = STAGE_DEFAULT;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = Objects.requireNonNullElse(stage, STAGE_DEFAULT);
    }
}