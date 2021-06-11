package ui.part.gamepart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import Manager.ImageManager;

/**
 * 笔刷选取的颜色类
 */
public class PigmentLabel extends JPanel {
	/**
	 * 选择的颜色
	 */
	public static int chosen;
	
	private Color color;
	private int i;
	
	private Image img = ImageManager.getDefaultImageManager().getPigmentBg();

	/**
	 * 构造函数
	 * @param color 颜色
	 * @param i 选取的颜色在颜色数组的索引
	 */
	public PigmentLabel(Color color, int i) {
		this.color = color;
		this.i = i;
		setOpaque(false);
	}

	/**
	 * 获取颜色
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 设置颜色被选中
	 */
	public void choose() {
		PigmentLabel.chosen = i;
	}

	/**
	 * 绘制颜色选择图形
	 * @param g Graphics，方法重载
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, width, height, this);
		g2d.setColor(color);
		g2d.fillRect(2, 2, width - 4, height - 4);
		if (i == chosen) {
			g.setColor(Color.WHITE);
			g.drawRect(1, 1, width - 3, height - 3);
			g.drawRect(2, 2, width - 5, height - 5);
		}
	}
}
