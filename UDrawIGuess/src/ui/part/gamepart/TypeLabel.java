package ui.part.gamepart;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 笔刷类型
 */
public class TypeLabel extends JPanel {
	/**
	 * 选择的笔刷类型
	 */
	public static int chosen;

	/**
	 * 笔刷类型，，包括笔、橡皮和清除
	 */
	public enum Type { BRUSH, ERASER, CLEAR }
	private Type type;
	private Image img;
	private int i;

	private ImageManager imageManager = ImageManager.getDefaultImageManager();
	private Image typeChosenBg = imageManager.getBrushTypeChosenBg();
	private Image canvasClearBg = imageManager.getCanvasClearBg();

	/**
	 * 设置笔刷图片
	 * @param type 笔刷类型
	 * @param i 选择的笔刷类型在笔刷数组的索引
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
	 * 获取笔刷类型
	 * @return
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 设置笔刷被选中
	 */
	public void choose() {
		TypeLabel.chosen = i;
	}

	/**
	 * 绘制笔刷
	 * @param g Graphics，方法重载
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
