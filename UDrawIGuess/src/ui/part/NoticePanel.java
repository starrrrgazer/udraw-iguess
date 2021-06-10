package ui.part;

import Manager.FontManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.SwingUtilities;


public class NoticePanel extends ToastPanel {
	private final int offsetX = 10;
	private final int offsetY = 3;
	private final float maxAlpha = 180;
	private final float fadeSpeed = 1;
	
	private long startTime;
	private long endTime;
	
	private int alpha = 0;
	private String message;
	
	private Font toastNoticeFont = FontManager.getDefaultFontManager().getToastNoticeFont();
	
	public NoticePanel(String message, int duration) {
		setMessage(message);
		setTime(System.currentTimeMillis(), duration);
		initSize();
	}
	
	private void setMessage(String message) {
		this.message = message;
	}
	
	private void setTime(long time, int duration) {
		this.startTime = time;
		this.endTime = time + duration;
	}
	
	private void initSize() {
		FontMetrics fontMetrics = getFontMetrics(toastNoticeFont);
		int textWidth = SwingUtilities.computeStringWidth(fontMetrics, message);
		int textHeight = fontMetrics.getHeight();
		setSize(textWidth + (offsetX << 1), textHeight + (offsetY << 1));
	}
	
	private void setAlpha(int alpha) {
		this.alpha = (int) Math.min(alpha, maxAlpha);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void onShow(long currentTime) {
		int alphaTemp;
		if (currentTime + currentTime < startTime + endTime) {
			alphaTemp = (int) ((currentTime - startTime) / fadeSpeed);
		} else {
			alphaTemp = (int) ((endTime - currentTime) / fadeSpeed);
		}
		setAlpha(alphaTemp);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		RoundRectangle2D.Float bounds = new RoundRectangle2D.Float(0, 0, width, height, 9, 9);
		g2d.setClip(bounds);
		g2d.setColor(new Color(0, 0, 0, alpha));
		g2d.fillRect(0, 0, width, height);
		
		RoundRectangle2D.Float border1 = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, 7, 7);
		g2d.setColor(new Color(128, 128, 128, alpha));
		g2d.draw(border1);
		
		RoundRectangle2D.Float border2 = new RoundRectangle2D.Float(1, 1, width - 3, height - 3, 5, 5);
		g2d.setColor(new Color(64, 64, 64, alpha));
		g2d.draw(border2);
		

		int textHeight = getFontMetrics(toastNoticeFont).getHeight();
		g2d.setFont(toastNoticeFont);
		g2d.setColor(new Color(255, 255, 255, alpha));
		g2d.drawString(message, offsetX, (textHeight * 3 >> 2) + offsetY + 2);
	}
}