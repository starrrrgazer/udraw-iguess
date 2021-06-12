package ui.part;

import java.awt.Component;

public abstract class ComponentRunnable implements Runnable {
	/**
	 * ���õ����
	 */
	protected Component component;
	/**
	 * �����ˮƽλ��
	 */
	protected int x;
	/**
	 * ����Ĵ�ֱλ��
	 */
	protected int y;
	/**
	 * ����Ƿ����Ӧ
	 */
	protected boolean enabled;

	/**
	 * ���캯��
	 * @param component ���õ����
	 * @param enabled �Ƿ����Ӧ
	 */
	public ComponentRunnable(Component component, boolean enabled) {
		this.component = component;
		this.enabled = enabled;
	}

	/**
	 * ���캯��
	 * @param component ���õ����
	 * @param x �����ˮƽλ��
	 * @param y ����Ĵ�ֱλ��
	 */
	public ComponentRunnable(Component component, int x, int y) {
		this.component = component;
		this.x = x;
		this.y = y;
	}

}