package ui.part;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 头像信息类
 */
public class HeadPortraitLabel extends JPanel {
	
	private static HeadPortraitLabel headPortraitLabel;
	
	private int i;
	private BufferedImage img;
	private int arc = 25;
	private boolean choosing;
	private int offset;
	private Image bg = ImageManager.getDefaultImageManager().getHeadBg();

	/**
	 * 构造函数
	 * @param i 头像图片在头像数组的索引
	 */
	public HeadPortraitLabel(int i) {
		this(i, 0, false);
	}

	/**
	 * 构造函数
	 * @param i 头像图片在头像数组的索引
	 * @param arc 圆弧的宽度和高度
	 */
	public HeadPortraitLabel(int i, int arc) {
		this(i, arc, false);
	}

	/**
	 * 构造函数
	 * @param i 头像图片在头像数组的索引
	 * @param arc 圆弧的宽度和高度
	 * @param choosing 是否被选中
	 */
	public HeadPortraitLabel(int i, int arc, boolean choosing) {
		setI(i);
		setArc(arc);
		setChoosing(choosing);
		setOpaque(false);
	}

	/**
	 * 获取被选中的头像在头像数组的索引
	 * @return
	 */
	public int getI() {
		return i;
	}

	/**
	 * 设置头像数组的索引，并设置头像图片
	 * @param i 头像在头像数组的索引
	 */
	public void setI(int i) {
		this.i = i;
		setImage(ImageManager.getDefaultImageManager().getHeadPortraits(i));
	}

	/**
	 * 获取头像图片
	 * @return 头像图片
	 */
	public BufferedImage getImage() {
		return img;
	}

	/**
	 * 设置头像图片
	 * @param img 头像图片
	 */
	public void setImage(BufferedImage img) {
		this.img = img;
		repaint();
	}

	/**
	 * 获取圆弧的宽度和高度
	 * @return 圆弧的宽度和高度的值
	 */
	public int getArc() {
		return arc;
	}

	/**
	 * 设置圆弧的宽度和高度
	 * @param arc 圆弧的宽度和高度的值
	 */
	public void setArc(int arc) {
		this.arc = arc;
		repaint();
	}

	/**
	 * 设置是否被选中
	 * @param choosing true，被选中
	 */
	public void setChoosing(boolean choosing) {
		this.choosing = choosing;
		if (choosing) {
			offset = 2;
		} else {
			offset = 0;
		}
	}

	/**
	 * 绑定头像label
	 */
	public void choose() {
		headPortraitLabel = this;
	}

	/**
	 * 绘制头像
	 * @param g Graphics，方法重载
	 */
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

//	public static int getChoosen() {
//		return headPortraitLabel.getI();
//	}
}
