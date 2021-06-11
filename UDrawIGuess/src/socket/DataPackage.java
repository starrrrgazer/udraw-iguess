package socket;

import java.io.Serializable;

/**
 * ���ݽ�����������
 * @see ClientInfo
 */
public class DataPackage implements Serializable {
	/**
	 * ���ݽ���ʱ���ݵ�����
	 */
	public enum DataType { CLIENT_LIST, LOST_CONNECTION, LOGIN, LOGOUT,
		BRUSH_POSITION, BRUSH_PIGMENT, BRUSH_SIZE, CANVAS_CLEAR, SYSTEM_MESSAGE, MESSAGE,
		GAME_PLAYING, GAME_ROUND, INIT_BRUSH, PAINTER, TOPIC, GAME_COUNT_DOWN, DRAWABLE, GAME_TIME,
		HINT, GUESSED, GAME_SHOW_TOPIC, GAME_SHOW_RESULT }
	
	private DataType type;
	private Object data;

	/**
	 * ���캯��
	 * @param type ��������
	 * @param data ��������
	 * @see ClientInfo
	 */
	public DataPackage(DataType type, Object data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * ��ȡ��������
	 * @return ��������
	 */
	public DataType getType() {
		return type;
	}

	/**
	 * ��ȡ��������
	 * @return ��������
	 */
	public Object getData() {
		return data;
	}
}
