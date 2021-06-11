package ui.part;

import Manager.FontManager;
import Manager.ImageManager;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 界面跳转用到的按钮
 */
public class JumpButton extends JPanel {
	/**
	 * 黄金分割比
	 */
	public static final float GOLD_SECTION = 0.618f;
	/**
	 * 按钮宽度
	 */
	public static final int WIDTH = 64;
	/**
	 * 按钮高度
	 */
	public static final int HEIGHT = 80;
	
	private int textWidth;
	private int textHeight;
	
	private String text;
	
	private Font font = FontManager.getDefaultFontManager().getJumpButtonFont();
	private Image img = ImageManager.getDefaultImageManager().getJumpButtonBg();

	/**
	 * 构造函数
	 * @param name 按钮值，即按钮显示的内容
	 */
	public JumpButton(String name) {
		setText(name);
		setFont(font);
	}

	/**
	 * 获取按钮值
	 * @return 按钮值
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置按钮值
	 * @param text 按钮值
	 */
	public void setText(String text) {
		this.text = text;
		FontMetrics fontMetrics = getFontMetrics(font);
		textWidth = SwingUtilities.computeStringWidth(fontMetrics, text);
		textHeight = fontMetrics.getHeight();
	}

	/**
	 * 绘制按钮
	 * @param g Graphics 方法重载
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		g2d.drawString(text, (getWidth() - textWidth >> 1), textHeight);
	}
}
