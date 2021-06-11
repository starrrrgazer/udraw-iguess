package socket;

import java.io.Serializable;

/**
 * 数据交互的类型类
 * @see ClientInfo
 */
public class DataPackage implements Serializable {
	/**
	 * 数据交互时数据的类型
	 */
	public enum DataType { CLIENT_LIST, LOST_CONNECTION, LOGIN, LOGOUT,
		BRUSH_POSITION, BRUSH_PIGMENT, BRUSH_SIZE, CANVAS_CLEAR, SYSTEM_MESSAGE, MESSAGE,
		GAME_PLAYING, GAME_ROUND, INIT_BRUSH, PAINTER, TOPIC, GAME_COUNT_DOWN, DRAWABLE, GAME_TIME,
		HINT, GUESSED, GAME_SHOW_TOPIC, GAME_SHOW_RESULT }
	
	private DataType type;
	private Object data;

	/**
	 * 构造函数
	 * @param type 数据类型
	 * @param data 数据内容
	 * @see ClientInfo
	 */
	public DataPackage(DataType type, Object data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * 获取数据类型
	 * @return 数据类型
	 */
	public DataType getType() {
		return type;
	}

	/**
	 * 获取数据内容
	 * @return 数据内容
	 */
	public Object getData() {
		return data;
	}
}
