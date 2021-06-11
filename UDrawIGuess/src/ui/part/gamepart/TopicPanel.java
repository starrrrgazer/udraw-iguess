package ui.part.gamepart;

import Manager.FontManager;
import ui.mergePanel.MainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class TopicPanel extends ToastPanel implements MouseListener {
	
	private final int arc = 5;
	private final int thickness = 6;
	private final int width = 320;
	private final int height = 160;
	private final int bottomHeight = 50;
	
	private long endTime;

	private Color mainColor = new Color(230, 180, 115);
	private Color darkColor = new Color(225, 150, 80);
	private Color lightColor = new Color(255, 222, 160);
	
	private JLabel guessedConut = new JLabel();
	private JLabel countDownLabel = new JLabel();
	private JLabel countDown = new JLabel();
	private JLabel topicLabel = new JLabel();
	private JLabel topic = new JLabel();
	
	private FontManager fontManager = FontManager.getDefaultFontManager();
	private Font toastTopicPanelFont = fontManager.getToastTopicPanelFont();
	private Font toastShowTopicFont = fontManager.getToastShowTopicFont();

	public TopicPanel(String topic, int duration) {
		initContent(topic);
		initBounds();
		setTime(System.currentTimeMillis(), duration);
		
		setLayout(null);
		add(this.guessedConut);
		add(this.countDownLabel);
		add(this.countDown);
		add(this.topicLabel);
		add(this.topic);
		setSize(width, height);
		setOpaque(false);
	}
	
	private void initContent(String topic) {
		int guessedCount = MainFrame.getMainFrame().getGamePanelOnly().getScorePanel().getGuessedCount();
		if (guessedCount == 0) {
			this.guessedConut.setText("没有人猜对哦~");
		} else {
			this.guessedConut.setText("有" + guessedCount + "人猜对了。");
		}
		this.guessedConut.setFont(toastTopicPanelFont);
		this.countDownLabel.setText("倒计时：");
		this.countDownLabel.setFont(toastTopicPanelFont);
		this.countDown.setForeground(Color.RED);
		this.countDown.setFont(toastTopicPanelFont);
		this.topicLabel.setText("答案：");
		this.topicLabel.setFont(toastTopicPanelFont);
		this.topic.setText("【" + topic + "】");
		this.topic.setFont(toastShowTopicFont);
		this.topic.setForeground(Color.RED);
	}
	
	private void initBounds() {
		FontMetrics defaultFontMetrics = getFontMetrics(toastTopicPanelFont);
		int defaultFontHeight = defaultFontMetrics.getHeight();
		
		int guessedConutWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.guessedConut.getText());
		this.guessedConut.setBounds(20, 13, guessedConutWidth, defaultFontHeight);
		
		int countDownLabelWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.countDownLabel.getText());
		this.countDownLabel.setBounds(290 - countDownLabelWidth, 13, countDownLabelWidth, defaultFontHeight);
		int countDownWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.countDown.getText() + "0");
		this.countDown.setBounds(290, 13, countDownWidth, defaultFontHeight);
		
		int topicLabelWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.topicLabel.getText());
		int topicWidth = SwingUtilities.computeStringWidth(getFontMetrics(toastShowTopicFont), this.topic.getText());
		int topicHeight = this.topic.getFont().getSize();
		this.topicLabel.setBounds(width - topicLabelWidth - topicWidth >> 1, 80 - defaultFontHeight, topicLabelWidth, defaultFontHeight);
		this.topic.setBounds(width + topicLabelWidth - topicWidth >> 1, 80 - topicHeight, topicWidth, topicHeight);

	}
	
	private void setTime(long time, int duration) {
		this.endTime = time + duration;
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	@Override
	public void onShow(long time) {
		countDown.setText((endTime - time) / 1000 + "");
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
		g2d.setColor(darkColor);
		g2d.fillRect(0, height - bottomHeight, width, bottomHeight);
		g2d.setColor(lightColor);
		g2d.drawLine(0, height - bottomHeight, width, height - bottomHeight);

		g2d.setClip(null);
		g2d.setColor(oldColor);
	}

	public void buttonPressed(EventObject e) {

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		buttonPressed(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}