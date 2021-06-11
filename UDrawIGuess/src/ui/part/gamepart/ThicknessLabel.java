package ui.part.gamepart;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 设置笔刷粗细
 */
public class ThicknessLabel extends JPanel {
	/**
	 * 笔刷粗细是否被选中
	 */
	public static int chosen = 1;
	
	private int thickness;
	private int i;
	
	private Image chosenBg = ImageManager.getDefaultImageManager().getThicknessChosenBg();

	/**
	 * 构造函数
	 * @param thickness 粗细程度
	 * @param i 选择的笔刷粗细在笔刷数组的索引
	 */
	public ThicknessLabel(int thickness, int i) {
		this.thickness = thickness;
		this.i = i;
		setOpaque(false);
	}

	/**
	 * 获取笔刷粗细
	 * @return 笔刷粗细
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * 设置笔刷粗细被选中
	 */
	public void choose() {
		ThicknessLabel.chosen = i;
	}

	/**
	 * 绘制笔刷粗细图片
	 * @param g Graphics，方法重载
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		int size = thickness << 1;
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.fillOval(width - size >> 1, height - size >> 1, size, size);
		if (i == chosen) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(chosenBg, 0, 0, width, height, this);
		}
	}
}
