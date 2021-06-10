package ui.part;

import socket.ClientInfo;

import java.util.ArrayList;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	private ScoreLabel[] labels = new ScoreLabel[5];
	
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
	
	public void initScore(String value) {
		for (ScoreLabel label : labels) {
			if (label.isVisible()) {
				label.initScore(value);
			}
		}
	}
	
	public void speaking(String speaking, int index) {
		Toast.getDefaultToast().makeToastSpeaking(speaking, 1000, labels[index], index);
	}
	
	public void addScore(int score, int index) {
		Toast.getDefaultToast().makeToastAddScore(score, 1000, labels[index], index);
	}
	
	public void guessed(ArrayList<Integer> list) {
		if (list.size() > 0) {
			labels[list.get(2)].guessed(true);
			labels[list.get(0)].addScore(list.get(1));
			addScore(list.get(1), list.get(0));
			labels[list.get(2)].addScore(list.get(3));
			addScore(list.get(3), list.get(2));
		}
	}
	
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
	
	public void lostConnection(int i) {
		labels[i].lostConnection();
	}
	
	public int getGuessedCount() {
		int i = 0;
		for (ScoreLabel label : labels) {
			if (label.isVisible() && label.isGuessed()) {
				i++;
			}
		}
		return i;
	}
	
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
