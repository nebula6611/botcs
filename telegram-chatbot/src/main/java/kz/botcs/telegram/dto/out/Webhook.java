package kz.botcs.telegram.dto.out;

import kz.botcs.telegram.dto.RestDTO;

public class Webhook implements RestDTO {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
