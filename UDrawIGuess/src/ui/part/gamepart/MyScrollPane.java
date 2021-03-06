package ui.part.gamepart;

import Manager.ImageManager;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.event.MouseInputListener;

/**
 * 聊天框的滚动条
 */
public class MyScrollPane extends JPanel implements MouseInputListener, ComponentListener {
	private final int barWidth = 15;
	private boolean dragged;

	private JComponent view;
	private JViewport viewPort = new JViewport();
	private JPanel scrollBar = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getScrollBarBg();
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		};
	};

	/**
	 * 构造函数
	 * @param view
	 */
	public MyScrollPane(JComponent view) {
		this.view = view;

		view.setOpaque(false);
		viewPort.setOpaque(false);
		scrollBar.setOpaque(false);
		this.setOpaque(false);

		viewPort.setView(view);

		setLayout(null);
		add(scrollBar);
		add(viewPort);
		
		view.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		view.addComponentListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private int updateBarHeight() {
		int barHeight = getHeight() * getHeight() / view.getHeight();
		scrollBar.setSize(barWidth, barHeight);
		return barHeight;
	}
	
	private int updateBarBounds() {
		scrollBar.setVisible(updateBarHeight() < getHeight());
		int barPositionY = -view.getY() * getHeight() / view.getHeight();
		scrollBar.setLocation(getWidth() - 1 - barWidth, barPositionY);
		return barPositionY;
	}
	
	private int updateViewPosition() {
		int viewY = -scrollBar.getY() * view.getHeight() / getHeight();
		view.setLocation(0, viewY);
		return viewY;
	}

	
	private void dragBar(int y) {
		int barHeight = updateBarHeight();
		int barPositionY = Math.max(0, Math.min(getHeight() - barHeight, y - (barHeight >> 1)));
		scrollBar.setLocation(getWidth() - 1 - barWidth, barPositionY);
		updateViewPosition();
	}

	/**
	 * 设置滚动条的大小和位置
	 * @param x 水平偏移量
	 * @param y 垂直偏移量
	 * @param width 宽度
	 * @param height 高度
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		viewPort.setBounds(0, 0, width - barWidth, height);
		scrollBar.setBounds(width - barWidth, 0, barWidth, height);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * 鼠标按下时，拖动滚动条
	 * @param e 鼠标事件
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() >= scrollBar.getX()) {
			dragged = true;
			dragBar(e.getY());
		}
	}

	/**
	 * 鼠标释放时，取消拖动
	 * @param e 鼠标事件
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		dragged = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * 鼠标拖动时，拖动滚动条
	 * @param e 鼠标事件
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragged) {
			dragBar(e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	/**
	 * 组件重置大小
	 * @param e 组件事件
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		updateBarBounds();
	}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
