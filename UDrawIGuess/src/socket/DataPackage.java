package socket;

import java.io.Serializable;

public class DataPackage implements Serializable {
	public enum DataType { CLIENT_LIST, LOST_CONNECTION, LOGIN, LOGOUT,
		BRUSH_POSITION, BRUSH_PIGMENT, BRUSH_SIZE, CANVAS_CLEAR, SYSTEM_MESSAGE, MESSAGE,
		GAME_PLAYING, GAME_ROUND, INIT_BRUSH, PAINTER, TOPIC, GAME_COUNT_DOWN, DRAWABLE, GAME_TIME,
		HINT, GUESSED, GAME_SHOW_TOPIC, GAME_SHOW_RESULT }
	
	private DataType type;
	private Object data;
	
	public DataPackage(DataType type) {
		this(type, null);
	}
	
	public DataPackage(DataType type, Object data) {
		this.type = type;
		this.data = data;
	}
	
	public DataType getType() {
		return type;
	}

	public Object getData() {
		return data;
	}
}
