package web.multiform;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class PhotoUploader {
    private Part photo;
    private String name;

    public PhotoUploader(Part photo, String name) {
        this.photo = photo;
        this.name = name;
    }

    public String upload() throws IOException {
        File file = new File("src/main/webapp/photos");
        if (checkImage()) {
            file.mkdirs();
            file = new File(file.getAbsolutePath() + File.separator + name + ".jpg");
            file.createNewFile();
            photo.write(file.getAbsolutePath());
            return name;
        } else {
            return null;
        }
    }

    private boolean checkImage() {
        return photo != null && photo.getSize() > 0 && photo.getContentType().startsWith("image/");
    }
}
