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
 * ������ת�õ��İ�ť
 */
public class JumpButton extends JPanel {
	/**
	 * �ƽ�ָ��
	 */
	public static final float GOLD_SECTION = 0.618f;
	/**
	 * ��ť���
	 */
	public static final int WIDTH = 64;
	/**
	 * ��ť�߶�
	 */
	public static final int HEIGHT = 80;
	
	private int textWidth;
	private int textHeight;
	
	private String text;
	
	private Font font = FontManager.getDefaultFontManager().getJumpButtonFont();
	private Image img = ImageManager.getDefaultImageManager().getJumpButtonBg();

	/**
	 * ���캯��
	 * @param name ��ťֵ������ť��ʾ������
	 */
	public JumpButton(String name) {
		setText(name);
		setFont(font);
	}

	/**
	 * ��ȡ��ťֵ
	 * @return ��ťֵ
	 */
	public String getText() {
		return text;
	}

	/**
	 * ���ð�ťֵ
	 * @param text ��ťֵ
	 */
	public void setText(String text) {
		this.text = text;
		FontMetrics fontMetrics = getFontMetrics(font);
		textWidth = SwingUtilities.computeStringWidth(fontMetrics, text);
		textHeight = fontMetrics.getHeight();
	}

	/**
	 * ���ư�ť
	 * @param g Graphics ��������
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
