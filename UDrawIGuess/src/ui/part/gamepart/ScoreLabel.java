package ui.part.gamepart;

import Manager.FileControl;
import Manager.FontManager;
import socket.DesaturateFilter;
import ui.part.HeadPortraitLabel;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 头像下面的得分标记
 */
public class ScoreLabel extends JPanel {
	
	private String progressId;
	private JLabel nameLabel = new JLabel();
	private HeadPortraitLabel headPortraitLabel = new HeadPortraitLabel(0);
	private JLabel painterLabel = new JLabel();
	private JLabel guessedLabel = new JLabel();
	private JLabel scoreLabel = new JLabel("0");
	
	private Font scorePanelfont = FontManager.getDefaultFontManager().getScorePanelFont();

	/**
	 * 默认构造函数
	 */
	public ScoreLabel() {

		nameLabel.setFont(scorePanelfont);
		scoreLabel.setFont(scorePanelfont);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		painterLabel.setVisible(false);
		guessedLabel.setVisible(false);
		
		add(nameLabel);
		add(headPortraitLabel);
		add(painterLabel);
		add(guessedLabel);
		add(scoreLabel);
		
		setLayout(null);
		setOpaque(false);
	}

	/**
	 * 设置位置和大小
	 * @param x 水平偏移量
	 * @param y 垂直偏移量
	 * @param width 宽度
	 * @param height 高度
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		nameLabel.setBounds(0, 0, width, 20);
		headPortraitLabel.setBounds(width - height + 40 >> 1, 20, height - 40, height - 40);
		headPortraitLabel.setArc(height - 40 >> 2);
		painterLabel.setBounds(width - height + 34 >> 1, 17, height - 34, height - 34);
		guessedLabel.setBounds(width - height + 32 >> 1, 16, height - 32, height - 32);
		scoreLabel.setBounds(0, height - 20, width, 20);
		scoreLabel.setForeground(Color.CYAN);
	}

	/**
	 * 设置玩家的头像和昵称
	 * @param progressId 玩家的进程id
	 * @param name 玩家的昵称
	 * @param headPortrait 玩家的头像在头像数组的索引
	 */
	public void setClient(String progressId, String name, int headPortrait) {
		this.progressId = progressId;
		nameLabel.setText(name);
		headPortraitLabel.setImage(FileControl.getBufferedImage("avatar/" + (headPortrait + 100 + "").substring(1) + ".jpg"));
	}

	/**
	 * 初始化各个玩家的得分
	 * @param value 得分
	 */
	public void initScore(String value) {
		scoreLabel.setText(value);
	}

	/**
	 * 给各个玩家加分
	 * @param score 加的分值
	 */
	public void addScore(int score) {
		int currentScore = getScore();
		scoreLabel.setText(currentScore + score + "");
	}

	/**
	 * 玩家断开连接的处理函数
	 */
	public void lostConnection() {
		headPortraitLabel.setImage(DesaturateFilter.filter(headPortraitLabel.getImage()));
	}

	/**
	 * 当前玩家是不是绘画者
	 * @param isPainter
	 */
	public void setPainter(boolean isPainter) {
		painterLabel.setVisible(isPainter);
	}

	/**
	 * 玩家是否猜对
	 * @param guessed true猜对
	 */
	public void guessed(boolean guessed) {
		guessedLabel.setVisible(guessed);
	}

	/**
	 * 根据是否猜对进行显示或隐藏
	 * @return label的显示或隐藏，也就是玩家猜的结果是否正确
	 */
	public boolean isGuessed() {
		return guessedLabel.isVisible();
	}

	/**
	 * 获取玩家的昵称
	 * @return
	 */
	public String getNickName() {
		return nameLabel.getText();
	}

	/**
	 * 获取玩家的头像
	 * @return 玩家的头像图片
	 */
	public BufferedImage getHeadPortrait() {
		return headPortraitLabel.getImage();
	}

	/**
	 * 获取玩家的得分
	 * @return 玩家的得分
	 */
	public int getScore() {
		return Integer.parseInt(scoreLabel.getText());
	}

	/**
	 * 获取玩家的进程id
	 * @return 玩家的进程id
	 */
	public String getProgressId() {
		return progressId;
	}
}