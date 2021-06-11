package ui.part.gamepart;


import Manager.ImageManager;
import socket.ClientAction;
import socket.Config;
import socket.MyPath;
import socket.ServerAction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import socket.DataPackage.DataType;
import ui.part.gamepart.TypeLabel.Type;

/**
 * ������
 */
public class DrawPanel extends JPanel implements MouseInputListener {
	
	private Point[] oldPosition = new Point[3];
	private int[] pathIndex = new int[3];
	private ArrayList<MyPath> paths = new ArrayList<MyPath>();
	
	private BufferedImage bufferdImage;
	private Graphics2D g2d;
	
	private boolean dragged;
	
	private Image[] cursorIcon = ImageManager.getDefaultImageManager().getCursorIcon();

	/**
	 * Ĭ�Ϲ��캯��
	 */
	public DrawPanel() {
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * ���û���λ�úʹ�С
	 * @param x ˮƽƫ����
	 * @param y ��ֱƫ����
	 * @param width ������
	 * @param height ����߶�
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		bufferdImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		g2d = bufferdImage.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * �ڻ����ϻ�������
	 * @param g Graphics����������
	 */
	@Override
	public void paintComponent(Graphics g) {
		Color oldColor = g2d.getColor();
		Stroke oldStroke = g2d.getStroke();
		g2d.clearRect(0, 0, bufferdImage.getWidth(), bufferdImage.getHeight());
		synchronized (paths) {
			for (MyPath path : paths) {
				g2d.setColor(path.getColor());
				g2d.setStroke(path.getStroke());
				g2d.draw(path.getPath());
			}
		}
		g2d.setColor(oldColor);
		g2d.setStroke(oldStroke);
		g.drawImage(bufferdImage, 0, 0, this);
	}

	/**
	 * ���ƻ���߿�
	 * @param g Graphics����������
	 */
	@Override
	protected void paintBorder(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(1, 1, width - 3, height - 3);
	}

	private void sendDraw(int index, Point position) {
		if (Config.serving) {
			draw(index, position);
			ServerAction.sendData(DataType.BRUSH_POSITION, new Object[]{ index, position });
		} else {
			ClientAction.sendData(DataType.BRUSH_POSITION, new Object[]{ index, position });
		}
	}

	/**
	 * �ڻ����ϻ��������ľ��巽��
	 * @param index
	 * @param newPosition
	 */
	public void draw(int index, Point newPosition) {
		if (newPosition != null) {
			if (oldPosition[index] == null) {
				MyPath path = new MyPath(g2d.getColor(), g2d.getStroke(), newPosition);
				paths.add(path);
				pathIndex[index] = paths.size() - 1;
			}
			MyPath path = paths.get(pathIndex[index]);
			path.lineTo(newPosition);
		}
		oldPosition[index] = newPosition;
		repaint();
	}

	/**
	 * ����������ɫ
	 * @param color����ˢ��ɫ
	 */
	public void setPigment(Color color) {
		draw(0, null);
		draw(1, null);
		draw(2, null);
		g2d.setColor(color);
	}

	/**
	 * ����������ϸ
	 * @param thickness
	 */
	public void setThickness(int thickness) {
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}

	/**
	 * ��������ڵ�����
	 */
	public void canvasClear() {
		synchronized (paths) {
			draw(0, null);
			draw(1, null);
			draw(2, null);
			paths.clear();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * ��갴��ʱ����Ǳ��϶�������������Ϣ
	 * @param e ����¼�
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (isEnabled()) {
			dragged = true;
			sendDraw(0, e.getPoint());
		}
	}

	/**
	 * ����ͷ�ʱ������϶�����������������Ϣ
	 * @param e ����¼�
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isEnabled()) {
			dragged = false;
			sendDraw(0, null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * ����϶��¼�������������Ϣ
	 * @param e ����¼�
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (isEnabled() && dragged) {
			sendDraw(0, e.getPoint());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
}
