package DrawAndGuess;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageMonitor implements Serializable
{
    byte[] imageData = null;
	
	public ImageMonitor(BufferedImage image)
	{
		imageData = getCompressedImage(image);
	}
	
	public BufferedImage getBufferedImage()  //得到图片对象的方法，返回一个图片对象
	{
		BufferedImage image = getDecompressedImage(imageData);
		return image;
	}
	
	//将图片对象编码并存在字节数组中
	
	private byte[] getCompressedImage(BufferedImage image)   //返回一个已经存完图的数组
	{
		byte[] imageData = null;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			imageData = baos.toByteArray();
		} catch (IOException ex)
		{
			imageData = null;
		}
		return imageData;
	}

	//从字节数组中解码出图片对象
	
	private BufferedImage getDecompressedImage(byte[] imageData) 
	{
		try
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
			return ImageIO.read(bais);
		} catch (IOException ex)
		{
			return null;
		}
	}
}

