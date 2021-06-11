package socket;

import java.io.Serializable;

/**
 * �ͻ��˺ͷ����ͨ�ŵ�������
 */
public class ClientInfo implements Serializable {
	private String progressId;
	private String nickName;
	private int headPortrait;

	/**
	 * ���캯��
	 * @param progressId �߳�ID
	 * @param nickName	�ͻ��˵��ǳ�
	 * @param headPortrait �ͻ���ѡ���ͷ��
	 */
	public ClientInfo(String progressId, String nickName, int headPortrait) {
		this.progressId = progressId;
		this.nickName = nickName;
		this.headPortrait = headPortrait;
	}

	/**
	 * ��ȡ�߳�ID
	 * @return �߳�ID���ַ���
	 */
	public String getProgressId() {
		return progressId;
	}

	/**
	 * ��ȡ�ͻ��˵��ǳ�
	 * @return �ǳƵ��ַ���
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * ��ȡͷ��
	 * @return ͷ�����������
	 * @see Manager.ImageManager#getHeadPortraits(int)
	 */
	public int getHeadPortrait() {
		return headPortrait;
	}

	/**
	 * ��дtostring
	 * @return string
	 */
	@Override
	public String toString() {
		return "id=" + progressId + ",name=" + nickName + "portrait=" + headPortrait;
	}
}
