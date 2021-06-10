package socket;

import Manager.FileControl;
import ui.mergeFace.GamePanel;
import socket.DataPackage.DataType;
import ui.mergeFace.MainFrame;
import ui.part.gamepart.HeaderPanel;
import ui.part.gamepart.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class GamePlayThread extends Thread {
	public static int roundTotal = 2;
	
	private final int countDownTime = 3;
	private final int drawTime = 60;
	private final int showTopicTime = 5;
	private final int showResultTime = 10;
	private final int showHintTime = 10;
	
	private static String topic[];
	private static boolean guessable;
	private static ArrayList<Integer> guessStatus;
	private static int losterCount;
	
	@Override
	public void run() {

		playing: {
		
			Config.waiting = false;

			ArrayList<String> topics = getTopics();
			guessStatus = getGuessStatus();
			losterCount = 0;

			ArrayList<Client> clientList = ServerAction.getClientList();
			roundTotal = clientList.size() > 1 ? 2 : 3;
			
			GamePanel gamePanel = MainFrame.getMainFrame().getGamePanelOnly();
			gamePanel.gamePlaying(true);
			ServerAction.sendData(DataType.GAME_PLAYING, true);

			for (int round = 0; Config.serving && round < roundTotal; round++) {

				gamePanel.getHeaderPanel().setRoundLabel(round + 1 + " / " + roundTotal);
				ServerAction.sendData(DataType.GAME_ROUND, round + 1 + " / " + roundTotal);

				for (int painterN = 0; painterN < clientList.size() + 1; painterN++) {
					
					if (losterCount >= clientList.size()) {
						break;
					}

					int index = (int) (Math.random() * topics.size());
					topic = topics.remove(index).split(";");

					gamePanel.getBrushPanel().initBrush();
					ServerAction.sendData(DataType.INIT_BRUSH, null);

					Client client = null;
					ClientInfo painter;
					if (painterN == 0) {
						painter = new ClientInfo(Config.progressId, Config.nickName, Config.headPortrait);
						gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_TOPIC, topic[0]);
						ServerAction.sendData(DataType.PAINTER, painter);
					} else {
						client = clientList.get(painterN - 1);
						if (client.isClosed()) {
							continue;
						}
						painter = client.getInfo();
						gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_PAINTER, Config.nickName);
						ServerAction.sendData(DataType.PAINTER, painter);
						ServerAction.sendDataToOne(DataType.TOPIC, topic[0], client);
					}
					gamePanel.getHeaderPanel().setPainterHeadPortrait(painter.getHeadPortrait());
					gamePanel.getScorePanel().setPainter(painter);
					
					initGuessStatus();
					guessStatus.set(painterN, 2);

					for (int time = showTopicTime + drawTime + countDownTime; time > 0; time--) {
						
						if (!Config.serving) {
							break playing;
						}
						if (time > showTopicTime + drawTime) {				//µ¹¼ÆÊ±½×¶Î
							gamePanel.countDownBeforeDraw(time - drawTime - showTopicTime);
							ServerAction.sendData(DataType.GAME_COUNT_DOWN, time - drawTime - showTopicTime);
						} else if (time >= showTopicTime) {					//Äã»­ÎÒ²Â½×¶Î
							if (time == showTopicTime + drawTime) {
								gamePanel.countDownBeforeDraw(time - drawTime - showTopicTime);
								ServerAction.sendData(DataType.GAME_COUNT_DOWN, time - drawTime - showTopicTime);
								if (client == null) {
									gamePanel.getDrawPanel().setEnabled(true);
									gamePanel.getBrushPanel().setEnabled(true);
								} else {
									ServerAction.sendDataToOne(DataType.DRAWABLE, true, client);
								}
								guessable = true;
							}
							if (time == showTopicTime + drawTime - showHintTime) {
								if (client == null) {
									ServerAction.sendData(DataType.HINT, topic[0].length() + "¸ö×Ö");
								} else {
									gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_HINT, topic[0].length() + "¸ö×Ö");
									ServerAction.sendDataExceptOne(DataType.HINT, topic[0].length() + "¸ö×Ö", client);
								}
							} else if (time == showTopicTime + drawTime - showHintTime - showHintTime) {
								if (client == null) {
									ServerAction.sendData(DataType.HINT, topic[0].length() + "¸ö×Ö£¬" + topic[1]);
								} else {
									gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_HINT, topic[0].length() + "¸ö×Ö£¬" + topic[1]);
									ServerAction.sendDataExceptOne(DataType.HINT, topic[0].length() + "¸ö×Ö£¬" + topic[1], client);
								}
							}
							if (checkGuessStatus()) {
								time = showTopicTime;
							}
							gamePanel.getHeaderPanel().setTime(time - showTopicTime);
							ServerAction.sendData(DataType.GAME_TIME, time - showTopicTime);
							if (time == showTopicTime) {					//ÏÔÊ¾´ð°¸½×¶Î
								guessable = false;
								gamePanel.getDrawPanel().setEnabled(false);
								gamePanel.getBrushPanel().setEnabled(false);
								ServerAction.sendData(DataType.DRAWABLE, false);

								Toast.getDefaultToast().makeToastTopic(topic[0], showTopicTime * 1000, gamePanel.getDrawPanel());
								ServerAction.sendData(DataType.GAME_SHOW_TOPIC, topic[0] + "\r\n" + showTopicTime * 1000);
							}
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			Toast.getDefaultToast().makeToastResult(showResultTime * 1000, gamePanel.getDrawPanel());
			ServerAction.sendData(DataType.GAME_SHOW_RESULT, showResultTime * 1000);
			try {
				Thread.sleep(showResultTime * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gamePanel.gamePlaying(false);
			ServerAction.sendData(DataType.GAME_PLAYING, false);
			ServerAction.updatePlayers();

			Config.waiting = true;
		}
		MainFrame.getMainFrame().getGamePanelOnly().gamePlaying(false);
	}
	
	private ArrayList<String> getTopics() {
		ArrayList<String> topics = new ArrayList<String>();
		try {
			InputStream in = FileControl.getInputStream("file", "topics.txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			for (String st = null; (st = input.readLine()) != null;) {
				topics.add(st);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return topics;
	}
	
	private ArrayList<Integer> getGuessStatus() {
		ArrayList<Integer> guessStatus = new ArrayList<Integer>();
		for (int i = 0; i < ServerAction.getClientList().size() + 1; i++) {
			guessStatus.add(0);
		}
		return guessStatus;
	}
	
	private void initGuessStatus() {
		for (int i = 0; i < guessStatus.size(); i++) {
			if (guessStatus.get(i) > 0) {
				guessStatus.set(i, 0);
			}
			if (guessStatus.get(i) == -2) {
				guessStatus.set(i, -1);
			}
		}
	}
	
	private boolean checkGuessStatus() {
		for (int i = 0; i < guessStatus.size(); i++) {
			if (guessStatus.get(i) == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void lostConnection(int clientIndex) {
		if (guessStatus.get(clientIndex) == 2) {
			guessStatus.set(clientIndex, -2);
			losterCount++;
		} else if (guessStatus.get(clientIndex) >= 0) {
			guessStatus.set(clientIndex, -1);
			losterCount++;
		}
	}
	
	public static ArrayList<Integer> checkAnswer(String answer, int clientIndex) {
		if (guessable && answer.equals(topic[0])) {
			synchronized (guessStatus) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				if (guessStatus.get(clientIndex) == 0) {
					guessStatus.set(clientIndex, 1);
					int number = 0;
					int painter = 0;
					for (int i = 0; i < guessStatus.size(); i++) {
						int status = guessStatus.get(i);
						if (status == 1) {
							number++;
						} else if (Math.abs(status) == 2) {
							painter = i;
						}
					}
					list.add(painter);
					list.add(number == 1 ? 3 : 1);
					list.add(clientIndex);
					list.add(number == 1 ? 2 : 1);
				}
				return list;
			}
		}
		return null;
	}
}
