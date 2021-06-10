package socket;

import java.io.Serializable;

public class ClientInfo implements Serializable {
	private String progressId;
	private String nickName;
	private int headPortrait;
	
	public ClientInfo(String progressId, String nickName, int headPortrait) {
		this.progressId = progressId;
		this.nickName = nickName;
		this.headPortrait = headPortrait;
	}
	
	public String getProgressId() {
		return progressId;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public int getHeadPortrait() {
		return headPortrait;
	}
	
	@Override
	public String toString() {
		return "id=" + progressId + ",name=" + nickName + "portrait=" + headPortrait;
	}
}
