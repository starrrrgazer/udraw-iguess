package ui.part.gamepart;

import ui.mergePanel.MainFrame;

import java.awt.Component;
import java.awt.Container;

import javax.swing.SwingUtilities;

/**
 * 动画的线程
 * @see TopicPanel
 * @see SpeakingPanel
 * @see ResultPanel
 * @see NoticePanel
 */
public class Toast extends Thread {
	
	private static Toast toast = new Toast();
	
	private final int deltaTime = 16;
	
	private Container container;
	private ToastPanel[] addScorePanel = new ToastPanel[5];

	/**
	 * 获取toast的单例模式
	 * @return toast
	 */
	public static Toast getDefaultToast() {
		return toast;
	}
	
	private Toast() {
		container = MainFrame.getMainFrame().getToastPanel();
		this.start();
	}

	/**
	 * 显示“提示信息”
	 * @param message 提示信息
	 * @param duration 显示的时长
	 */
	public void makeToastNotice(final String message, final int duration) {
		
		removeSpecifiedComponents(container, NoticePanel.class, Object.class);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ToastPanel noticePanel = new NoticePanel(message, duration);
				container.add(noticePanel, 0);
				noticePanel.setLocationRelativeTo(container);
			}
		});
	}

	/**
	 * 显示“题目内容”
	 * @param topic 题目内容
	 * @param duration 显示时长
	 * @param centerComponent 组件
	 */
	public void makeToastTopic(String topic, int duration, Component centerComponent) {
		makeRevealPanel(topic, duration, centerComponent);
	}

	/**
	 * 显示“猜题结果”
	 * @param duration 显示时长
	 * @param centerComponent 组件
	 */
	public void makeToastResult(int duration, Component centerComponent) {
		makeRevealPanel(null, duration, centerComponent);
	}
	
	private void makeRevealPanel(final String topic, final int duration, final Component centerComponent) {

		removeSpecifiedComponents(container, TopicPanel.class, Object.class);
		removeSpecifiedComponents(container, ResultPanel.class, Object.class);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ToastPanel revealPanel = topic != null ? new TopicPanel(topic, duration) : new ResultPanel(duration);
				container.add(revealPanel);
				revealPanel.setLocationRelativeTo(centerComponent);
			}
		});
	}

	/**
	 * 显示“说话内容”
	 * @param speaking 说话内容
	 * @param duration 显示时长
	 * @param centerComponent 组件
	 * @param index 组件在数组的索引
	 */
	public void makeToastSpeaking(String speaking, int duration, Component centerComponent, int index) {
		makeToastSpeaking(speaking, duration, centerComponent, index, false);
	}

	/**
	 * 显示“加分动画”
	 * @param score 加的分
	 * @param duration 显示时长
	 * @param centerComponent 组件
	 * @param index 组件在数组的索引
	 */
	public void makeToastAddScore(int score, int duration, Component centerComponent, int index) {
		makeToastSpeaking("+" + score, duration, centerComponent, index, true);
	}
	
	private void makeToastSpeaking(final String speaking, final int duration, final Component centerComponent, final int index, final boolean isAddScore) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (addScorePanel[index] != null) {
					container.remove(addScorePanel[index]);
					container.repaint();
				}

				addScorePanel[index] = new SpeakingPanel(speaking, isAddScore, duration);
				container.add(addScorePanel[index], container.getComponentCount() - 1);
				addScorePanel[index].setLocationRelativeTo(centerComponent);
			}
		});
	}

	/**
	 * 线程运行方法
	 */
	@Override
	public void run() {
		while (true) {
			long currentTime = System.currentTimeMillis();
			for (Component component : container.getComponents()) {
				if (component instanceof ToastPanel) {
					ToastPanel toastPanel = (ToastPanel) component;
					if (currentTime < toastPanel.getEndTime()) {
						toastPanel.onShow(currentTime);
					} else {
						toastPanel.beRemoved();
					}
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

	/**
	 * 清空toast
	 */
	public void makeToastClear() {
		removeSpecifiedComponents(container, null, NoticePanel.class);
	}

	private <T> void removeSpecifiedComponents(final Container container, Class<? extends T> specifiedType, Class<? extends T> exceptedType) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			if (component.getClass() == specifiedType) {
				container.remove(component);
			}
			if (!exceptedType.isInstance(component)) {
				container.remove(component);
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				container.repaint();
			}
		});
	}
}
