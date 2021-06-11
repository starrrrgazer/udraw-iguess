package ui.mergePanel;

import Manager.FontManager;
import Manager.ImageManager;
import socket.ClientAction;
import socket.Config;
import ui.part.ButtonDropper;
import ui.part.gamepart.Toast;
import ui.part.JumpButton;
import ui.part.MyTextField;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * ip连接页面，可以连接已经开启服务端的主机的ip和端口
 */
public class IpConnectPanel extends FacePanel {
	
	private JPanel panel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getIpConnectBg();
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	};
	private JLabel IPLabel = new JLabel("服务器IP：");
	private MyTextField IPjtf = new MyTextField();
	private JLabel portLabel = new JLabel("端 口 号：");
	private MyTextField portjtf = new MyTextField();
	
	private JumpButton connect = new JumpButton("连接游戏");
	private JumpButton back = new JumpButton("返 回");
	
	private Font ipConnectfont = FontManager.getDefaultFontManager().getIpConnectLabelFont();

	/**
	 * 构造函数
	 * @param owner
	 * panel的所有者，即mainFrame
	 */
	public IpConnectPanel(MainFrame owner) {
		super(owner);
		
		panel.setOpaque(false);
		panel.setBounds(140, 110, 520, 300);
		
		panel.setLayout(null);
		IPLabel.setFont(ipConnectfont);
		IPLabel.setBounds(125, 200, 100, 30);
		IPjtf.setBounds(215, 200, 160, 30);
		IPjtf.setToolTipText("IP格式：127.0.0.1");
		portLabel.setFont(ipConnectfont);
		portLabel.setBounds(125, 240, 100, 30);
		portjtf.setBounds(215, 240, 160, 30);
		portjtf.setToolTipText("Port格式：小于65536的整数");
		
		connect.addMouseListener(this);
		back.addMouseListener(this);

		panel.add(IPLabel);
		panel.add(IPjtf);
		panel.add(portLabel);
		panel.add(portjtf);
		
		connect.setBounds(MainFrame.WIDTH - JumpButton.WIDTH >> 1, 470, JumpButton.WIDTH, JumpButton.HEIGHT);
		back.setBounds(600, 50, 48, 60);
		
		add(panel);
		add(connect);
		add(back);

		setLayout(null);
		setEnabled(false);
	}
	
	private void buttonPressed(EventObject e) {
		if (e.getSource() == connect) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), null, null);
			String ip = IPjtf.getText();
			String port = portjtf.getText();
			if (!ip.matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}")) {
				Toast.getDefaultToast().makeToastNotice("IP格式错误，应如 127.0.0.1 。", 1000);
				return;
			} else if (!port.matches("\\d{1,5}") || Integer.parseInt(port) >= 2 << 16) {
				Toast.getDefaultToast().makeToastNotice("端口格式错误，应取小于65536的整数。", 1000);
				return;
			} else if ("127.0.0.1".equals(ip)) {
				Toast.getDefaultToast().makeToastNotice("不能连接本地主机，请输入其他主机的IP。", 1000);
				return;
			}
			Config.ip = ip;
			Config.port = Integer.parseInt(port);
			new ClientAction().start();
		}
		else if (e.getSource() == back) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getOnLinePanel());
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
