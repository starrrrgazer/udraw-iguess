package ui.part;

import java.awt.Component;

import javax.swing.SwingUtilities;



public class ButtonDropper extends Thread {
	
	private static ButtonDropper dropper = new ButtonDropper();
	
	private final int distance = 10;
	private final int duration = 200;
	private final int deltaTime = 16;

	private Component component;
	private int startX;
	private int startY;
	private long startTime;

	private Component prevComponent;
	private Component nextComponent;
	
	public static ButtonDropper getDefaultDropper() {
		return dropper;
	}
	
	private ButtonDropper() {
		this.start();
	}
	
	public void pressed(Component component, Component prevComponent, Component nextComponent) {
		if (this.component == null) {
			startX = component.getX();
			startY = component.getY();
			startTime = System.currentTimeMillis();
			
			this.component = component;
			
			this.prevComponent = prevComponent;
			this.nextComponent = nextComponent;
		}
	}
	
	private void setLocation(Component component, int x, int y) {
		SwingUtilities.invokeLater(new ComponentRunnable(component, x, y) {
			@Override
			public void run() {
				component.setLocation(x, y);
			}
		});
	}
	
	@Override
	public void run() {
		int y;
		while (true) {
			long currentTime = System.currentTimeMillis();
			if (component != null) {
				if (currentTime < startTime + duration) {
					y = (int) (startY + distance * Math.sin((currentTime - startTime) * Math.PI / duration));
					setLocation(component, startX, y);
				} else {
					setLocation(component, startX, startY);
					component = null;
					ComponentDropper.getDefaultDropper().switchPanel(prevComponent, nextComponent);
				}
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
