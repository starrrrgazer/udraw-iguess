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

    public Image getFrameBg() {
        return FileControl.getImage("background/frame_bg.jpg");
    }

    public Image getTitleBg() {
        return FileControl.getImage("background/title_bg.jpg");
    }

    public Image getViewBg() {
        return FileControl.getImage("background/view_bg.jpg");
    }

    public Image[] getMinimizeImages() {
        Image[] minimizeImages = new Image[] {
                FileControl.getImage("background/minimize_normal.png"),
                FileControl.getImage("background/minimize_rollover.png"),
                FileControl.getImage("background/minimize_pressed.png")
        };
        return minimizeImages;
    }

    public Image[] getMaximizeImages() {
        Image[] maximizeImages = new Image[] {
                FileControl.getImage("background/maximize_normal.png"),
                FileControl.getImage("background/maximize_rollover.png"),
                FileControl.getImage("background/maximize_pressed.png")
        };
        return maximizeImages;
    }

    public Image[] getCloseImages() {
        Image[] closeImages = new Image[] {
                FileControl.getImage("background/close_normal.png"),
                FileControl.getImage("background/close_rollover.png"),
                FileControl.getImage("background/close_pressed.png")
        };
        return closeImages;
    }

    public Image getBrushImage() {
        return FileControl.getImage("brush.png");
    }

    public Image getEraserImage() {
        return FileControl.getImage("eraser.png");
    }

    public Image getClearImage() {
        return FileControl.getImage("clear.png");
    }

    public Image getCancelImage() {
        return FileControl.getImage("cancel1.png");
    }

}

