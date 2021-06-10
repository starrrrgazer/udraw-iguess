package ui.part;

import Manager.FontManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ServerInfoPanel extends JPanel {
	
	private JLabel roomNameLabel = new JLabel();
	private JLabel roomIpLabel = new JLabel();
	private JLabel roomPortLabel = new JLabel();
	
	private Font roomInfoFont = FontManager.getDefaultFontManager().getRoomInfoFont();
	
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

	public void updateInfo(String roomName, String ip, int port) {
		String myRoomName = "房间名：" + roomName;
		String myIp = "ip地址：" + ip;
		String myPort = "端口号：" + port;
		roomNameLabel.setText(myRoomName);
		roomIpLabel.setText(myIp);
		roomPortLabel.setText(myPort);
		
		int width = getWidth();
		int height = getHeight();
		int nameLength = SwingUtilities.computeStringWidth(getFontMetrics(roomInfoFont), myRoomName);
		int portLength = SwingUtilities.computeStringWidth(getFontMetrics(roomInfoFont), myPort);
		
		roomNameLabel.setBounds(20, 0, nameLength, height);
		roomIpLabel.setBounds(20 + nameLength, 0, width - 40 - nameLength - portLength, height);
		roomPortLabel.setBounds(width - 20 - portLength, 0, portLength, height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.GRAY);
		for (int i = 0; i < width; i += 20) {
			g.drawLine(i, height - 1, i + 15, height - 1);
		}
	}
}
