package ui.part;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import Manager.ImageManager;

public class PigmentLabel extends JPanel {
	public static int chosen;
	
	private Color color;
	private int i;
	
	private Image img = ImageManager.getDefaultImageManager().getPigmentBg();
	
	public PigmentLabel(Color color, int i) {
		this.color = color;
		this.i = i;
		setOpaque(false);
	}

	public Color getColor() {
		return color;
	}
	
	public void choose() {
		PigmentLabel.chosen = i;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, width, height, this);
		g2d.setColor(color);
		g2d.fillRect(2, 2, width - 4, height - 4);
		if (i == chosen) {
			g.setColor(Color.WHITE);
			g.drawRect(1, 1, width - 3, height - 3);
			g.drawRect(2, 2, width - 5, height - 5);
		}
	}
}
