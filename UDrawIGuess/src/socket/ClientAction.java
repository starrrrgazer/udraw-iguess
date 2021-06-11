package socket;

import ui.mergePanel.GamePanel;
import ui.mergePanel.MainFrame;
import ui.part.ComponentDropper;
import ui.part.gamepart.HeaderPanel;
import ui.part.gamepart.Toast;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import socket.DataPackage;

/**
 * 客户端的线程类，加入游戏后创建
 * @see Client
 * @see ClientInfo
 */
public class ClientAction extends Thread {
	
	private static Client client;

	/**
	 * 构造函数。
	 * 新建一个连接到服务端的socket
	 * @see Client
	 */
	public ClientAction() {
		try {
			Socket socket = new Socket(Config.ip, Config.port);
			client = new Client(socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 线程的运行方法
	 * 发送登录信息后，等待服务端的信息
	 * 根据收到消息的不同，执行不同命令
	 * @see DataPackage
	 * @see ClientInfo
	 */
	public void run() {
		ClientInfo clientInfo = new ClientInfo(Config.progressId, Config.nickName, Config.headPortrait);
		DataPackage dp = new DataPackage(DataPackage.DataType.LOGIN, clientInfo);
		sendDataPackage(dp);
		MainFrame mainFrame = MainFrame.getMainFrame();
		GamePanel gamePanel = mainFrame.getGamePanelOnly();
		while (!client.isClosed()) {
			try {
				DataPackage dtpg = (DataPackage) client.receive();
				switch (dtpg.getType()) {
				case LOGIN:
					ComponentDropper.getDefaultDropper().switchPanel(mainFrame.getCurrentPanel(), mainFrame.getGamePanel());
					break;
				case LOGOUT:
					logout("房主已经解散了游戏。");
					gamePanel.gamePlaying(false);
					break;
				case CLIENT_LIST:
					ArrayList<ClientInfo> playerInfos = (ArrayList<ClientInfo>) dtpg.getData();
					gamePanel.getInfoPanel().updateInfo(playerInfos.get(0).getNickName() + "的房间", Config.ip, Config.port);
					gamePanel.getHeaderPanel().gameWaiting();
					gamePanel.getScorePanel().updatePlayers(playerInfos);
					break;
				case SYSTEM_MESSAGE:
					gamePanel.getChatPanel().appendSystemMessage(dtpg.getData().toString());
					break;
				case MESSAGE:
					String[] messages = dtpg.getData().toString().split("\r\n");
					gamePanel.getChatPanel().appendMessage(messages[0], Integer.parseInt(messages[1]), messages[2]);
					MainFrame.getMainFrame().getGamePanel().getScorePanel().speaking(messages[2], Integer.parseInt(messages[3]));
					break;
				case GAME_PLAYING:
					gamePanel.gamePlaying((boolean) dtpg.getData());
					break;
				case GAME_ROUND:
					gamePanel.getHeaderPanel().setRoundLabel(dtpg.getData().toString());
					break;
				case INIT_BRUSH:
					gamePanel.getBrushPanel().initBrush();
					break;
				case PAINTER:
					ClientInfo painter = (ClientInfo) dtpg.getData();
					if (Config.progressId.equals(painter.getProgressId())) {
						gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_PAINTER, painter.getNickName());
						gamePanel.getHeaderPanel().setPainterHeadPortrait(painter.getHeadPortrait());
					}
					gamePanel.getScorePanel().setPainter(painter);
					break;
				case TOPIC:
					gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_TOPIC, dtpg.getData().toString());
					break;
				case DRAWABLE:
					boolean drawable = (boolean) dtpg.getData();
					gamePanel.getDrawPanel().setEnabled(drawable);
					gamePanel.getBrushPanel().setEnabled(drawable);
					break;
				case GAME_COUNT_DOWN:
					gamePanel.countDownBeforeDraw((int) dtpg.getData());
					break;
				case GAME_TIME:
					gamePanel.getHeaderPanel().setTime((int) dtpg.getData());
					break;
				case HINT:
					gamePanel.getHeaderPanel().setNotice(HeaderPanel.NoticeType.SHOW_HINT, dtpg.getData().toString());
					break;
				case GAME_SHOW_TOPIC:
					String[] strs = dtpg.getData().toString().split("\r\n");
					Toast.getDefaultToast().makeToastTopic(strs[0], Integer.parseInt(strs[1]), gamePanel.getDrawPanel());
					break;
				case GUESSED:
					ArrayList<Integer> list = (ArrayList<Integer>) dtpg.getData();
					gamePanel.getScorePanel().guessed(list);
					break;
				case GAME_SHOW_RESULT:
					Toast.getDefaultToast().makeToastResult((int) dtpg.getData(), gamePanel.getDrawPanel());
					break;
				case LOST_CONNECTION:
					int i = (int) dtpg.getData();
					gamePanel.getScorePanel().lostConnection(i);
					break;
				case BRUSH_POSITION:
					Object[] objects = (Object[]) dtpg.getData();
					gamePanel.getDrawPanel().draw((int) objects[0], (Point) objects[1]);
					break;
				case BRUSH_PIGMENT:
					gamePanel.getDrawPanel().setPigment((Color) dtpg.getData());
					break;
				case BRUSH_SIZE:
					gamePanel.getDrawPanel().setThickness((int) dtpg.getData());
					break;
				case CANVAS_CLEAR:
					gamePanel.getDrawPanel().canvasClear();
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				logout("与房间失去连接。");
			}
		}
	}

	/**
	 * 客户端的退出房间的方法
	 * 从游戏界面退回到登入界面
	 * @param message 
	 * 退出房间时显示的信息，包括“”和“房主已经解散了游戏”和“与房间失去了连接”
	 */
	public static void logout(String message) {
		Toast.getDefaultToast().makeToastClear();
		Toast.getDefaultToast().makeToastNotice(message, 1000);
		MainFrame mainFrame = MainFrame.getMainFrame();
		ComponentDropper.getDefaultDropper().switchPanel(mainFrame.getCurrentPanel(), mainFrame.getOnLinePanel());
		close();
	}

	/**
	 * 打包需要发送的数据,并发送给服务端
	 * @param type 数据的类型，参考Datapackage.Datatype
	 * @param data 数据内容，参考clientinfo
	 * @see DataPackage
	 * @see ClientInfo
	 * @see ClientAction#sendDataPackage(DataPackage) 
	 */
	public static void sendData(DataPackage.DataType type, Object data) {
		DataPackage dp = new DataPackage(type, data);
		sendDataPackage(dp);
	}

	/**
	 * 发送数据给服务端
	 * @param dp 已经打包好的数据
	 * @see ClientAction#sendData(DataPackage.DataType, Object)    
	 */
	public static void sendDataPackage(DataPackage dp) {
		if (client != null && !client.isClosed()) {
			try {
				client.send(dp);
			} catch (IOException e) {
				close();
			}
		}
	}

	/**
	 * 关闭客户端
	 */
	public static void close() {
		if (client != null) {
			client.close();
		}
	}
}
