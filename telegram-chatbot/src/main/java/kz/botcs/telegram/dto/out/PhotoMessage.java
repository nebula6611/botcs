package kz.botcs.telegram.dto.out;

public class PhotoMessage extends TextMessage {
    private String photoId;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }
}
