//package ui.part;
//
////import ui.mergeFace.DGMainFrame;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetSocketAddress;
//import java.net.SocketException;
//
//public class PointNewMsg implements AllMsg
//{
////	DGMainFrame tc;
//
//	int id;
//	Point point;
//	boolean clear;
//	boolean cancel;
//
//	public PointNewMsg(int id, Point point,boolean clear,boolean cancel)
//	{
//		this.id = id;
//		this.point = point;
//		this.clear = clear;
//		this.cancel = cancel;
//	}
//
////	public PointNewMsg(DGMainFrame tc)
////	{
////		this.tc = tc;
////	}
//
//	public void send(DatagramSocket ds, String IP, int udpPort)
//	{
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		DataOutputStream dos = new DataOutputStream(baos);
//		try {
//			dos.writeInt(id);
//			dos.writeInt(point.x);
//			dos.writeInt(point.y);
//			dos.writeBoolean(point.f);
//			dos.writeInt(point.co);
//			dos.writeInt(point.cx);
//			dos.writeInt(point.tool);
//			dos.writeBoolean(clear);
//			dos.writeBoolean(cancel);
//			dos.writeUTF(this.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		byte[] buf = baos.toByteArray();
//		try {
//			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, udpPort));
//			ds.send(dp);
//		} catch (SocketException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void parse(DataInputStream dis)
//	{
//		try {
//			int id = dis.readInt();
//			if(tc.mp.pp.id==id) {
//				return;
//			}
//			int x = dis.readInt();
//			int y = dis.readInt();
//			boolean f = dis.readBoolean();
//			int co = dis.readInt();
//			int cx = dis.readInt();
//			int tool = dis.readInt();
//			boolean clear = dis.readBoolean();
//			boolean cancel = dis.readBoolean();
//
//			String s = dis.readUTF();
//
//			Point point = new Point(x, y,f,co,cx,tool);
//			tc.mp.pp.list.add(point);
//			tc.mp.pp.clear = clear;
//			tc.mp.pp.cancel = cancel;
//			if(tc.mp.pp.clear)
//				tc.mp.pp.list.clear();
//			if(tc.mp.pp.cancel)
//			{
//				if(tc.mp.pp.list.size()>=0)
//				{
//				  tc.mp.pp.list.remove(tc.mp.pp.list.get(tc.mp.pp.list.size()-1));
//				  int i = tc.mp.pp.list.size()-1;
//				  while(tc.mp.pp.list.get(tc.mp.pp.list.size()-2).f)
//				  {
//					  tc.mp.pp.list.remove(tc.mp.pp.list.get(i));
//					  i--;
//				  }
//				  if(i>=0)
//				  {
//					  tc.mp.pp.list.remove(tc.mp.pp.list.get(i));
//				  }
//				}
//			}
//			tc.mp.pp.repaint();
//
////System.out.println(tc);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
