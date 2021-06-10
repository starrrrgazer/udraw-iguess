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
 * 文件控制类
 */
public class FileControl {
    //项目文件必须位于src目录下的下列3个子文件夹之一
    private static final String FILE = "FileResource/file/";	//存放普通文件
    private static final String IMAGE = "FileResource/image/";	//存放图片文件
    private static final HashMap<String, String> MAP = new HashMap<String, String>();
    static {
        MAP.put("file", FILE);
        MAP.put("image", IMAGE);
    }

    /**
     * 返回文件的URL地址
     * @param type 文件类型,包括文件、图片，FILE IMAGE
     * @param path 文件路径
     * @return 文件的URL地址
     */
    public static URL getURL(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResource(dir + path);
    }

    /**
     * 获得文件的输入流
     * @param type
     * 文件类型,包括文件、图片，FILE IMAGE
     * @param path
     * 文件路径
     * @return  文件的输入流
     */
    public static InputStream getInputStream(String type, String path) {
        String dir = MAP.get(type);
        return URLClassLoader.getSystemClassLoader().getResourceAsStream(dir + path);
    }


    /**
     * 获取图片
     * @param path img文件夹下的路径，包括/,/avatat,/background
     * @return 图片Image
     */
    public static Image getImage(String path) {
        URL url = getURL("image", path);
        if(url == null) {
            return null;
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
     * 读取图片资源
     * @param path img文件夹下的路径，包括/,avatat/,background/
     * @return BufferedImage，便于图片后续操作
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
