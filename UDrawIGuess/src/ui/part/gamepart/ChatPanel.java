package ui.part.gamepart;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Manager.FileControl;
import Manager.FontManager;
import socket.ClientAction;
import socket.Config;
import socket.GamePlayThread;
import socket.ServerAction;
import ui.mergePanel.MainFrame;
import ui.part.MyTextField;
import socket.DataPackage.DataType;


public class ChatPanel extends JPanel implements ActionListener, MouseListener {
	private JTextPane chatPane = new JTextPane();
	private final int headPortraitWidth = 30;
	
	private JLabel chatTitle = new JLabel("聊天信息：");
	private MyScrollPane chatScroll = new MyScrollPane(chatPane);
	private MyTextField chatField = new MyTextField();
	private MyButton sendButton = new MyButton("发送");
	
	private Font titleFont = FontManager.getDefaultFontManager().getChatTitleFont();
	
	public ChatPanel() {
		
		chatTitle.setFont(titleFont);
		
		setOpaque(false);
		setLayout(null);
		add(chatTitle);
		add(chatScroll);
		add(chatField);
		add(sendButton);
		
		chatPane.setEditable(false);
		
		chatField.addActionListener(this);
		sendButton.addMouseListener(this);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		chatTitle.setBounds(width - 100, 0, 80, 30);
		chatScroll.setBounds(5, 25, width - 7, height - 70);
		chatField.setBounds(5, height - 50, width - 60, 30);
		sendButton.setBounds(width - 60, height - 50, 50, 30);
	}
	
	public void chatPaneClear() {
		chatPane.setText("");
	}
	
	public void appendSystemMessage(String message) {
		appendContent(message + "\r\n", Font.DIALOG_INPUT, 13, false, false, false, Color.DARK_GRAY);
	}
	
	public void appendMessage(String name, int headPortrait, String message) {
		insertHeadPortrait(headPortrait);
		appendContent(" " + name + "：", Font.DIALOG, 13, false, false, false, new Color(100, 0, 20));
		appendContent(message + "\r\n", Font.DIALOG_INPUT, 13, false, false, false, Color.BLACK);
	}
	
	private void insertHeadPortrait(int headPortrait) {
		
		BufferedImage img = FileControl.getBufferedImage("avatar/" + (headPortrait + 100 + "").substring(1) + ".jpg");
		BufferedImage bimg = new BufferedImage(headPortraitWidth, headPortraitWidth, BufferedImage.TYPE_4BYTE_ABGR);
		bimg.createGraphics().drawImage(img, 0, 0, headPortraitWidth, headPortraitWidth, null);
		
		chatPane.setCaretPosition(chatPane.getDocument().getLength());
		chatPane.insertIcon(new ImageIcon(bimg));
	}
	
	private void appendContent(String content, String fontFamily, int fontSize,
			boolean isBold, boolean isItalic, boolean hasUnderline, Color fontColor) {
		
		MutableAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributeSet, fontFamily);			//字体
		StyleConstants.setFontSize(attributeSet, fontSize);				//字号
		StyleConstants.setBold(attributeSet, isBold);					//粗体
		StyleConstants.setItalic(attributeSet, isItalic);				//斜体
		StyleConstants.setUnderline(attributeSet, hasUnderline);		//下划线
		StyleConstants.setForeground(attributeSet, fontColor);			//颜色

		chatPane.setEditable(true);
		chatPane.setCaretPosition(chatPane.getDocument().getLength());
		chatPane.setCharacterAttributes(attributeSet, false);
		chatPane.replaceSelection(content);
		chatPane.setEditable(false);
	}
	
	private void send(EventObject e) {
		String message = chatField.getText();
		if (!"".equals(message)) {
			String messages = Config.nickName + "\r\n" + Config.headPortrait + "\r\n" + message;
			if (Config.serving) {
				ArrayList<Integer> list = GamePlayThread.checkAnswer(message, 0);
				if (list != null) {
					MainFrame.getMainFrame().getGamePanelOnly().getScorePanel().guessed(list);
					ServerAction.sendData(DataType.GUESSED, list);
				} else {
					appendMessage(Config.nickName, Config.headPortrait, message);
					MainFrame.getMainFrame().getGamePanel().getScorePanel().speaking(message, 0);
					ServerAction.sendData(DataType.MESSAGE, messages + "\r\n0");
				}
			} else {
				ClientAction.sendData(DataType.MESSAGE, messages);
			}
			chatField.setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		send(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		send(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
