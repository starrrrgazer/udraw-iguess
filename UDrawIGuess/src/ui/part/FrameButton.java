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
 * mainFrame�İ�ť���
 */
public class FrameButton extends JPanel implements MouseListener {
    /**
     * frame�İ�ť�����С������󻯣��ر�
     */
    public enum Type { MINIMIZE, MAXIMIZE, CLOSE }

    private Image[] imgs;
    private Image currentImg;

    /**
     * ���캯��
     * @param type ��ť�����
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
     * ���ð�ť�Ƿ�����Ӧ�¼�
     * @param enabled true������Ӧ�¼���false������
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
     * ���ð�ť����Ϲ���
     * @param g Graphics ��������
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
     * ��갴��ʱ���޸ĳɶ�Ӧ��ͼƬ
     * @param e ����¼�
     */
    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = imgs[2];
        repaint();
    }

    /**
     * ����ͷ�ʱ���޸ĳɶ�Ӧ��ͼƬ
     * @param e ����¼�
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentImg != imgs[0]) {
            currentImg = imgs[1];
            repaint();
        }
    }

    /**
     * �����뵽��ťʱ���޸ĳɶ�Ӧ��ͼƬ
     * @param e ����¼�
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        currentImg = imgs[1];
        repaint();
    }

    /**
     * ����뿪��ťʽ���޸ĳɶ�ӦͼƬ
     * @param e ����¼�
     */
    @Override
    public void mouseExited(MouseEvent e) {
        currentImg = imgs[0];
        repaint();
    }
}
