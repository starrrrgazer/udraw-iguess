package ui.part.gamepart;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 动画类的抽象父类
 * @see TopicPanel
 * @see SpeakingPanel
 * @see ResultPanel
 * @see ResultItemPanel
 * @see NoticePanel
 * @see Toast
 */
public abstract class ToastPanel extends JPanel {
	/**
	 * 构造函数
	 */
	public ToastPanel() {
		setOpaque(false);
	}

	/**
	 * 获取结束时间
	 * @return 结束时间
	 */
	public abstract long getEndTime();

	/**
	 * 根据当前时间和结束时间计算显示时间
	 * @param currentTime
	 */
	public abstract void onShow(long currentTime);

	/**
	 * 设置和组件的相对位置
	 * @param component 组件
	 */
	public void setLocationRelativeTo(Component component) {
		int x = component.getWidth() - this.getWidth() >> 1;
		int y = component.getHeight() - this.getHeight() >> 1;
		Point loaction = SwingUtilities.convertPoint(component, x, y, this.getParent());
		this.setLocation(loaction);
	}

	/**
	 * 获取容器
	 * @return container
	 */
	public Container getToastContainer() {
		return this.getParent();
	}

	/**
	 * 容器中移除组件
	 */
	public void beRemoved() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Container container = getToastContainer();
				if (container != null) {
					container.remove(ToastPanel.this);
					container.repaint();
				}
			}
		});
	}
}