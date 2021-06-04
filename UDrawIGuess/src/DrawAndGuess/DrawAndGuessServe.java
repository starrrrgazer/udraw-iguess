package DrawAndGuess;
import java.io.*;
import java.net.*;
import java.util.*;
public class DrawAndGuessServe
{
int set = 0;
	private String NowAnswer = "";
	boolean started = false;  //控制服务器端是否开启
	boolean IsProblem = false;
	ServerSocket ss = null;
	public static final int UDP_PORT = 8888;
	public static final int TCP_PORT = 6666;
	private static int ID = 0;   //用户的ID号
	DrawAndGuess tc;
	int id =0;
	
	List<Client> clients = new ArrayList<Client>();
	
	
	public static void main(String[] args) 
	{
		new DrawAndGuessServe().start();
		
	}
	
	public void start()   //开启服务器端的方法
	{
		        new Thread(new UDPThread()).start();
		        
				try  //建立服务器端口
				{
					ss = new ServerSocket(TCP_PORT);
					started = true;
				}	
				catch(BindException e)
				{
					System.out.println("端口使用中！");
					System.out.println("请关闭相关程序并重新启动！");
					System.exit(0);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
				try
				{
					
					while(started)
					{
						//boolean bconnected = false;  //控制服务器端是否接收到客户端信息
						Socket s = ss.accept();
						DataInputStream dis = new DataInputStream(s.getInputStream());
						String IP = s.getInetAddress().getHostAddress();
						int udpPort = dis.readInt();
						Client c = new Client(s,IP,udpPort);
//						CheckClient c1 = new CheckClient(tc);
						System.out.println("已建立连接！");
						
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						dos.writeInt(ID++);
						new Thread(c).start();
						clients.add(c);
					}
				}
				catch(EOFException e)
				{
					System.out.println("服务器已断开连接");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				finally  //finally是有异常没catch到时，在finally是中处理  finally语句一定会执行，且在最后执行
				{
					try 
					{
						ss.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}			
				}
	}
//	class CheckClient implements Runnable
//	{
//		NHWC tc;
//		private Socket s = null;
//		private DataInputStream dis = null;
//		private DataOutputStream dos = null;
//		private boolean bconnected = false;
//		
//		public CheckClient(NHWC tc){
//			this.tc = tc;
//			
//			try 
//			{
//				dis = new DataInputStream(s.getInputStream());
//				dos = new DataOutputStream(s.getOutputStream());
//				bconnected = true;	
//			} 
//			catch (IOException e) 
//			{
//				e.printStackTrace();
//			}
//		}

//		
//		public void run(){
//			try
//			{
//			   while(bconnected)  //while控制服务器端不断的读取数据并打印出来
//			   {
//				   IsProblem = dis.readBoolean();
//				   System.out.println(IsProblem);
//				   
//				  if(IsProblem){
//					  String nowAnswer =  dis.readUTF();
//					  System.out.println(nowAnswer);
//					  for(int i=0;i<clients.size();i++)
//				      {
//				    	  Client c = clients.get(i);
//				    	  c.send(nowAnswer);
//				      }
//				  }
//			   }
//			}
//			catch(EOFException e)
//			{
//				System.out.println("服务器已断开连接");
//			}
//			catch(Exception e)
//			{
//				System.out.println("已断开连接！");
//			}
//			
//			finally  //finally是有异常没catch到时，在finally是中处理  finally语句一定会执行，且在最后执行
//			{
//				try {
//					if(dis!=null)
//					   dis.close();
//					if(dos!=null)
//						dos.close();
//					if(s!=null)
//					   s.close();
//				} catch (IOException e1) 
//				{
//					e1.printStackTrace();
//				}
//				
//			}
//		}
//	}
	
	
	class Client implements Runnable   //聊天系统的线程
	{ 
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bconnected = false;
		
		String IP;
		int udpPort;
		
		public Client()
		{
			
		}
		
		public Client(Socket s,String IP,int udpPort)
		{
			this.s = s;
			this.IP = IP;
			this.udpPort = udpPort;
			
			try 
			{
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bconnected = true;	
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}

		public void sendInt(int num , int id){
			try {
				dos.writeInt(id);
				System.out.println(++set);
				System.out.println("         " + id);
				dos.writeInt(num);
				System.out.println(num);
			} catch (Exception e) {
				clients.remove(this); //从list中去掉当前对象
				System.out.println("有客户端退出！！！");
			}
		}
		
		public void send(String str ,int id)   //发送聊天信息的方法
		{
			
				try 
				{	dos.writeInt(id);
				 	System.out.println(++set);
					System.out.println("         " + id);
					dos.writeUTF(str);
					System.out.println(str);
				} catch (IOException e) 
				{
					clients.remove(this); //从list中去掉当前对象
					System.out.println("有客户端退出！！！");
					//e.printStackTrace();
				}
		}
		
		public void run()
		{
			try
			{
			   while(bconnected)  //while控制服务器端不断的读取数据并打印出来
			   {
				   id  =  dis.readInt();
				   if(id == 1){
					   String str = dis.readUTF();
					   System.out.println(str);
					   for(int i=0;i<clients.size();i++)
					   {
						   Client c = clients.get(i);
						   c.send(str,id);
						   System.out.println("成功发送1");
					   }
				   }
				   else if(id == 2){
					     NowAnswer = dis.readUTF();
					    System.out.println(NowAnswer);
					    System.out.println(++set);
					    for(int i=0;i<clients.size();i++)
						   {
							   Client c = clients.get(i);
							   c.send(NowAnswer,id);
							   System.out.println("成功发送2");
						   }
				   }
//				   else if(id == 3){
//					   int Score = dis.readInt();
//					   System.out.println(Score);
//					   System.out.println(++set);
//					   for(int i=0;i<clients.size();i++)
//					   {
//						   Client c = clients.get(i);
//						   c.sendInt(Score, id);
//						   System.out.println("成功发送3");
//					   }
//				   }
			   }
			}
			catch(EOFException e)
			{
				System.out.println("服务器已断开连接");
			}
			catch(Exception e)
			{
				System.out.println("已断开连接！");
			}
			
			finally  //finally是有异常没catch到时，在finally是中处理  finally语句一定会执行，且在最后执行
			{
				try {
					if(dis!=null)
					   dis.close();
					if(dos!=null)
						dos.close();
					if(s!=null)
					   s.close();
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	public class UDPThread implements Runnable   //接收客户端的包并发出去的类
	{
		byte[] buf = new byte[2048];

		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
System.out.println("Server's UDP thread started at port : " + UDP_PORT);
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.IP, c.udpPort));
						ds.send(dp);
					}
//System.out.println("从客户端接到一个包！！！");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
