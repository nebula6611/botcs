package kz.botcs.telegram.dto.out;

public class PhotoMessage extends OutMessage {
    private String photo;
    private String caption;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
