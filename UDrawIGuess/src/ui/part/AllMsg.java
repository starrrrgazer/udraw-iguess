package ui.part;
import java.io.*;
import java.net.*;
public interface AllMsg 
{
	public void send(DatagramSocket ds,String IP,int uPort);   //发送的方法
	public void parse(DataInputStream dis);     //解析的方法

}
