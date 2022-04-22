package kz.botcs.telegram.dto.in;

import kz.botcs.telegram.dto.RestDTO;

public class PhotoSize implements RestDTO {
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
