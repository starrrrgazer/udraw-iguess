package Manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

/**
 * �ļ�������
 */
public class FileControl {
    //��Ŀ�ļ�����λ��srcĿ¼�µ�����3�����ļ���֮һ
    private static final String FILE = "FileResource/file/";	//�����ͨ�ļ�
    private static final String IMAGE = "FileResource/image/";	//���ͼƬ�ļ�
    private static final HashMap<String, String> MAP = new HashMap<String, String>();
    static {
        MAP.put("file", FILE);
        MAP.put("image", IMAGE);
    }

    /**
     * �����ļ���URL��ַ
     * @param type �ļ�����,�����ļ���ͼƬ��FILE IMAGE
     * @param path �ļ�·��
     * @return �ļ���URL��ַ
     */
    public static URL getURL(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }

    /**
     * ����ļ���������
     * @param type
     * �ļ�����,�����ļ���ͼƬ��FILE IMAGE
     * @param path
     * �ļ�·��
     * @return  �ļ���������
     */
    public static InputStream getInputStream(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResourceAsStream(dir + path);
    }


    /**
     * ��ȡͼƬ
     * @param path img�ļ����µ�·��������/,/avatat,/background
     * @return ͼƬImage
     */
    public static Image getImage(String path) {
        URL url = getURL("image", path);
        if(url == null) {
            return null;
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
     * ��ȡͼƬ��Դ
     * @param path img�ļ����µ�·��������/,avatat/,background/
     * @return BufferedImage������ͼƬ��������
     */
    public static BufferedImage getBufferedImage(String path) {
        try {
            InputStream input = getInputStream("image", path);
            return ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
