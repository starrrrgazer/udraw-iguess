package ui.part;

import java.awt.Component;

import javax.swing.SwingUtilities;

/**
 * 切换界面的线程
 */
public class ComponentDropper extends Thread {
	
	private static ComponentDropper dropper = new ComponentDropper();
	
	private final int lineDistance = 900;
	private final int sineDistance = 30;
	private final int lineSteps = 20;
	private final int sineSteps = 15;
	private final int deltaTime = 16;

	private Component currentComponent;
	private Component nextComponent;
	private int offsetStep;
	private long startTime;

	/**
	 * 获得componentdropper的单例模式
	 * @return componentdropp
	 */
	public static ComponentDropper getDefaultDropper() {
		return dropper;
	}
	
	private ComponentDropper() {
		this.start();
	}

	/**
	 * 切换页面
	 * @param prevComponent 之前的页面
	 * @param nextComponent 将要跳转的页面
	 */
	public void switchPanel(Component prevComponent, Component nextComponent) {
		if (currentComponent == null) {
			Component tempComponent;
			if (prevComponent != null) {
				setEnabled(prevComponent, false);
				tempComponent = prevComponent;
				offsetStep = 0;
			} else if (nextComponent != null) {
				tempComponent = nextComponent;
				offsetStep = lineSteps + sineSteps;
			} else {
				tempComponent = null;
				offsetStep = lineSteps + sineSteps << 1 + 1;
			}
			
			startTime = System.currentTimeMillis();
			
			currentComponent = tempComponent;
			this.nextComponent = nextComponent;
		}
	}
	
	private void setEnabled(Component component, boolean enabled) {
		SwingUtilities.invokeLater(new ComponentRunnable(component, enabled) {
			@Override
			public void run() {
				component.setEnabled(enabled);
			}
		});
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
			if (currentComponent != null) {
				if (currentTime < startTime + ((sineSteps + lineSteps << 1) - offsetStep) * deltaTime) {
					double step = (currentTime - startTime) * 1.0 / deltaTime + offsetStep;
					if (step < sineSteps) {
						y = (int) (sineDistance * Math.sin((step) * Math.PI / sineSteps));
					} else if (step < sineSteps + lineSteps) {
						y = -(int) ((step - sineSteps) * lineDistance / lineSteps);
					} else if (step < sineSteps + (lineSteps << 1)) {
						if (currentComponent != nextComponent) {
							currentComponent = nextComponent;
						}
						y = (int) ((step - sineSteps - (lineSteps << 1)) * lineDistance / lineSteps);
					} else {
						y = (int) (sineDistance * Math.sin((step - sineSteps - (lineSteps << 1)) * Math.PI / sineSteps));
					}
					setLocation(currentComponent, 0, y);
				} else {
					setLocation(currentComponent, 0, 0);
					setEnabled(currentComponent, true);
					currentComponent = null;
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
