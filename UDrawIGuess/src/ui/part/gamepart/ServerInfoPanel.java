package ui.part.gamepart;

import Manager.FontManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 房间(服务端)信息panel
 */
public class ServerInfoPanel extends JPanel {
	
	private JLabel roomNameLabel = new JLabel();
	private JLabel roomIpLabel = new JLabel();
	private JLabel roomPortLabel = new JLabel();
	
	private Font roomInfoFont = FontManager.getDefaultFontManager().getRoomInfoFont();

	/**
	 * 构造函数
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
	 * 更新房间信息
	 * @param roomName 房间名
	 * @param ip 服务端ip
	 * @param port 服务端监听的端口
	 */
	public void updateInfo(String roomName, String ip, int port) {
		String myRoomName = "房间名：" + roomName;
		String myIp = "ip地址：" + ip;
		String myPort = "端口号：" + port;
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
