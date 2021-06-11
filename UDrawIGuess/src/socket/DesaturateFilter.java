package socket;

import java.awt.image.BufferedImage;

/**
 * 客户端断开连接后，改变头像颜色
 */
public class DesaturateFilter {

	/**
	 * 获取客户端头像，并且改变头像颜色
	 * @param src 客户端的头像图片
	 * @return 已经修改变色的客户端头像图片
	 */
	public static BufferedImage filter(BufferedImage src) {
		int width = src.getWidth();
		int height = src.getHeight();
		BufferedImage dst = new BufferedImage(width, height, src.getType());
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int colorTemp = src.getRGB(x, y);
				int[] color = new int[4];
				color[0] = colorTemp >> 24 & 0xff;
				color[1] = colorTemp >> 16 & 0xff;
				color[2] = colorTemp >> 8 & 0xff;
				color[3] = colorTemp & 0xff;
				int gray = (int) (color[1] * 0.3 + color[2] * 0.59 + color[3] * 0.11);
				int newColor = (color[0] << 24) | (gray << 16) | (gray << 8) | gray;
				dst.setRGB(x, y, newColor);
			}
		}
		return dst;
	}
}
