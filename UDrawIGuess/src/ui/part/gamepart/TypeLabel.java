package ui.part.gamepart;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * ��ˢ����
 */
public class TypeLabel extends JPanel {
	/**
	 * ѡ��ı�ˢ����
	 */
	public static int chosen;

	/**
	 * ��ˢ���ͣ��������ʡ���Ƥ�����
	 */
	public enum Type { BRUSH, ERASER, CLEAR }
	private Type type;
	private Image img;
	private int i;

	private ImageManager imageManager = ImageManager.getDefaultImageManager();
	private Image typeChosenBg = imageManager.getBrushTypeChosenBg();
	private Image canvasClearBg = imageManager.getCanvasClearBg();

	/**
	 * ���ñ�ˢͼƬ
	 * @param type ��ˢ����
	 * @param i ѡ��ı�ˢ�����ڱ�ˢ���������
	 */
	public TypeLabel(Type type, int i) {
		this.type = type;
		switch (type) {
		case BRUSH:
			this.img = imageManager.getBrushIcon();
			break;
		case ERASER:
			this.img = imageManager.getEraserIcon();
			break;
		case CLEAR:
			this.img = imageManager.getClearIcon();
			break;
		}
		this.i = i;
		setOpaque(false);
	}

	/**
	 * ��ȡ��ˢ����
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * ���ñ�ˢ��ѡ��
	 */
	public void choose() {
		TypeLabel.chosen = i;
	}

	/**
	 * ���Ʊ�ˢ
	 * @param g Graphics����������
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, -3, -3, this);
		if (i == chosen) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(typeChosenBg, 0, 0, width, height, this);
		} else if (i == 2) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(canvasClearBg, 0, 0, width, height, this);
		}
	}
}
