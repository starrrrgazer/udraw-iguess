package ui.part;

import socket.Config;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * 头像选择
 * @see HeadPortraitLabel
 */
public class HeadPortraitPanel extends JPanel implements MouseListener {
	
	private HeadPortraitLabel chosen;
	private HeadPortraitLabel chooser;
	private HeadPortraitLabel[] headPortraits = new HeadPortraitLabel[14];
	
	private ComponentMover mover;

	/**
	 * 构造函数
	 */
	public HeadPortraitPanel() {
		setOpaque(false);
		setLayout(null);

		chooser = new HeadPortraitLabel(headPortraits.length + 1, 0);
		chooser.setBounds(200, 0, 100, 100);
		add(chooser);
		for (int i = 0; i < headPortraits.length; i++) {
			headPortraits[i] = new HeadPortraitLabel(i + 1, 0, true);
			if (i < 4) {
				headPortraits[i].setBounds((i + 2) * 100, 0, 100, 100);
			} else if (i < 8) {
				headPortraits[i].setBounds((i - 2) * 100, 100, 100, 100);
			} else {
				headPortraits[i].setBounds((i - 8) * 100, 200, 100, 100);
			}
			headPortraits[i].addMouseListener(this);
			add(headPortraits[i]);
		}
		headPortraits[0].choose();
		chosen = new HeadPortraitLabel(1, 0, true);
		chosen.setBounds(0, 0, 200, 200);
		add(chosen);
		
		mover = new ComponentMover(chooser);

	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * 鼠标按下时，获取选中的头像
	 * @param e 鼠标事件
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		HeadPortraitLabel label = (HeadPortraitLabel) e.getSource();
		if (label != chooser) {
			Config.headPortrait = label.getI();
			mover.setTarget(label.getX(), label.getY());
			label.choose();
			chosen.setI(label.getI());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
