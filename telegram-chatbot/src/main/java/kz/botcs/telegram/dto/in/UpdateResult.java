package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

import java.util.List;

public class UpdateResult implements RestDTO {
    private Boolean ok;
    private List<Update> result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<Update> getResult() {
        return result;
    }

    public void setResult(List<Update> result) {
        this.result = result;
    }
}
