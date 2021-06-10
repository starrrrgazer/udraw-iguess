package ui.part.gamepart;

import Manager.FileControl;
import Manager.FontManager;
import socket.DesaturateFilter;
import ui.part.HeadPortraitLabel;

import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreLabel extends JPanel {
	
	private String progressId;
	private JLabel nameLabel = new JLabel();
	private HeadPortraitLabel headPortraitLabel = new HeadPortraitLabel(0);
	private JLabel painterLabel = new JLabel();
	private JLabel guessedLabel = new JLabel();
	private JLabel scoreLabel = new JLabel("得分：0");
	
	private Font scorePanelfont = FontManager.getDefaultFontManager().getScorePanelFont();
	
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
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		nameLabel.setBounds(0, 0, width, 20);
		headPortraitLabel.setBounds(width - height + 40 >> 1, 20, height - 40, height - 40);
		headPortraitLabel.setArc(height - 40 >> 2);
		painterLabel.setBounds(width - height + 34 >> 1, 17, height - 34, height - 34);
		guessedLabel.setBounds(width - height + 32 >> 1, 16, height - 32, height - 32);
		scoreLabel.setBounds(0, height - 20, width, 20);
	}
	
	public void setClient(String progressId, String name, int headPortrait) {
		this.progressId = progressId;
		nameLabel.setText(name);
		headPortraitLabel.setImage(FileControl.getBufferedImage("avatar/" + (headPortrait + 100 + "").substring(1) + ".jpg"));
	}
	
	public void initScore(String value) {
		scoreLabel.setText(value);
	}
	
	public void addScore(int score) {
		int currentScore = getScore();
		scoreLabel.setText("得分：" + currentScore + score + "");
	}
	
	public void lostConnection() {
		headPortraitLabel.setImage(DesaturateFilter.filter(headPortraitLabel.getImage()));
	}
	
	public void setPainter(boolean isPainter) {
		painterLabel.setVisible(isPainter);
	}
	
	public void guessed(boolean guessed) {
		guessedLabel.setVisible(guessed);
	}
	
	public boolean isGuessed() {
		return guessedLabel.isVisible();
	}
	
	public String getNickName() {
		return nameLabel.getText();
	}
	
	public BufferedImage getHeadPortrait() {
		return headPortraitLabel.getImage();
	}
	
	public int getScore() {
		return Integer.parseInt(scoreLabel.getText());
	}
	
	public String getProgressId() {
		return progressId;
	}
}