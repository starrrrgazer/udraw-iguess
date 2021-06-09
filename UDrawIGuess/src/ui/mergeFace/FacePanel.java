package ui.mergeFace;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;

import javax.swing.JPanel;



public abstract class FacePanel extends JPanel implements MouseListener {

	protected MainFrame owner;
	private Image img = ImageManager.getDefaultImageManager().getBoardBg();
	
	public FacePanel(MainFrame owner) {
		this.owner = owner;
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
