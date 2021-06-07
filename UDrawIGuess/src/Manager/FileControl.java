package Manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

public class FileControl {
    //��Ŀ�ļ�����λ��srcĿ¼�µ�����3�����ļ���֮һ
    private static final String FILE = "FileResource/file/";	//�����ͨ�ļ�
    private static final String IMAGE = "FileResource/image/";	//���ͼƬ�ļ�
    private static final String FONT = "FileResource/font/";	//��������ļ�
    private static final HashMap<String, String> MAP = new HashMap<String, String>();
    static {
        MAP.put("file", FILE);
        MAP.put("image", IMAGE);
        MAP.put("font", FONT);
    }

    /**
     * ������Դ�ļ���URL��ַ
     * @param type ��Դ�ļ�����,�����ļ���ͼƬ����Ƶ
     * @param path ��Դ�ļ�·��(�磺me.jpg)
     * @return ��Դ�ļ���URL��ַ
     */
    public static URL getURL(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }

    //��ȡ������
    public static InputStream getInputStream(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResourceAsStream(dir + path);
    }

    //��ȡ������Դ
    public static Font getFont(String path) {
        try {
            InputStream input = getInputStream("font", path);
            if (input != null) {
                return Font.createFont(Font.TRUETYPE_FONT, input);
            }
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //��ȡ�ļ���Դ
    public static File getFile(String path) {
        URL url = getURL("file", path);
        if (url == null) {
            return null;
        }
        return new File(url.getFile());
    }


    //��ȡͼƬ��Դ
    public static Image getImage(String path) {
        URL url = getURL("image", path);
        if(url == null) {
            return null;
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    //��ȡͼƬ��Դ
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
