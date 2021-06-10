package ui.part;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class TypeLabel extends JPanel {
	public static int chosen;
	
	public enum Type { BRUSH, ERASER, CLEAR }
	private Type type;
	private Image img;
	private int i;

	private ImageManager imageManager = ImageManager.getDefaultImageManager();
	private Image typeChosenBg = imageManager.getBrushTypeChosenBg();
	private Image canvasClearBg = imageManager.getCanvasClearBg();
	
	public TypeLabel(Type type, int i) {
		this.type = type;
		switch (type) {
		case BRUSH:
			this.img = imageManager.getBrushIcon();
			break;
		case ERASER:
			this.img = imageManager.getEraserIcon();
			break;
		case CLEAR:
			this.img = imageManager.getClearIcon();
			break;
		}
		this.i = i;
		setOpaque(false);
	}

	public Type getType() {
		return type;
	}
	
	public void choose() {
		TypeLabel.chosen = i;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, -3, -3, this);
		if (i == chosen) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(typeChosenBg, 0, 0, width, height, this);
		} else if (i == 2) {
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(canvasClearBg, 0, 0, width, height, this);
		}
	}
}
