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
 * ͷ������ĵ÷ֱ��
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
	 * Ĭ�Ϲ��캯��
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
	 * ����λ�úʹ�С
	 * @param x ˮƽƫ����
	 * @param y ��ֱƫ����
	 * @param width ���
	 * @param height �߶�
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
	 * ������ҵ�ͷ����ǳ�
	 * @param progressId ��ҵĽ���id
	 * @param name ��ҵ��ǳ�
	 * @param headPortrait ��ҵ�ͷ����ͷ�����������
	 */
	public void setClient(String progressId, String name, int headPortrait) {
		this.progressId = progressId;
		nameLabel.setText(name);
		headPortraitLabel.setImage(FileControl.getBufferedImage("avatar/" + (headPortrait + 100 + "").substring(1) + ".jpg"));
	}

	/**
	 * ��ʼ��������ҵĵ÷�
	 * @param value �÷�
	 */
	public void initScore(String value) {
		scoreLabel.setText(value);
	}

	/**
	 * ��������Ҽӷ�
	 * @param score �ӵķ�ֵ
	 */
	public void addScore(int score) {
		int currentScore = getScore();
		scoreLabel.setText(currentScore + score + "");
	}

	/**
	 * ��ҶϿ����ӵĴ�����
	 */
	public void lostConnection() {
		headPortraitLabel.setImage(DesaturateFilter.filter(headPortraitLabel.getImage()));
	}

	/**
	 * ��ǰ����ǲ��ǻ滭��
	 * @param isPainter
	 */
	public void setPainter(boolean isPainter) {
		painterLabel.setVisible(isPainter);
	}

	/**
	 * ����Ƿ�¶�
	 * @param guessed true�¶�
	 */
	public void guessed(boolean guessed) {
		guessedLabel.setVisible(guessed);
	}

	/**
	 * �����Ƿ�¶Խ�����ʾ������
	 * @return label����ʾ�����أ�Ҳ������ҲµĽ���Ƿ���ȷ
	 */
	public boolean isGuessed() {
		return guessedLabel.isVisible();
	}

	/**
	 * ��ȡ��ҵ��ǳ�
	 * @return
	 */
	public String getNickName() {
		return nameLabel.getText();
	}

	/**
	 * ��ȡ��ҵ�ͷ��
	 * @return ��ҵ�ͷ��ͼƬ
	 */
	public BufferedImage getHeadPortrait() {
		return headPortraitLabel.getImage();
	}

	/**
	 * ��ȡ��ҵĵ÷�
	 * @return ��ҵĵ÷�
	 */
	public int getScore() {
		return Integer.parseInt(scoreLabel.getText());
	}

	/**
	 * ��ȡ��ҵĽ���id
	 * @return ��ҵĽ���id
	 */
	public String getProgressId() {
		return progressId;
	}
}