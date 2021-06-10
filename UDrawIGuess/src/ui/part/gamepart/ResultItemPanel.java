package ui.part.gamepart;

import Manager.FontManager;
import ui.part.HeadPortraitLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ResultItemPanel extends JPanel {

	private JLabel scoreLabel1 = new JLabel();
	private JLabel scoreLabel2 = new JLabel();
	private HeadPortraitLabel headPortrait = new HeadPortraitLabel(0);
	private JLabel nickName = new JLabel();
	
	private FontManager fontManager = FontManager.getDefaultFontManager();
	private Font toastResultSocreFont = fontManager.getToastResultScoreFont();
	private Font toastResultPanelFont = fontManager.getToastResultPanelFont();
	
	public ResultItemPanel(ScoreLabel label) {
		
		initContent(label);
		
		add(scoreLabel1);
		add(scoreLabel2);
		add(headPortrait);
		add(nickName);
		
		setLayout(null);
		setOpaque(false);
	}
	
	private void initContent(ScoreLabel label) {
		this.scoreLabel1.setHorizontalAlignment(JLabel.CENTER);
		this.scoreLabel1.setText(label.getScore() + "");
		this.scoreLabel1.setFont(toastResultSocreFont);
		this.scoreLabel1.setForeground(Color.RED);
		this.scoreLabel2.setText("·Ö");
		this.scoreLabel1.setFont(toastResultPanelFont);
		this.headPortrait.setImage(label.getHeadPortrait());
		this.headPortrait.setArc(15);
		this.nickName.setText(label.getNickName());
		this.scoreLabel1.setFont(toastResultPanelFont);
	}
	
	private void initBounds(int lineHeight) {
		FontMetrics defaultFontMetrics = getFontMetrics(toastResultPanelFont);
		int defaultFontHeight = defaultFontMetrics.getHeight();
		
		int scoreLabel1Width = SwingUtilities.computeStringWidth(getFontMetrics(toastResultSocreFont), this.scoreLabel1.getText());;
		int scoreLabel1Height = this.scoreLabel1.getFont().getSize();
		this.scoreLabel1.setBounds(45, lineHeight - scoreLabel1Height >> 1, 30, scoreLabel1Height);
		
		int scoreLabel2Width = SwingUtilities.computeStringWidth(defaultFontMetrics, this.scoreLabel2.getText());
		this.scoreLabel2.setBounds(50 + scoreLabel1Width, lineHeight - defaultFontHeight >> 1, scoreLabel2Width, defaultFontHeight);
		
		int headPortraitWidth = 40;
		this.headPortrait.setBounds(55 + scoreLabel1Width + scoreLabel2Width, lineHeight - headPortraitWidth >> 1, headPortraitWidth, headPortraitWidth);

		int nickNameWidth = SwingUtilities.computeStringWidth(defaultFontMetrics, this.nickName.getText());
		this.nickName.setBounds(60 + scoreLabel1Width + scoreLabel2Width + headPortraitWidth, lineHeight - defaultFontHeight >> 1, nickNameWidth, defaultFontHeight);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		initBounds(height);
	}
}
