package ui.part;

import Manager.FontManager;
import socket.Config;
import ui.mergeFace.MainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ResultPanel extends ToastPanel {
	
	private final int arc = 5;
	private final int thickness = 5;
	private final int offsetHeight = 30;
	private final int lineHeight = 50;
	private final int width = 320;
	private int height = lineHeight * Config.maximum + lineHeight + offsetHeight + offsetHeight;
	
	private long endTime;

	private Color mainColor = new Color(230, 180, 115);
	private Color darkColor = new Color(225, 150, 80);
	
	private JLabel countDownLabel = new JLabel();
	private JLabel countDown = new JLabel();
	
	private Font toastResultPanelFont = FontManager.getDefaultFontManager().getToastResultPanelFont();

	public ResultPanel(int duration) {
		
		setTime(System.currentTimeMillis(), duration);
		
		FontMetrics defaultFontMetrics = getFontMetrics(toastResultPanelFont);
		int defaultFontHeight = defaultFontMetrics.getHeight();
		
		this.countDownLabel.setText("µ¹¼ÆÊ±£º");
		int countDownLabelWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.countDownLabel.getText());
		this.countDownLabel.setFont(toastResultPanelFont);
		this.countDownLabel.setBounds(290 - countDownLabelWidth, 10, countDownLabelWidth, defaultFontHeight);
		
		this.countDown.setForeground(Color.RED);
		int countDownWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.countDown.getText() + "0");
		this.countDown.setFont(toastResultPanelFont);
		this.countDown.setBounds(290, 10, countDownWidth, defaultFontHeight);
		
		add(countDownLabel);
		add(countDown);
		
		ArrayList<ScoreLabel> list = MainFrame.getMainFrame().getGamePanelOnly().getScorePanel().getUsersScore();
		height = list.size() * lineHeight + offsetHeight + offsetHeight;
		addUsers(list);
		
		setLayout(null);
		setSize(width, height);
		setOpaque(false);
	}
	
	private void addUsers(ArrayList<ScoreLabel> list) {
		for (int i = 0; !list.isEmpty(); i++) {
			int temp = 0;
			for (int j = 1; j < list.size(); j++) {
				if (list.get(temp).getScore() < list.get(j).getScore()) {
					temp = j;
				}
			}
			ResultItemPanel panel = new ResultItemPanel(list.remove(temp));
			panel.setBounds(0, i * lineHeight + offsetHeight, width, lineHeight);
			add(panel);
		}
	}
	
	private void setTime(long time, int duration) {
		this.endTime = time + duration;
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void onShow(long currentTime) {
		countDown.setText((endTime - currentTime) / 1000 + "");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color oldColor = g2d.getColor();

		RoundRectangle2D.Float rect = new RoundRectangle2D.Float(
				thickness, thickness, width - (thickness << 1) - 1, height - (thickness << 1) - 1, (arc << 1) - 1, (arc << 1) - 1);

		g2d.setColor(mainColor);
		g2d.fill(rect);
		g2d.setClip(rect);
		
		for (int i = 0; i < height / lineHeight; i += 2) {
			g2d.setColor(darkColor);
			g2d.fillRect(0, i * lineHeight + offsetHeight, width, lineHeight);
		}
		
		g2d.setClip(null);
		g2d.setColor(oldColor);
	}
}