package ui.part;

import java.awt.Component;

import javax.swing.SwingUtilities;


/**
 * 跳转按钮的线程实现
 * @see ComponentDropper
 */
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

	/**
	 * 返回dropper的单例模式
	 * @return buttondropper
	 */
	public static ButtonDropper getDefaultDropper() {
		return dropper;
	}
	
	private ButtonDropper() {
		this.start();
	}

	/**
	 * 记录触发事件的按钮，记录现在和将要跳转的组件
	 * @param component
	 * @param prevComponent
	 * @param nextComponent
	 */
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

	/**
	 * 线程运行方法
	 */
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
