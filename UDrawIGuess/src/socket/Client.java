package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * �ͻ�����
 * @see ClientInfo
 * @see ClientAction
 */
public class Client {
	
	private ClientInfo info;
	
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	/**
	 * ���캯��������һ���ͻ���
	 * @param socket ���ӷ���˵�socket
	 */
	public Client(Socket socket) {
		setSocket(socket);
	}

	/**
	 * ��ȡ�ͻ��˵�socket
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
	 * ���ؿͻ��˵������Ϣ
	 * @see ClientInfo
	 * @return ClientInfo
	 */
	public ClientInfo getInfo() {
		return info;
	}

	/**
	 * ���ÿͻ��˵������Ϣ
	 * @param info �ͻ��˵������Ϣ
	 */
	public void setInfo(ClientInfo info) {
		this.info = info;
	}

	/**
	 * �ж�socket�Ƿ��Ѿ��ر�����
	 * @return	true���Ѿ��ر����ӣ�false��û�йر�����
	 */
	public boolean isClosed() {
		return socket.isClosed();
	}

	/**
	 * �ر�socket
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
	 * �ͻ��˽�����Ϣ
	 * @return object����clienInfo��
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object receive() throws ClassNotFoundException, IOException {
		return input.readObject();
	}

	/**
	 * �ͻ��˷�����Ϣ
	 * @param obj clientInfo��
	 * @throws IOException
	 */
	public void send(Object obj) throws IOException {
		output.writeObject(obj);
		output.flush();
	}
}
