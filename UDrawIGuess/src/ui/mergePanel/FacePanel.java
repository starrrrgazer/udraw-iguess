package ui.mergePanel;

import Manager.ImageManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


/**
 * panel�ĳ�����
 * ������ÿ��panel��ͬ�ı���
 */
public abstract class FacePanel extends JPanel implements MouseListener {

	protected MainFrame owner;
	private Image img = ImageManager.getDefaultImageManager().getBoardBg();

	/**
	 * ���캯��
	 * @param owner
	 * panel�������ߣ���mainFrame
	 */
	public FacePanel(MainFrame owner) {
		this.owner = owner;
		setOpaque(false);
	}

	/**
	 *������ÿ��panel�ı��������������˾�ݣ�ʹ����ƽ����ʾ
	 * @param g
	 * Graphics��ʵ�������Ʊ���
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
