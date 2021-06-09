package ui.mergeFace;

import Manager.FontManager;
import socket.Config;
import ui.part.ButtonDropper;
import ui.part.HeadPortraitPanel;
import ui.part.component.JumpButton;
import ui.part.component.MyTextField;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.EventObject;

import javax.swing.JLabel;



public class OnLinePanel extends FacePanel {
	
	private HeadPortraitPanel headPortraitPanel = new HeadPortraitPanel();
	
	private JLabel nickName = new JLabel("昵 称：");
	private MyTextField nickNameField = new MyTextField();
	
	private JumpButton newRoom = new JumpButton("创建游戏");
	private JumpButton joinIn = new JumpButton("加入游戏");
	
	private Font nameConfigLabelFont = FontManager.getDefaultFontManager().getNameConfigLabelFont();
	
	public OnLinePanel(MainFrame owner) {
		super(owner);
		
		headPortraitPanel.setBounds(100, 80, 600, 300);
		
		nickName.setFont(nameConfigLabelFont);
		nickName.setBounds(200, 410, 150, 40);
		nickNameField.setBounds(300, 415, 300, 30);
		
		int temp = (int) ((MainFrame.WIDTH - (JumpButton.WIDTH << 1)) * JumpButton.GOLD_SECTION);
		newRoom.setBounds(MainFrame.WIDTH - temp - (JumpButton.WIDTH << 1), 475, JumpButton.WIDTH, JumpButton.HEIGHT);
		joinIn.setBounds(temp + JumpButton.WIDTH, 475, JumpButton.WIDTH, JumpButton.HEIGHT);
		
		newRoom.addMouseListener(this);
		joinIn.addMouseListener(this);
//		LeapManager.getDefaultLeapManager().addLeapListener(newRoom, this);
//		LeapManager.getDefaultLeapManager().addLeapListener(joinIn, this);

		add(headPortraitPanel);
		add(nickName);
		add(nickNameField);
		add(newRoom);
		add(joinIn);
		
		setLayout(null);
		setEnabled(false);
	}
	
	public void buttonPressed(EventObject e) {
		if (isEnabled()) {
			try {
				Config.ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			String nickNameConfig = nickNameField.getText();
			if ("".equals(nickNameConfig)) {
				Config.nickName = Config.ip;
			} else {
				Config.nickName = nickNameConfig;
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonPressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
