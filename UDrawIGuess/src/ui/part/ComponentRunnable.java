package ui.part;

import java.awt.Component;

public abstract class ComponentRunnable implements Runnable {
	/**
	 * 设置的组件
	 */
	protected Component component;
	/**
	 * 组件的水平位置
	 */
	protected int x;
	/**
	 * 组件的垂直位置
	 */
	protected int y;
	/**
	 * 组件是否可相应
	 */
	protected boolean enabled;

	/**
	 * 构造函数
	 * @param component 设置的组件
	 * @param enabled 是否可响应
	 */
	public ComponentRunnable(Component component, boolean enabled) {
		this.component = component;
		this.enabled = enabled;
	}

	/**
	 * 构造函数
	 * @param component 设置的组件
	 * @param x 组件的水平位置
	 * @param y 组件的垂直位置
	 */
	public ComponentRunnable(Component component, int x, int y) {
		this.component = component;
		this.x = x;
		this.y = y;
	}

}