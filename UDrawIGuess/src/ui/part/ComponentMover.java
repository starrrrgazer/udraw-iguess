package ui.part;

import java.awt.Component;
import javax.swing.SwingUtilities;



public class ComponentMover extends Thread {

	private final int deltaTime = 20;
	
	private Component component;
	private int targetX;
	private int targetY;
	private float velocty = 0.2f;
	
	public ComponentMover(Component component) {
		this.setComponent(component);
	}
	
	private void setComponent(Component component) {
		if (component != null) {
			this.component = component;
			this.setTarget(component.getX(), component.getY());
			this.start();
		}
	}
	
	public void setTarget(int targetX, int targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
//	public void setVelocty(float velocty) {
//		this.velocty = Math.min(1, Math.max(0, velocty));
//	}
	
	private void setLocation(int x, int y) {
		SwingUtilities.invokeLater(new ComponentRunnable(this.component, x, y) {
			@Override
			public void run() {
				component.setLocation(x, y);
				component.getParent().repaint();
			}
		});
	}
	
	@Override
	public void run() {
		while (true) {
			long currentTime = System.currentTimeMillis();
			int x = component.getX();
			int y = component.getY();
			if (x != targetX || y != targetY) {
				double offsizeX = (targetX - x) * (1 - velocty);
				double offsizeY = (targetY - y) * (1 - velocty);
				x = targetX - (int) offsizeX;
				y = targetY - (int) offsizeY;
				setLocation(x, y);
			}
			long extraTime = System.currentTimeMillis() - currentTime;
			try {
				Thread.sleep(Math.max(0, deltaTime - extraTime));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}