package DrawAndGuess;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class ImgMsg1 
{
	BufferedImage image;
	
	public ImgMsg1(BufferedImage image)
	{
		this.image = image;
	}
/*
	public void sendPicture(BufferedImage image)   //发送消息的方法 
	{            
		try 
		{
				dos.writeUTF("sendPicture");
				ImageMonitor monitor = new ImageMonitor(image);
				dos.writeObject(monitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	public void resivePicture() throws IOException, ClassNotFoundException   //接收消息的方法
	{    
		ImageMonitor monitor = (ImageMonitor) dis.readObject();
		BufferedImage image = monitor.getBufferedImage();
			drawpanel.image = image;
			drawpanel.repaint();
	}
	*/
	public void send(BufferedImage image)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream dos;
		try 
		{
			dos = new ObjectOutputStream(baos);
			dos.writeUTF("sendPicture");
			ImageMonitor monitor = new ImageMonitor(image);
			dos.writeObject(monitor);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
