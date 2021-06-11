package ui.part.gamepart;

import Manager.FontManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * ����(�����)��Ϣpanel
 */
public class ServerInfoPanel extends JPanel {
	
	private JLabel roomNameLabel = new JLabel();
	private JLabel roomIpLabel = new JLabel();
	private JLabel roomPortLabel = new JLabel();
	
	private Font roomInfoFont = FontManager.getDefaultFontManager().getRoomInfoFont();

	/**
	 * ���캯��
	 */
	public ServerInfoPanel() {		
		roomIpLabel.setHorizontalAlignment(JLabel.CENTER);
		roomNameLabel.setFont(roomInfoFont);
		roomIpLabel.setFont(roomInfoFont);
		roomPortLabel.setFont(roomInfoFont);

		setLayout(null);
		add(roomNameLabel);
		add(roomIpLabel);
		add(roomPortLabel);
		setOpaque(false);
	}

	/**
	 * ���·�����Ϣ
	 * @param roomName ������
	 * @param ip �����ip
	 * @param port ����˼����Ķ˿�
	 */
	public void updateInfo(String roomName, String ip, int port) {
		String myRoomName = "��������" + roomName;
		String myIp = "ip��ַ��" + ip;
		String myPort = "�˿ںţ�" + port;
//		roomNameLabel.setText(myRoomName);
//		roomIpLabel.setText(myIp);
		roomPortLabel.setText(myPort);
		
		int width = getWidth();
		int height = getHeight();
//		int nameLength = SwingUtilities.computeStringWidth(getFontMetrics(roomInfoFont), myRoomName);
		int portLength = SwingUtilities.computeStringWidth(getFontMetrics(roomInfoFont), myPort);

//		roomNameLabel.setBounds(20, 0, nameLength, height);
//		roomIpLabel.setBounds(20 + nameLength, 0, width - 40 - nameLength - portLength, height);
		roomPortLabel.setBounds(20, 0, portLength, height);
	}

}
