package Manager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageManager {
    private static ImageManager imageManager = new ImageManager();

    private BufferedImage[] headPortraits = new BufferedImage[] {

    };

    private ImageManager() {}

    public static ImageManager getDefaultImageManager() {
        return imageManager;
    }


}

