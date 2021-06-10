package ui.part;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class ThicknessLabel extends JPanel {
	public static int chosen = 1;
	
	private int thickness;
	private int i;
	
	private Image chosenBg = ImageManager.getDefaultImageManager().getThicknessChosenBg();
	
	public ThicknessLabel(int thickness, int i) {
		this.thickness = thickness;
		this.i = i;
		setOpaque(false);
	}

	public int getThickness() {
		return thickness;
	}
	
	public void choose() {
		ThicknessLabel.chosen = i;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		int size = thickness << 1;
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.fillOval(width - size >> 1, height - size >> 1, size, size);
		if (i == chosen) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(chosenBg, 0, 0, width, height, this);
		}
	}
}
