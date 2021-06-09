package ui.part.component;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Manager.FontManager;
import Manager.ImageManager;


public class MyButton extends JPanel implements MouseListener {

	private Image[] imgs = ImageManager.getDefaultImageManager().getButtonBgs();
	private Image currentImg = imgs[0];
	
	private String text;
	private int textWidth;
	private int textHeight;
	private int offset;
	
	private Font font = FontManager.getDefaultFontManager().getButtonFont();
	
	public MyButton(String text) {
		setText(text);
		setFont(font);
		setOpaque(false);
		addMouseListener(this);
	}
	
	public void setText(String text) {
		this.text = text;
		FontMetrics fontMetrics = getFontMetrics(font);
		textWidth = SwingUtilities.computeStringWidth(fontMetrics, text);
		textHeight = fontMetrics.getHeight();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setClip(0, 0, height >> 1, height);
		g2d.drawImage(currentImg, 0, 0, height, height, this);
		g2d.setClip(height >> 1, 0, width - height, height);
		g2d.drawImage(currentImg, 0, 0, width, height, this);
		g2d.setClip(width - (height >> 1), 0, height >> 1, height);
		g2d.drawImage(currentImg, width - height, 0, height, height, this);
		g2d.setClip(null);
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(text, width - textWidth >> 1, (height + (textHeight >> 1) >> 1) + offset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		currentImg = imgs[1];
		offset = 1;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentImg = imgs[0];
		offset = 0;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {
		currentImg = imgs[0];
		offset = 0;
		repaint();
	}
}
