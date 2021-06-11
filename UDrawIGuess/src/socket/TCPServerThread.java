package socket;

import ui.mergePanel.GamePanel;
import ui.mergePanel.MainFrame;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 服务端构建类，“创建游戏”后创建
 * @see ServerAction
 */
public class TCPServerThread extends Thread {
	/**
	 * 线程运行方法，建立一个监听config的端口的服务端，离开房间后关闭服务端
	 * @see Config
	 * @see ServerAction
	 */
	public void run() {
		Config.serving = true;
		Config.waiting = true;
		try {
			ServerSocket serverSocket = initServerSocket();
			initGamePanelHeader();
			ServerAction.updatePlayers();
			while (Config.serving) {
				try {
					Socket socket = serverSocket.accept();
					if (!Config.serving) {
						socket.close();
						break;
					}
					ServerAction.clientConnect(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ServerAction.close();
			ServerAction.clientListClear();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化服务端
	 * @return 服务端socket
	 * @see Config
	 */
	public ServerSocket initServerSocket() {
		Config.port = Config.udpPort;
		while (true) {
			try {
				ServerSocket serverSocket = new ServerSocket(++Config.port);
				return serverSocket;
			} catch (BindException e) {
				continue;
			} catch (IllegalArgumentException e) {
				Config.port = Config.udpPort;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initGamePanelHeader() {		
		GamePanel gamePanel = MainFrame.getMainFrame().getGamePanelOnly();
		gamePanel.getInfoPanel().updateInfo(Config.nickName + "的房间", Config.ip, Config.port);
		gamePanel.getHeaderPanel().gameWaiting();
	}

	/**
	 * 关闭服务端
	 */
	public static void closeServer() {
		new Thread() {
			public void run() {
				try {
					Socket socket = new Socket("127.0.0.1", Config.port);
					socket.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
