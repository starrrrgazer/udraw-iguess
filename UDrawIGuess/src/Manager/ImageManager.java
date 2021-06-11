package Manager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片管理类，方便管理用到的所有图片资源
 */
public class ImageManager {
    private static ImageManager imageManager = new ImageManager();

    private ImageManager() {}

    /**
     * 单例模式的引用
     * @return ImageManager，单例模式
     */
    public static ImageManager getDefaultImageManager() {
        return imageManager;
    }

    /**
     * 获取标题背景图片
     * @return Image
     */
    public Image getTitleBg() {
        return FileControl.getImage("background/title_bg.jpg");
    }

    /**
     * 获取界面底部图片
     * @return Image
     */
    public Image getViewBg() {
        return FileControl.getImage("background/view_bg.png");
    }

    /**
     * 获取最小化按钮图片
     * @return  Image[]
     */
    public Image[] getMinimizeImages() {
        Image[] minimizeImages = new Image[] {
                FileControl.getImage("background/minimize_normal.png"),
                FileControl.getImage("background/minimize_rollover.png"),
                FileControl.getImage("background/minimize_pressed.png")
        };
        return minimizeImages;
    }

    /**
     * 获取最大化按钮图片
      * @return Image[]
     */
    public Image[] getMaximizeImages() {
        Image[] maximizeImages = new Image[] {
                FileControl.getImage("background/maximize_normal.png"),
                FileControl.getImage("background/maximize_rollover.png"),
                FileControl.getImage("background/maximize_pressed.png")
        };
        return maximizeImages;
    }

    /**
     * 获取关闭按钮图片
     * @return image[]
     */
    public Image[] getCloseImages() {
        Image[] closeImages = new Image[] {
                FileControl.getImage("background/close_normal.png"),
                FileControl.getImage("background/close_rollover.png"),
                FileControl.getImage("background/close_pressed.png")
        };
        return closeImages;
    }

    /**
     * 头像数组
     */
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

    /**
     * 获取头像数组中的图片
     * @param i
     * 头像数组的索引
     * @return bufferedImage
     */
    public BufferedImage getHeadPortraits(int i) {
        return headPortraits[i];
    }

    /**
     * 获取头像选择框的图片
     * @return  Image
     */
    public Image getHeadBg() {
        return getConcaveMiniBg();
    }
    private Image getConcaveMiniBg() {
        return FileControl.getImage("background/concave_mini_bg.png");
    }

    private Image getConvexBg() {
        return FileControl.getImage("background/convex_bg.png");
    }
    private Image getConvexMiniBg() {
        return FileControl.getImage("background/convex_mini_bg.png");
    }

    /**
     * 获取输入文本框的图片
     * @return image
     */
    public Image getInputFieldBg() {
        return FileControl.getImage("background/input_field_bg.png");
    }

    /**
     * 获取按钮图片组
     * @return image[]
     */
    public Image[] getButtonBgs() {
        Image[] buttonBgs = new Image[] {
                getConvexMiniBg(),
                getConcaveMiniBg()
        };
        return buttonBgs;
    }

    /**
     * 获取跳转按钮图片
     * @return image
     */
    public Image getJumpButtonBg() {
        return FileControl.getImage("background/jump_button_bg1.png");
    }

    /**
     * 获取背景图片
     * @return image
     */
    public Image getBoardBg() {
        return FileControl.getImage("background/board_bg3.jpg");
    }

    private Image getConcaveCircleBg() {
        return FileControl.getImage("background/concave_circle_bg.png");
    }

    /**
     * 获取ip连接图片
     * @return image
     */
    public Image getIpConnectBg() {
        return getConvexBg();
    }

    /**
     * 获取计时图片
     * @return image
     */
    public Image getCountdownBg() {
        return getConcaveCircleBg();
    }

    /**
     * 获取颜料图片
     * @return image
     */
    public Image getPigmentBg() {
        return getConcaveMiniBg();
    }

    /**
     * 获取笔粗细图片
     * @return image
     */
    public Image getThicknessChosenBg() {
        return getConcaveMiniBg();
    }

    /**
     * 获取选择笔触类型图片
     * @return image
     */
    public Image getBrushTypeChosenBg() {
        return getConcaveMiniBg();
    }

    /**
     * 获取清除图片边角图片
     * @return image
     */
    public Image getCanvasClearBg() {
        return getConvexMiniBg();
    }

    /**
     * 获取笔触图片
     * @return image
     */
    public Image getBrushIcon() {
        return FileControl.getImage("brush.png");
    }

    /**
     * 获取橡皮图片
     * @return image
     */
    public Image getEraserIcon() {
        return FileControl.getImage("eraser.png");
    }

    /**
     * 获取清除图片
     * @return
     */
    public Image getClearIcon() {
        return FileControl.getImage("clear.png");
    }

    /**
     * 获取光标类型图片组
     * @return  image[]
     */
    public Image[] getCursorIcon() {
        Image[] cursorIcon = new Image[] {
                FileControl.getImage("brush.png"),
                FileControl.getImage("eraser.png")
        };
        return cursorIcon;
    }

    /**
     * 获取聊天滚动条图片
     * @return image
     */
    public Image getScrollBarBg() {
        return FileControl.getImage("background/scroll_bar_bg.png");
    }

    /**
     * 获取发言图片
     * @return image
     */
    public Image getSpeakingBg() {
        return FileControl.getImage("background/speaking_bg.png");
    }
}

