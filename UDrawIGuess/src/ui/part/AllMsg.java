package ui.part;
import java.io.*;
import java.net.*;
public interface AllMsg 
{
	public void send(DatagramSocket ds,String IP,int uPort);   //���͵ķ���
	public void parse(DataInputStream dis);     //�����ķ���

}
