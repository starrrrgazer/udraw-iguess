package socket;

import java.awt.image.BufferedImage;

public class DesaturateFilter {
	
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
