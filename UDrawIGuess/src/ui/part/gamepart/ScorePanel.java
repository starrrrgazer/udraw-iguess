package ui.part.gamepart;

import socket.ClientInfo;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 得分信息
 * @see ScoreLabel
 */
public class ScorePanel extends JPanel {
	
	private ScoreLabel[] labels = new ScoreLabel[5];

	/**
	 * 构造函数
	 */
	public ScorePanel() {
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new ScoreLabel();
			labels[i].setBounds(i* 110, 0, 100, 100);
			labels[i].setVisible(false);
			add(labels[i]);
		}
		
		setLayout(null);
		setOpaque(false);
	}

	/**
	 * 设置绘画人的信息
	 * @param painter clientinfo
	 * @see ClientInfo
	 */
	public void setPainter(ClientInfo painter) {
		for (ScoreLabel label : labels) {
			if (painter != null && painter.getProgressId().equals(label.getProgressId())) {
				label.setPainter(true);
			} else {
				label.setPainter(false);
			}
		}
		initGuessed();
	}
	
	private void initGuessed() {
		for (ScoreLabel label : labels) {
			label.guessed(false);
		}
	}

	/**
	 * 初始化得分
	 * @param value 分值
	 */
	public void initScore(String value) {
		for (ScoreLabel label : labels) {
			if (label.isVisible()) {
				label.initScore(value);
			}
		}
	}

	/**
	 * 得分加分显示
	 * @param speaking 加分内容
	 * @param index 得分label的索引
	 */
	public void speaking(String speaking, int index) {
		Toast.getDefaultToast().makeToastSpeaking(speaking, 1000, labels[index], index);
	}

	/**
	 * 得分加分
	 * @param score 分值
	 * @param index label的索引
	 */
	public void addScore(int score, int index) {
		Toast.getDefaultToast().makeToastAddScore(score, 1000, labels[index], index);
	}

	/**
	 * 根据各个玩家的猜测结果进行加分
	 * @param list 玩家的猜的结果列表
	 */
	public void guessed(ArrayList<Integer> list) {
		if (list.size() > 0) {
			labels[list.get(2)].guessed(true);
			labels[list.get(0)].addScore(list.get(1));
			addScore(list.get(1), list.get(0));
			labels[list.get(2)].addScore(list.get(3));
			addScore(list.get(3), list.get(2));
		}
	}

	/**
	 * 更新玩家
	 * @param clientInfos 玩家们的信息列表
	 */
	public void updatePlayers(ArrayList<ClientInfo> clientInfos) {
		for (int i = 0; i < clientInfos.size(); i++) {
			ClientInfo clientInfo = clientInfos.get(i);
			labels[i].setClient(clientInfo.getProgressId(), clientInfo.getNickName(), clientInfo.getHeadPortrait());
			labels[i].repaint();
			labels[i].setVisible(true);
		}
		for (int i = clientInfos.size(); i < labels.length; i++) {
			labels[i].setClient("", "", 0);
			labels[i].repaint();
			labels[i].setVisible(false);
		}
	}

	/**
	 * 玩家断开连接的处理函数
	 * @param i
	 */
	public void lostConnection(int i) {
		labels[i].lostConnection();
	}

	/**
	 * 获取猜对的数量
	 * @return ing，猜对的数量
	 */
	public int getGuessedCount() {
		int i = 0;
		for (ScoreLabel label : labels) {
			if (label.isVisible() && label.isGuessed()) {
				i++;
			}
		}
		return i;
	}

	/**
	 * 获取玩家得分label列表
	 * @return 玩家得分的label列表
	 */
	public ArrayList<ScoreLabel> getUsersScore() {
		ArrayList<ScoreLabel> list = new ArrayList<ScoreLabel>();
		for (ScoreLabel label : labels) {
			if (label.isVisible()) {
				list.add(label);
			}
		}
		return list;
	}
}
