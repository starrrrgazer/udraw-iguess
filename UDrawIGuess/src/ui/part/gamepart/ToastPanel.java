package ui.part.gamepart;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * ������ĳ�����
 * @see TopicPanel
 * @see SpeakingPanel
 * @see ResultPanel
 * @see ResultItemPanel
 * @see NoticePanel
 * @see Toast
 */
public abstract class ToastPanel extends JPanel {
	/**
	 * ���캯��
	 */
	public ToastPanel() {
		setOpaque(false);
	}

	/**
	 * ��ȡ����ʱ��
	 * @return ����ʱ��
	 */
	public abstract long getEndTime();

	/**
	 * ���ݵ�ǰʱ��ͽ���ʱ�������ʾʱ��
	 * @param currentTime
	 */
	public abstract void onShow(long currentTime);

	/**
	 * ���ú���������λ��
	 * @param component ���
	 */
	public void setLocationRelativeTo(Component component) {
		int x = component.getWidth() - this.getWidth() >> 1;
		int y = component.getHeight() - this.getHeight() >> 1;
		Point loaction = SwingUtilities.convertPoint(component, x, y, this.getParent());
		this.setLocation(loaction);
	}

	/**
	 * ��ȡ����
	 * @return container
	 */
	public Container getToastContainer() {
		return this.getParent();
	}

	/**
	 * �������Ƴ����
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