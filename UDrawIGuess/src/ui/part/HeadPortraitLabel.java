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
 * ͷ����Ϣ��
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
	 * ���캯��
	 * @param i ͷ��ͼƬ��ͷ�����������
	 */
	public HeadPortraitLabel(int i) {
		this(i, 0, false);
	}

	/**
	 * ���캯��
	 * @param i ͷ��ͼƬ��ͷ�����������
	 * @param arc Բ���Ŀ�Ⱥ͸߶�
	 */
	public HeadPortraitLabel(int i, int arc) {
		this(i, arc, false);
	}

	/**
	 * ���캯��
	 * @param i ͷ��ͼƬ��ͷ�����������
	 * @param arc Բ���Ŀ�Ⱥ͸߶�
	 * @param choosing �Ƿ�ѡ��
	 */
	public HeadPortraitLabel(int i, int arc, boolean choosing) {
		setI(i);
		setArc(arc);
		setChoosing(choosing);
		setOpaque(false);
	}

	/**
	 * ��ȡ��ѡ�е�ͷ����ͷ�����������
	 * @return
	 */
	public int getI() {
		return i;
	}

	/**
	 * ����ͷ�������������������ͷ��ͼƬ
	 * @param i ͷ����ͷ�����������
	 */
	public void setI(int i) {
		this.i = i;
		setImage(ImageManager.getDefaultImageManager().getHeadPortraits(i));
	}

	/**
	 * ��ȡͷ��ͼƬ
	 * @return ͷ��ͼƬ
	 */
	public BufferedImage getImage() {
		return img;
	}

	/**
	 * ����ͷ��ͼƬ
	 * @param img ͷ��ͼƬ
	 */
	public void setImage(BufferedImage img) {
		this.img = img;
		repaint();
	}

	/**
	 * ��ȡԲ���Ŀ�Ⱥ͸߶�
	 * @return Բ���Ŀ�Ⱥ͸߶ȵ�ֵ
	 */
	public int getArc() {
		return arc;
	}

	/**
	 * ����Բ���Ŀ�Ⱥ͸߶�
	 * @param arc Բ���Ŀ�Ⱥ͸߶ȵ�ֵ
	 */
	public void setArc(int arc) {
		this.arc = arc;
		repaint();
	}

	/**
	 * �����Ƿ�ѡ��
	 * @param choosing true����ѡ��
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
	 * ��ͷ��label
	 */
	public void choose() {
		headPortraitLabel = this;
	}

	/**
	 * ����ͷ��
	 * @param g Graphics����������
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
