package ui.part.gamepart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Manager.FileControl;
import Manager.FontManager;
import Manager.ImageManager;
import socket.Config;
import socket.GamePlayThread;
import socket.ServerAction;
import ui.part.HeadPortraitLabel;

/**
 * 游戏头部信息panel
 * 未开始游戏时显示“开始游戏”按钮或等待房主开始游戏
 * 开始游戏后显示轮数和题目或提示
 */
public class HeaderPanel extends JPanel implements MouseListener {
	/**
	 * 提示类型，包括题目，当前画图的人，提示
	 */
	public enum NoticeType { SHOW_TOPIC, SHOW_PAINTER, SHOW_HINT }
	
	private JPanel infoPanel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getCountdownBg();
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(img, countDown.getX(), countDown.getY(), countDown.getWidth(), countDown.getHeight(), this);
		}
	};
	private HeadPortraitLabel headPortrait = new HeadPortraitLabel(0, 10);
	private JLabel roundLabel = new JLabel("第 0 / 0 轮");
	private JLabel noticeLeft = new JLabel();
	private JLabel noticeRight = new JLabel();
	private JLabel countDown = new JLabel("0");
	
	private JPanel noticePanel = new JPanel();
	private MyButton startGame = new MyButton("开始游戏");
	private JLabel noticeLabel = new JLabel("等待房主开始游戏");
	
	private FontManager fontManager = FontManager.getDefaultFontManager();
	private Font headerNoticeFont = fontManager.getHeaderNoticeFont();
	private Font gameTimeFont = fontManager.getGameTimeFont();

	/**
	 * 构造函数
	 */
	public HeaderPanel() {
		
		startGame.setForeground(new Color(128, 0, 0));
		noticeRight.setForeground(Color.RED);
		noticeLabel.setFont(headerNoticeFont);
		roundLabel.setFont(headerNoticeFont);
		noticeLeft.setFont(headerNoticeFont);
		noticeRight.setFont(headerNoticeFont);
		countDown.setFont(gameTimeFont);
		countDown.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.setLayout(null);
		infoPanel.add(headPortrait);
		headPortrait.setVisible(false);
		infoPanel.add(roundLabel);
		infoPanel.add(noticeLeft);
		infoPanel.add(noticeRight);
		infoPanel.add(countDown);
		infoPanel.setOpaque(false);
		infoPanel.setVisible(false);
		
		noticeLabel.setHorizontalAlignment(JLabel.CENTER);
		noticePanel.setLayout(null);
		noticePanel.add(startGame);
		noticePanel.add(noticeLabel);
		noticePanel.setOpaque(false);

		startGame.addMouseListener(this);
		
		add(infoPanel);
		add(noticePanel);
		setLayout(null);
		setOpaque(false);
	}

	/**
	 * 等待开始游戏时，设置“开始游戏”按钮课件，提示panel不可见
	 */
	public void gameWaiting() {
		startGame.setVisible(Config.serving);
		noticeLabel.setVisible(!Config.serving);
	}

	/**
	 * 设置信息面板是否显示
	 * @param visible true，显示，false，不显示
	 */
	public void setInfoVisible(boolean visible) {
		infoPanel.setVisible(visible);
		noticePanel.setVisible(!visible);
	}

	/**
	 * 显示当前画的人的头像
	 * @param headPortrait 头像数组的索引
	 */
//	public void setPainterHeadPortrait(int headPortrait) {
//		BufferedImage img = FileControl.getBufferedImage("avatar/" + (headPortrait + 100 + "").substring(1) + ".jpg");
////		this.headPortrait.setImage(img);
//	}

	/**
	 * 设置轮数显示
	 * @param round 当前的轮数
	 */
	public void setRoundLabel(String round) {
		roundLabel.setText("第 " + round + " 轮");
	}

	/**
	 * 设置提示消息
	 * @param type 提示类型
	 * @param notice 提示内容
	 */
	public void setNotice(NoticeType type, String notice) {
		if (type == NoticeType.SHOW_TOPIC) {
			noticeLeft.setText("题目是：");
		} else if (type == NoticeType.SHOW_PAINTER) {
			noticeLeft.setText("现在画图的是：");
		} else {
			noticeLeft.setText("提示：");
		}
		noticeRight.setText(notice);
		
		int width = getWidth();
		int labelWidth = SwingUtilities.computeStringWidth(getFontMetrics(headerNoticeFont), noticeLeft.getText());
		int noticeWidth = SwingUtilities.computeStringWidth(getFontMetrics(headerNoticeFont), notice);
		
		noticeLeft.setBounds(width - labelWidth - noticeWidth >> 1, 25, labelWidth, 20);
		noticeRight.setBounds(width + labelWidth - noticeWidth >> 1, 25, noticeWidth, 20);
	}

	/**
	 * 设置倒计时
	 * @param time 剩余的时间
	 */
	public void setTime(int time) {
		countDown.setText(time + "");
	}

	/**
	 * 方法重写，设置整个headpanel的同时，设置各个组件的位置和大小
	 * @param x 水平偏移量
	 * @param y 垂直偏移量
	 * @param width 宽度
	 * @param height 高度
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		infoPanel.setBounds(0, 0, width, height);

//		headPortrait.setBounds(40, 5, 40, 40);
		int roundWidth = SwingUtilities.computeStringWidth(getFontMetrics(headerNoticeFont), roundLabel.getText());
		roundLabel.setBounds(width - roundWidth >> 1, 5, roundWidth, 20);
		countDown.setBounds(width - 35 - height, 5, height - 10, height - 10);
		
		noticePanel.setBounds(0, 0, width, height);
		startGame.setBounds(width - 104 >> 1, 10, 104, 30);
		noticeLabel.setBounds(width - 104 >> 1, 0, 104, height);
	}

	/**
	 * 游戏开始按钮，根据当前房间人数是否>=2，显示不同内容
	 * @param e 鼠标
	 */
	private void buttonPressed(EventObject e) {
		if (ServerAction.getClientList().isEmpty()) {
			Toast.getDefaultToast().makeToastNotice("请邀请小伙伴们一起玩吧！", 1000);
		} else {
			new GamePlayThread().start();
		}
	}

	/**
	 * 鼠标点击
	 * @param e 鼠标事件
	 */
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
