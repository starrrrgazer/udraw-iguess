package ui.part;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Manager.ImageManager;

/**
 * mainFrame的按钮组件
 */
public class FrameButton extends JPanel implements MouseListener {
    /**
     * frame的按钮类别，最小化，最大化，关闭
     */
    public enum Type { MINIMIZE, MAXIMIZE, CLOSE }

    private Image[] imgs;
    private Image currentImg;

    /**
     * 构造函数
     * @param type 按钮的类别
     */
    public FrameButton(Type type) {
        switch (type) {
            case MINIMIZE:
                imgs = ImageManager.getDefaultImageManager().getMinimizeImages();
                break;
            case MAXIMIZE:
                imgs = ImageManager.getDefaultImageManager().getMaximizeImages();
                break;
            case CLOSE:
                imgs = ImageManager.getDefaultImageManager().getCloseImages();
                break;
        }
        currentImg = imgs[0];
        this.addMouseListener(this);
        this.setOpaque(false);
    }

    /**
     * 设置按钮是否能响应事件
     * @param enabled true，能响应事件，false，不能
     */
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.addMouseListener(this);
        } else {
            this.removeMouseListener(this);
        }
        repaint();
    }

    /**
     * 设置按钮的组合规则
     * @param g Graphics 方法重载
     * @see Graphics2D
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!isEnabled()) {
            g2d.setComposite(AlphaComposite.SrcOver.derive(.5f));
        }
        g2d.drawImage(currentImg, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * 鼠标按下时，修改成对应的图片
     * @param e 鼠标事件
     */
    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = imgs[2];
        repaint();
    }

    /**
     * 鼠标释放时，修改成对应的图片
     * @param e 鼠标事件
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentImg != imgs[0]) {
            currentImg = imgs[1];
            repaint();
        }
    }

    /**
     * 鼠标进入到按钮时，修改成对应的图片
     * @param e 鼠标事件
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        currentImg = imgs[1];
        repaint();
    }

    /**
     * 鼠标离开按钮式，修改成对应图片
     * @param e 鼠标事件
     */
    @Override
    public void mouseExited(MouseEvent e) {
        currentImg = imgs[0];
        repaint();
    }
}
