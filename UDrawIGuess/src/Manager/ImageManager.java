package Manager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageManager {
    private static ImageManager imageManager = new ImageManager();

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
        return FileControl.getImage("background/view_bg.png");
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

    //获取画板工具
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

    //获取头像
    private BufferedImage[] headPortraits = new BufferedImage[] {
            FileControl.getBufferedImage("background/concave_mini_bg.png"),
            FileControl.getBufferedImage("avatar/01.jpg"),
            FileControl.getBufferedImage("avatar/02.jpg"),
            FileControl.getBufferedImage("avatar/03.jpg"),
            FileControl.getBufferedImage("avatar/04.jpg"),
            FileControl.getBufferedImage("avatar/05.jpg"),
            FileControl.getBufferedImage("avatar/06.jpg"),
            FileControl.getBufferedImage("avatar/07.jpg"),
            FileControl.getBufferedImage("avatar/08.jpg"),
            FileControl.getBufferedImage("avatar/09.jpg"),
            FileControl.getBufferedImage("avatar/10.jpg"),
            FileControl.getBufferedImage("avatar/11.jpg"),
            FileControl.getBufferedImage("avatar/12.jpg"),
            FileControl.getBufferedImage("avatar/13.jpg"),
            FileControl.getBufferedImage("avatar/14.jpg"),
            FileControl.getBufferedImage("background/convex_mini_bg.png")
    };
    public BufferedImage getHeadPortraits(int i) {
        return headPortraits[i];
    }
    public Image getHeadBg() {
        return getConcaveMiniBg();
    }
    private Image getConcaveMiniBg() {
        return FileControl.getImage("background/concave_mini_bg.png");
    }
    private Image getConcaveBg() {
        return FileControl.getImage("background/concave_bg.png");
    }
    private Image getConvexBg() {
        return FileControl.getImage("background/convex_bg.png");
    }
    private Image getConvexMiniBg() {
        return FileControl.getImage("background/convex_mini_bg.png");
    }



    //inputfield
    public Image getInputFieldBg() {
        return FileControl.getImage("background/input_field_bg.png");
    }
    //button
    public Image[] getButtonBgs() {
        Image[] buttonBgs = new Image[] {
                getConvexMiniBg(),
                getConcaveMiniBg()
        };
        return buttonBgs;
    }
    public Image getJumpButtonBg() {
        return FileControl.getImage("background/jump_button_bg1.png");
    }
    public Image getBoardBg() {
        return FileControl.getImage("background/board_bg3.jpg");
    }

    private Image getConvexCircleBg() {
        return FileControl.getImage("background/convex_circle_bg.png");
    }

    private Image getConcaveCircleBg() {
        return FileControl.getImage("background/concave_circle_bg.png");
    }

    public Image getIpConnectBg() {
        return getConvexBg();
    }

    public Image getCountdownBg() {
        return getConcaveCircleBg();
    }

    public Image getPigmentBg() {
        return getConcaveMiniBg();
    }

    public Image getThicknessChosenBg() {
        return getConcaveMiniBg();
    }

    public Image getBrushTypeChosenBg() {
        return getConcaveMiniBg();
    }

    public Image getCanvasClearBg() {
        return getConvexMiniBg();
    }

    public Image getBrushIcon() {
        return FileControl.getImage("brush.png");
    }

    public Image getEraserIcon() {
        return FileControl.getImage("eraser.png");
    }

    public Image getClearIcon() {
        return FileControl.getImage("clear.png");
    }

    public Image[] getCursorIcon() {
        Image[] cursorIcon = new Image[] {
                FileControl.getImage("brush.png"),
                FileControl.getImage("eraser.png")
        };
        return cursorIcon;
    }

    public Image getScrollBarBg() {
        return FileControl.getImage("background/scroll_bar_bg.png");
    }

    public Image getSpeakingBg() {
        return FileControl.getImage("background/speaking_bg.png");
    }
}

