package socket;

import java.util.UUID;

public class Config {
	
	/*���̱�ʶ*/
	public static String progressId = UUID.randomUUID().toString();

	/*�û�ͷ��*/
	public static int headPortrait = 1;

	/*�û��ǳ�*/
	public static String nickName = "";
	
	/*������IP*/
	public static String ip ="127.0.0.1";
	
	/*�������˿�*/
	public static int port = 3141;
	
	/*�ಥ�˿�*/
	public static int udpPort = 3141;

	/*��������*/
	public static int maximum = 4;

	/*��ǰ��������*/
	public static int current = 0;

	/*�����Ƿ񴴽�*/
	public static boolean serving;
	
	/*�ȴ���Ϸ��ʼ*/
	public static boolean waiting;

	/*�ͻ�ͷ��*/
	public static int[] clientHeadPortrait = {0, 0, 0, 0};
}
