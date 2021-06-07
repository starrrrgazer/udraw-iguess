package ui.part.component;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Manager.ImageManager;

//@SuppressWarnings("serial")
public class FrameButton extends JPanel implements MouseListener {

    public static enum Type { MINIMIZE, MAXIMIZE, CLOSE }

    private Image[] imgs;
    private Image currentImg;

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

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.addMouseListener(this);
        } else {
            this.removeMouseListener(this);
        }
        repaint();
    }

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

    @Override
    public void mousePressed(MouseEvent e) {
        currentImg = imgs[2];
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentImg != imgs[0]) {
            currentImg = imgs[1];
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        currentImg = imgs[1];
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentImg = imgs[0];
        repaint();
    }
}
