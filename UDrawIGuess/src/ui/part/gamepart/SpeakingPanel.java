package ui.part.gamepart;

import Manager.FontManager;
import Manager.ImageManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * 玩家说话显示
 * @see ToastPanel
 */
public class SpeakingPanel extends ToastPanel {
	
	private int width = 90;
	private int height = 60;
	private int labelWidth = 70;
	private int labelHeight = 34;
	private int offsetY = 50;

	private long endTime;
	
	private JLabel speaking = new JLabel();
	
	private Font addScoreFont = FontManager.getDefaultFontManager().getAddScoreFont();
	private Image img = ImageManager.getDefaultImageManager().getSpeakingBg();

	/**
	 * 构造函数
	 * @param speaking 说话内容
	 * @param isAddScore 是否是加分的说话内容，true是
	 * @param duration 显示时长
	 */
	public SpeakingPanel(String speaking, boolean isAddScore, int duration) {
		
		setTime(System.currentTimeMillis(), duration);
		
		if (isAddScore) {
			this.speaking.setFont(addScoreFont);
			this.speaking.setForeground(Color.RED);
			this.speaking.setText(speaking);
		} else {
			this.speaking.setText( speaking);
		}

		this.speaking.setHorizontalAlignment(JLabel.CENTER);
		this.speaking.setVerticalAlignment(JLabel.CENTER);
		this.speaking.setBounds(11, 6, labelWidth, labelHeight);

		this.setLayout(null);
		this.add(this.speaking);
		this.setSize(width, height);
		this.setOpaque(false);
	}
	
	private void setTime(long time, int duration) {
		this.endTime = time + duration;
	}

	/**
	 * 设置和组件的相对位置
	 * @param component 组件
	 */
	@Override
	public void setLocationRelativeTo(Component component) {
		int x = component.getWidth() - this.getWidth() >> 1;
		int y = component.getHeight() - this.getHeight() >> 1;
		Point loaction = SwingUtilities.convertPoint(component, x, y - offsetY, this.getParent());
		this.setLocation(loaction);
	}

	/**
	 * 获取结束时间
	 * @return 结束时间
	 */
	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void onShow(long currentTime) {}

	/**
	 * 绘制说话的图像
	 * @param g Graphics，方法重载
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, width, height, this);
	}

}
