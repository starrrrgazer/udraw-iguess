package DrawAndGuess;
import java.io.*;
import java.net.*;
import java.util.*;
public class DrawAndGuessServe
{
int set = 0;
	private String NowAnswer = "";
	boolean started = false;  //���Ʒ��������Ƿ���
	boolean IsProblem = false;
	ServerSocket ss = null;
	public static final int UDP_PORT = 8888;
	public static final int TCP_PORT = 6666;
	private static int ID = 0;   //�û���ID��
	DrawAndGuess tc;
	int id =0;
	
	List<Client> clients = new ArrayList<Client>();
	
	
	public static void main(String[] args) 
	{
		new DrawAndGuessServe().start();
		
	}
	
	public void start()   //�����������˵ķ���
	{
		        new Thread(new UDPThread()).start();
		        
				try  //�����������˿�
				{
					ss = new ServerSocket(TCP_PORT);
					started = true;
				}	
				catch(BindException e)
				{
					System.out.println("�˿�ʹ���У�");
					System.out.println("��ر���س�������������");
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
						//boolean bconnected = false;  //���Ʒ��������Ƿ���յ��ͻ�����Ϣ
						Socket s = ss.accept();
						DataInputStream dis = new DataInputStream(s.getInputStream());
						String IP = s.getInetAddress().getHostAddress();
						int udpPort = dis.readInt();
						Client c = new Client(s,IP,udpPort);
//						CheckClient c1 = new CheckClient(tc);
						System.out.println("�ѽ������ӣ�");
						
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						dos.writeInt(ID++);
						new Thread(c).start();
						clients.add(c);
					}
				}
				catch(EOFException e)
				{
					System.out.println("�������ѶϿ�����");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				finally  //finally�����쳣ûcatch��ʱ����finally���д���  finally���һ����ִ�У��������ִ��
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
//			   while(bconnected)  //while���Ʒ������˲��ϵĶ�ȡ���ݲ���ӡ����
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
//				System.out.println("�������ѶϿ�����");
//			}
//			catch(Exception e)
//			{
//				System.out.println("�ѶϿ����ӣ�");
//			}
//			
//			finally  //finally�����쳣ûcatch��ʱ����finally���д���  finally���һ����ִ�У��������ִ��
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
	
	
	class Client implements Runnable   //����ϵͳ���߳�
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
				clients.remove(this); //��list��ȥ����ǰ����
				System.out.println("�пͻ����˳�������");
			}
		}
		
		public void send(String str ,int id)   //����������Ϣ�ķ���
		{
			
				try 
				{	dos.writeInt(id);
				 	System.out.println(++set);
					System.out.println("         " + id);
					dos.writeUTF(str);
					System.out.println(str);
				} catch (IOException e) 
				{
					clients.remove(this); //��list��ȥ����ǰ����
					System.out.println("�пͻ����˳�������");
					//e.printStackTrace();
				}
		}
		
		public void run()
		{
			try
			{
			   while(bconnected)  //while���Ʒ������˲��ϵĶ�ȡ���ݲ���ӡ����
			   {
				   id  =  dis.readInt();
				   if(id == 1){
					   String str = dis.readUTF();
					   System.out.println(str);
					   for(int i=0;i<clients.size();i++)
					   {
						   Client c = clients.get(i);
						   c.send(str,id);
						   System.out.println("�ɹ�����1");
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
							   System.out.println("�ɹ�����2");
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
//						   System.out.println("�ɹ�����3");
//					   }
//				   }
			   }
			}
			catch(EOFException e)
			{
				System.out.println("�������ѶϿ�����");
			}
			catch(Exception e)
			{
				System.out.println("�ѶϿ����ӣ�");
			}
			
			finally  //finally�����쳣ûcatch��ʱ����finally���д���  finally���һ����ִ�У��������ִ��
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
	
	public class UDPThread implements Runnable   //���տͻ��˵İ�������ȥ����
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
//System.out.println("�ӿͻ��˽ӵ�һ����������");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
