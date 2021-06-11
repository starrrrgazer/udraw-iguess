package socket;

import java.io.Serializable;

/**
 * 客户端和服务端通信的内容类
 */
public class ClientInfo implements Serializable {
	private String progressId;
	private String nickName;
	private int headPortrait;

	/**
	 * 构造函数
	 * @param progressId 线程ID
	 * @param nickName	客户端的昵称
	 * @param headPortrait 客户端选择的头像
	 */
	public ClientInfo(String progressId, String nickName, int headPortrait) {
		this.progressId = progressId;
		this.nickName = nickName;
		this.headPortrait = headPortrait;
	}

	/**
	 * 获取线程ID
	 * @return 线程ID的字符串
	 */
	public String getProgressId() {
		return progressId;
	}

	/**
	 * 获取客户端的昵称
	 * @return 昵称的字符串
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 获取头像
	 * @return 头像数组的索引
	 * @see Manager.ImageManager#getHeadPortraits(int)
	 */
	public int getHeadPortrait() {
		return headPortrait;
	}

	/**
	 * 重写tostring
	 * @return string
	 */
	@Override
	public String toString() {
		return "id=" + progressId + ",name=" + nickName + "portrait=" + headPortrait;
	}
}
