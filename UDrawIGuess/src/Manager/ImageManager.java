package Manager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ͼƬ�����࣬��������õ�������ͼƬ��Դ
 */
public class ImageManager {
    private static ImageManager imageManager = new ImageManager();

    private ImageManager() {}

    /**
     * ����ģʽ������
     * @return ImageManager������ģʽ
     */
    public static ImageManager getDefaultImageManager() {
        return imageManager;
    }

    /**
     * ��ȡ���ⱳ��ͼƬ
     * @return Image
     */
    public Image getTitleBg() {
        return FileControl.getImage("background/title_bg.jpg");
    }

    /**
     * ��ȡ����ײ�ͼƬ
     * @return Image
     */
    public Image getViewBg() {
        return FileControl.getImage("background/view_bg.png");
    }

    /**
     * ��ȡ��С����ťͼƬ
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
     * ��ȡ��󻯰�ťͼƬ
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
     * ��ȡ�رհ�ťͼƬ
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
     * ͷ������
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
     * ��ȡͷ�������е�ͼƬ
     * @param i
     * ͷ�����������
     * @return bufferedImage
     */
    public BufferedImage getHeadPortraits(int i) {
        return headPortraits[i];
    }

    /**
     * ��ȡͷ��ѡ����ͼƬ
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
     * ��ȡ�����ı����ͼƬ
     * @return image
     */
    public Image getInputFieldBg() {
        return FileControl.getImage("background/input_field_bg.png");
    }

    /**
     * ��ȡ��ťͼƬ��
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
     * ��ȡ��ת��ťͼƬ
     * @return image
     */
    public Image getJumpButtonBg() {
        return FileControl.getImage("background/jump_button_bg1.png");
    }

    /**
     * ��ȡ����ͼƬ
     * @return image
     */
    public Image getBoardBg() {
        return FileControl.getImage("background/board_bg3.jpg");
    }

    private Image getConcaveCircleBg() {
        return FileControl.getImage("background/concave_circle_bg.png");
    }

    /**
     * ��ȡip����ͼƬ
     * @return image
     */
    public Image getIpConnectBg() {
        return getConvexBg();
    }

    /**
     * ��ȡ��ʱͼƬ
     * @return image
     */
    public Image getCountdownBg() {
        return getConcaveCircleBg();
    }

    /**
     * ��ȡ����ͼƬ
     * @return image
     */
    public Image getPigmentBg() {
        return getConcaveMiniBg();
    }

    /**
     * ��ȡ�ʴ�ϸͼƬ
     * @return image
     */
    public Image getThicknessChosenBg() {
        return getConcaveMiniBg();
    }

    /**
     * ��ȡѡ��ʴ�����ͼƬ
     * @return image
     */
    public Image getBrushTypeChosenBg() {
        return getConcaveMiniBg();
    }

    /**
     * ��ȡ���ͼƬ�߽�ͼƬ
     * @return image
     */
    public Image getCanvasClearBg() {
        return getConvexMiniBg();
    }

    /**
     * ��ȡ�ʴ�ͼƬ
     * @return image
     */
    public Image getBrushIcon() {
        return FileControl.getImage("brush.png");
    }

    /**
     * ��ȡ��ƤͼƬ
     * @return image
     */
    public Image getEraserIcon() {
        return FileControl.getImage("eraser.png");
    }

    /**
     * ��ȡ���ͼƬ
     * @return
     */
    public Image getClearIcon() {
        return FileControl.getImage("clear.png");
    }

    /**
     * ��ȡ�������ͼƬ��
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
     * ��ȡ���������ͼƬ
     * @return image
     */
    public Image getScrollBarBg() {
        return FileControl.getImage("background/scroll_bar_bg.png");
    }

    /**
     * ��ȡ����ͼƬ
     * @return image
     */
    public Image getSpeakingBg() {
        return FileControl.getImage("background/speaking_bg.png");
    }
}

