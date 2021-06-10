package ui.part;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class ToastPanel extends JPanel {
	
	public ToastPanel() {
		setOpaque(false);
	}
	
	public abstract long getEndTime();
	
	public abstract void onShow(long currentTime);
	
	public void setLocationRelativeTo(Component component) {
		int x = component.getWidth() - this.getWidth() >> 1;
		int y = component.getHeight() - this.getHeight() >> 1;
		Point loaction = SwingUtilities.convertPoint(component, x, y, this.getParent());
		this.setLocation(loaction);
	}
	
	public Container getToastContainer() {
		return this.getParent();
	}
	
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