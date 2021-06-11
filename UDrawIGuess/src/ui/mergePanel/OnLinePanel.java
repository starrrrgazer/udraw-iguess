package ui.mergePanel;

import Manager.FontManager;
import socket.Config;
import socket.TCPServerThread;
import ui.part.ButtonDropper;
import ui.part.HeadPortraitPanel;
import ui.part.JumpButton;
import ui.part.MyTextField;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.EventObject;

import javax.swing.JLabel;


/**
 * ������Ϸ�ĵ�һ�����棬���������ѡ��ͷ����д�ǳƣ�ѡ�񴴽���Ϸ���߼�����Ϸ
 */
public class OnLinePanel extends FacePanel {
	private JLabel chooseHeadPortraitPanel = new JLabel("ѡ��ͷ��");
	private HeadPortraitPanel headPortraitPanel = new HeadPortraitPanel();

	private JLabel nickName = new JLabel("�� �ƣ�");
	private MyTextField nickNameField = new MyTextField();

	private JumpButton newRoom = new JumpButton("������Ϸ");
	private JumpButton joinIn = new JumpButton("������Ϸ");

	private Font nameConfigLabelFont = FontManager.getDefaultFontManager().getNameConfigLabelFont();
	private Font chooseHPFont = FontManager.getDefaultFontManager().getChooseHPFont();

	/**
	 * ���캯����
	 * onlinePanel�ĳ�ʼ��,����������
	 * @param owner
	 * panel�������ߣ���mainFrame
	 */
	public OnLinePanel(MainFrame owner) {
		super(owner);
		chooseHeadPortraitPanel.setBounds(50,50,100,20);
		headPortraitPanel.setBounds(100, 80, 600, 300);
		chooseHeadPortraitPanel.setFont(chooseHPFont);
		nickName.setFont(nameConfigLabelFont);
		nickName.setBounds(200, 410, 150, 40);
		nickNameField.setBounds(300, 415, 300, 30);

		int temp = (int) ((MainFrame.WIDTH - (JumpButton.WIDTH << 1)) * JumpButton.GOLD_SECTION);
		newRoom.setBounds(MainFrame.WIDTH - temp - (JumpButton.WIDTH << 1), 475, JumpButton.WIDTH, JumpButton.HEIGHT);
		joinIn.setBounds(temp + JumpButton.WIDTH, 475, JumpButton.WIDTH, JumpButton.HEIGHT);

		newRoom.addMouseListener(this);
		joinIn.addMouseListener(this);

		add(chooseHeadPortraitPanel);
		add(headPortraitPanel);
		add(nickName);
		add(nickNameField);
		add(newRoom);
		add(joinIn);

		setLayout(null);
		setEnabled(false);
	}

	/**
	 * ����û�û����д�ǳƣ�����Java�Դ��Ļ�ȡip������ȡip��Ϊ�ǳ�
	 * ������������Ϸ�����½�����˵��߳�
	 * ���������Ϸ���߼�����Ϸ���ᷢ��ҳ����ת
	 * @param e
	 * �¼�����
	 * @see TCPServerThread
	 * @see ButtonDropper
	 */
	public void buttonPressed(EventObject e) {
		if (isEnabled()) {
			try {
				Config.ip = Inet4Address.getLocalHost().getHostAddress();
				System.out.println(Config.ip);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			String nickNameConfig = nickNameField.getText();
			if ("".equals(nickNameConfig)) {
				Config.nickName = Config.ip;
			} else {
				Config.nickName = nickNameConfig;
			}
			if (e.getSource() == newRoom) {
				new TCPServerThread().start();
				ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getGamePanel());
			} else if (e.getSource() == joinIn) {
				ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getIpConnectPanel());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 *
	 * @param e
	 * �����
	 */
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
