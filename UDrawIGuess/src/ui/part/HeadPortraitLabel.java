package ui.part;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class HeadPortraitLabel extends JPanel {
	
	private static HeadPortraitLabel headPortraitLabel;
	
	private int i;
	private BufferedImage img;
	private int arc = 25;
	private boolean choosing;
	private int offset;
	private Image bg = ImageManager.getDefaultImageManager().getHeadBg();
	
	public HeadPortraitLabel(int i) {
		this(i, 0, false);
	}
	
	public HeadPortraitLabel(int i, int arc) {
		this(i, arc, false);
	}
	
	public HeadPortraitLabel(int i, int arc, boolean choosing) {
		setI(i);
		setArc(arc);
		setChoosing(choosing);
		setOpaque(false);
	}
	
	public int getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
		setImage(ImageManager.getDefaultImageManager().getHeadPortraits(i));
	}

	public BufferedImage getImage() {
		return img;
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
		repaint();
	}
	
	public int getArc() {
		return arc;
	}
	
	public void setArc(int arc) {
		this.arc = arc;
		repaint();
	}
	
	public boolean isChoosing() {
		return choosing;
	}
	
	public void setChoosing(boolean choosing) {
		this.choosing = choosing;
		if (choosing) {
			offset = 2;
		} else {
			offset = 0;
		}
	}
	
	public void choose() {
		headPortraitLabel = this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		if (choosing && headPortraitLabel != this) {
			g2d.drawImage(bg, 0, 0, width, height, this);
		}
		RoundRectangle2D rect = new RoundRectangle2D.Float(offset, offset, width - (offset << 1), height - (offset << 1), arc, arc);
		g.setClip(rect);
		g.drawImage(img, 0, 0, width, height, this);
		g.setClip(null);
	}
	
	public static int getChoosen() {
		return headPortraitLabel.getI();
	}
}
