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
	
	public BufferedImage getBufferedImage()  //�õ�ͼƬ����ķ���������һ��ͼƬ����
	{
		BufferedImage image = getDecompressedImage(imageData);
		return image;
	}
	
	//��ͼƬ������벢�����ֽ�������
	
	private byte[] getCompressedImage(BufferedImage image)   //����һ���Ѿ�����ͼ������
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

	//���ֽ������н����ͼƬ����
	
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

