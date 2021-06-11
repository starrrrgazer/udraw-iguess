package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端类
 * @see ClientInfo
 * @see ClientAction
 */
public class Client {
	
	private ClientInfo info;
	
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	/**
	 * 构造函数，创建一个客户端
	 * @param socket 连接服务端的socket
	 */
	public Client(Socket socket) {
		setSocket(socket);
	}

	/**
	 * 获取客户端的socket
	 * @return socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	private void setSocket(Socket socket) {
		this.socket = socket;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回客户端的相关信息
	 * @see ClientInfo
	 * @return ClientInfo
	 */
	public ClientInfo getInfo() {
		return info;
	}

	/**
	 * 设置客户端的相关信息
	 * @param info 客户端的相关信息
	 */
	public void setInfo(ClientInfo info) {
		this.info = info;
	}

	/**
	 * 判断socket是否已经关闭连接
	 * @return	true，已经关闭连接，false，没有关闭连接
	 */
	public boolean isClosed() {
		return socket.isClosed();
	}

	/**
	 * 关闭socket
	 */
	public void close() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 客户端接受信息
	 * @return object，即clienInfo类
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object receive() throws ClassNotFoundException, IOException {
		return input.readObject();
	}

	/**
	 * 客户端发送信息
	 * @param obj clientInfo类
	 * @throws IOException
	 */
	public void send(Object obj) throws IOException {
		output.writeObject(obj);
		output.flush();
	}
}
