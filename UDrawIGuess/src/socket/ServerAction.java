package socket;

import ui.mergePanel.GamePanel;
import ui.mergePanel.MainFrame;
import socket.DataPackage.DataType;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


/**
 * 服务端的线程类
 * @see Client
 * @see DataPackage
 */
public class ServerAction extends Thread {
	
	private static ArrayList<Client> clientList = new ArrayList<Client>();
	
	private Client client;

	/**
	 * 构造函数,创立一个监听某一客户端的线程
	 * @param client 客户端的socket
	 */
	public ServerAction(Client client) {
		this.client = client;
	}

	/**
	 * 服务端的运行方法，
	 * 根据数据类型不同，执行不同操作
	 * @see DataPackage
	 */
	public void run() {
		GamePanel gamePanel = MainFrame.getMainFrame().getGamePanelOnly();
		while (!client.isClosed()) {
			try {
				DataPackage dtpg = (DataPackage) client.receive();
				switch (dtpg.getType()) {
				case LOGIN:
					if (!Config.waiting || ServerAction.clientList.size() >= Config.maximum) {
						connectionRefuse(client);
					} else {
						connectionAccept(client, dtpg);
					}
					break;
				case LOGOUT:
					clientLogout(client);
					break;
				case MESSAGE:
					String[] messages = dtpg.getData().toString().split("\r\n");
					int index = clientList.indexOf(client) + 1;
					ArrayList<Integer> list = GamePlayThread.checkAnswer(messages[2], index);
					if (list != null) {
						gamePanel.getScorePanel().guessed(list);
						sendData(DataType.GUESSED, list);
					} else {
						gamePanel.getChatPanel().appendMessage(messages[0], Integer.parseInt(messages[1]), messages[2]);
						gamePanel.getScorePanel().speaking(messages[2], index);
						sendData(DataType.MESSAGE, messages[0] + "\r\n" + messages[1] + "\r\n" + messages[2] + "\r\n" + index);
					}
					break;
				case BRUSH_POSITION:
					Object[] objects = (Object[]) dtpg.getData();
					gamePanel.getDrawPanel().draw((int) objects[0], (Point) objects[1]);
					sendDataPackage(dtpg);
					break;
				case BRUSH_PIGMENT:
					gamePanel.getDrawPanel().setPigment((Color) dtpg.getData());
					sendDataPackage(dtpg);
					break;
				case BRUSH_SIZE:
					gamePanel.getDrawPanel().setThickness((int) dtpg.getData());
					sendDataPackage(dtpg);
					break;
				case CANVAS_CLEAR:
					gamePanel.getDrawPanel().canvasClear();
					sendDataPackage(dtpg);
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				clientLogout(client);
			}
		}
	}

	/**
	 * 连接到当前服务端的客户端的列表
	 * @return 已连接的客户端的列表
	 */
	public static ArrayList<Client> getClientList() {
		return clientList;
	}

	/**
	 * 客户端连接
	 * @param socket 监听到的客户端的socket
	 */
	public static void clientConnect(Socket socket) {
		Client client = new Client(socket);
		ServerAction.addClient(client);
	}

	private static void addClient(Client client) {
		synchronized (clientList) {
			clientList.add(client);
			new ServerAction(client).start();
		}
	}

	/**
	 * 拒绝客户端的连接
	 * @param client 监听到的客户端
	 */
	public static void connectionRefuse(Client client) {
		synchronized (clientList) {
			String message = Config.waiting ? "该房间已开始游戏，请在游戏结束后加入。" : "该房间人数已满，请稍后加入。";
			sendDataToOne(DataType.LOGOUT, message, client);
			client.close();
			clientList.remove(client);
		}
	}

	/**
	 * 接受客户端的连接
	 * @param client 监听到的客户端
	 * @param dtpg	打包好的数据
	 * @see DataPackage
	 */
	public static void connectionAccept(Client client, DataPackage dtpg) {
		ClientInfo clientInfo = (ClientInfo) dtpg.getData();
		client.setInfo(clientInfo);
		sendData(DataType.LOGIN, null);
		MainFrame.getMainFrame().getGamePanelOnly().getChatPanel().appendSystemMessage(clientInfo.getNickName() + "加入了游戏。");
		sendData(DataType.SYSTEM_MESSAGE, clientInfo.getNickName() + "加入了游戏。");
		updatePlayers();
	}

	/**
	 * 更新已连接的客户端
	 */
	public static void updatePlayers() {
		ArrayList<ClientInfo> playerInfos = new ArrayList<ClientInfo>();
		for (int i = clientList.size() - 1; i >= 0; i--) {
			Client client = clientList.get(i);
			if (!client.isClosed()) {
				playerInfos.add(0, client.getInfo());
			} else {
				clientList.remove(i);
			}
		}
		ClientInfo serverInfo = new ClientInfo(Config.progressId, Config.nickName, Config.headPortrait);
		playerInfos.add(0, serverInfo);
		MainFrame.getMainFrame().getGamePanelOnly().getScorePanel().updatePlayers(playerInfos);
		sendData(DataType.CLIENT_LIST, playerInfos);
	}

	/**
	 * 打包数据，并且发送给客户端
	 * @param type 数据类型
	 * @param data 数据内容
	 * @see DataPackage
	 * @see ClientInfo
	 */
	public static void sendData(DataType type, Object data) {
		DataPackage dp = new DataPackage(type, data);
		sendDataPackage(dp);
	}

	/**
	 * 服务端接收到某客户端的数据后，发送数据给其他客户端
	 * @param type 数据类型
	 * @param data 数据内容
	 * @param exceptedClient 被排除的客户端
	 */
	public static void sendDataExceptOne(DataType type, Object data, Client exceptedClient) {
		DataPackage dp = new DataPackage(type, data);
		sendDataPackageExceptOne(dp, exceptedClient);
	}

	/**
	 * 发送数据给一个客户端
	 * @param type 数据类型
	 * @param data 数据内容
	 * @param specifiedClient 接收数据的客户端
	 */
	public static void sendDataToOne(DataType type, Object data, Client specifiedClient) {
		DataPackage dp = new DataPackage(type, data);
		sendDataPackageToOne(dp, specifiedClient);
	}

	/**
	 * 发送打包好的数据给已连接的客户端
	 * @param dp 打包好的数据
	 */
	public static void sendDataPackage(DataPackage dp) {
		for (int i = clientList.size() - 1; i >= 0; i--) {
			Client client = clientList.get(i);
			sendDataPackageToOne(dp, client);
		}
	}

	/**
	 * 服务端接收到某客户端的数据后，发送打包好的数据给其他客户端
	 * @param dp 打包好的数据
	 * @param exceptedClient 被排除的客户端
	 */
	public static void sendDataPackageExceptOne(DataPackage dp, Client exceptedClient) {
		for (int i = clientList.size() - 1; i >= 0 && clientList.get(i) != exceptedClient; i--) {
			Client client = clientList.get(i);
			sendDataPackageToOne(dp, client);
		}
	}

	/**
	 * 发送打包好的数据给客户端
	 * @param dp 打包好的数据
	 * @param specifiedClient 接收数据的客户端
	 */
	public static void sendDataPackageToOne(DataPackage dp, Client specifiedClient) {
		try {
			if (!specifiedClient.isClosed()) {
				specifiedClient.send(dp);
			}
		} catch (IOException e) {
			clientLogout(specifiedClient);
		}
	}
	
	private static void clientLogout(Client client) {
		synchronized (clientList) {
			client.close();
			ClientInfo clientInfo = client.getInfo();
			GamePanel gamePanel = MainFrame.getMainFrame().getGamePanelOnly();
			gamePanel.getChatPanel().appendSystemMessage(clientInfo.getNickName() + "离开了游戏。");
			sendData(DataType.SYSTEM_MESSAGE, clientInfo.getNickName() + "离开了游戏。");
			if (Config.waiting) {
				clientList.remove(client);
				updatePlayers();
			} else {
				int n = clientList.indexOf(client) + 1;
				GamePlayThread.lostConnection(n);
				gamePanel.getScorePanel().lostConnection(n);
				sendData(DataType.LOST_CONNECTION, n);
			}
		}
	}

	/**
	 * 关闭服务端
	 */
	public static void close() {
		for (Client client : clientList) {
			client.close();
		}
	}

	/**
	 * 清空已连接的客户端
	 */
	public static void clientListClear() {
		synchronized (clientList) {
			clientList.clear();
		}
	}
}
