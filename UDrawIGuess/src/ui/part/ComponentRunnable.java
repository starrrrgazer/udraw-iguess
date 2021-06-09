package ui.part;

import java.awt.Component;
import java.awt.Container;

public abstract class ComponentRunnable implements Runnable {
	protected Component component;
	protected int x;
	protected int y;
	protected boolean enabled;
	protected Component child;
	
	public ComponentRunnable(Component component, boolean enabled) {
		this.component = component;
		this.enabled = enabled;
	}
	
	public ComponentRunnable(Component component, int x, int y) {
		this.component = component;
		this.x = x;
		this.y = y;
	}
	
	public ComponentRunnable(Container component, Component child) {
		this.component = component;
		this.child = child;
	}
}