package ui.mergePanel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JLabel;

import Manager.FontManager;
import socket.ClientAction;
import socket.Config;
import socket.ServerAction;
import socket.TCPServerThread;
import ui.part.*;
import ui.part.JumpButton;
import socket.DataPackage.DataType;
import ui.part.gamepart.*;

/**
 * 游戏界面
 * @see ServerInfoPanel
 * @see HeaderPanel
 * @see BrushConfigPanel
 * @see DrawPanel
 * @see ScorePanel
 * @see ChatPanel
 */
public class GamePanel extends FacePanel {
	
	private ServerInfoPanel infoPanel = new ServerInfoPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private BrushConfigPanel brushPanel = new BrushConfigPanel();
	private DrawPanel drawPanel = new DrawPanel();
	private ScorePanel scorePanel = new ScorePanel();
	private ChatPanel chatPanel = new ChatPanel();
	
	private JLabel countDown = new JLabel("3");

	private JumpButton back = new JumpButton("离 开");
	
	private Font countdownFont = FontManager.getDefaultFontManager().getThreeCountdownFont();

	/**
	 * 构造函数
	 * @param owner
	 * gamepanel的所有者，即mainframe
	 */
	public GamePanel(MainFrame owner) {
		super(owner);
		
		infoPanel.setBounds(40, 30, 540, 15);
		headerPanel.setBounds(40, 45, 540, 50);
		brushPanel.setBounds(40, 95, 60, 345);
		drawPanel.setBounds(100, 95, 480, 345);
		scorePanel.setBounds(40, 440, 540, 100);
		chatPanel.setBounds(580, 70, 180, 470);
		back.setBounds(600, 42, 48, 60);
		countDown.setBounds(290, 165, 100, 100);

		countDown.setHorizontalAlignment(JLabel.CENTER);
		countDown.setFont(countdownFont);
		countDown.setVisible(false);
		
		back.addMouseListener(this);
		
		add(countDown);
		add(infoPanel);
		add(headerPanel);
		add(drawPanel);
		add(scorePanel);
		add(back);
		add(brushPanel);
		add(chatPanel);
		
		setLayout(null);
		setEnabled(false);
	}

	/**
	 * 游戏开始
	 * @param playing 游戏是否开始,true 开始
	 */
	public void gamePlaying(boolean playing) {
		countDownBeforeDraw(0);
		brushPanel.initBrush();
		if (playing) {
			headerPanel.setInfoVisible(true);
			drawPanel.setEnabled(false);
			brushPanel.setEnabled(false);
			scorePanel.initScore("0");
		} else {
			headerPanel.setInfoVisible(false);
			drawPanel.setEnabled(true);
			brushPanel.setEnabled(true);
			scorePanel.setPainter(null);
			scorePanel.initScore("");
			chatPanel.chatPaneClear();
		}
	}

	/**
	 *
	 * @return
	 */
	public ServerInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public HeaderPanel getHeaderPanel() {
		return headerPanel;
	}

	public BrushConfigPanel getBrushPanel() {
		return brushPanel;
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}
	
	public void countDownBeforeDraw(int time) {
		countDown.setText(time + "");
		if (time > 0) {
			countDown.setVisible(true);
		} else {
			countDown.setVisible(false);
		}
	}
	
	public void quit() {
		gamePlaying(false);
		if (Config.serving) {
			Config.waiting = false;
			Config.serving = false;
			ServerAction.sendData(DataType.LOGOUT, null);
			TCPServerThread.closeServer();
			Toast.getDefaultToast().makeToastClear();
			Toast.getDefaultToast().makeToastNotice("房间已解散！", 1000);
			ComponentDropper.getDefaultDropper().switchPanel(owner.getCurrentPanel(), owner.getOnLinePanel());
		} else {
			ClientAction.sendData(DataType.LOGOUT, null);
			ClientAction.close();
		}
	}
	
	private void exit(final EventObject e) {
		if (isEnabled()) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), null, null);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(200);
						quit();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				};
			}.start();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		exit(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
