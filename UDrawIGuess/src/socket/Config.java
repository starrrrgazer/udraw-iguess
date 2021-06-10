package socket;

import java.util.UUID;

public class Config {
	
	/*进程标识*/
	public static String progressId = UUID.randomUUID().toString();

	/*用户头像*/
	public static int headPortrait = 1;

	/*用户昵称*/
	public static String nickName = "";
	
	/*服务器IP*/
	public static String ip ="127.0.0.1";
	
	/*服务器端口*/
	public static int port = 3141;
	
	/*多播端口*/
	public static int udpPort = 3141;

	/*房间容量*/
	public static int maximum = 4;

	/*当前房间人数*/
	public static int current = 0;

	/*房间是否创建*/
	public static boolean serving;
	
	/*等待游戏开始*/
	public static boolean waiting;

	/*客户头像*/
	public static int[] clientHeadPortrait = {0, 0, 0, 0};
}
