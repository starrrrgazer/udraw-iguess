package ui.part;

import Manager.FontManager;
import Manager.ImageManager;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 输入的文本框类
 */
public class MyTextField extends JPanel {
	
	private JTextField textField;
	private int offsetX = 6;
	private int offsetY = 0;
	
	private Font font = FontManager.getDefaultFontManager().getInputFieldFont();
	private Image img = ImageManager.getDefaultImageManager().getInputFieldBg();

	/**
	 * 默认构造函数
	 */
	public MyTextField() {
		this(null);
	}

	/**
	 * 构造函数
	 * @param text 文本框的名字，即显示的内容
	 */
	public MyTextField(String text) {
		textField = new JTextField(text);
		textField.setFont(font);
		textField.setOpaque(false);
		textField.setBorder(null);
		
		add(textField);
		setLayout(null);
		setOpaque(false);
	}

	/**
	 * 获取文本框的名字
	 * @return 文本框的名字
	 */
	public String getText() {
		return textField.getText();
	}

	/**
	 * 设置文本框的名字
	 * @param text 文本框的名字
	 */
	public void setText(String text) {
		textField.setText(text);
	}

	/**
	 * 获取文本框提示内容
	 * @return
	 */
	@Override
	public String getToolTipText() {
		return textField.getToolTipText();
	}

	/**
	 * 设置文本框提示内容
	 * @param text
	 */
	@Override
	public void setToolTipText(String text) {
		textField.setToolTipText(text);
	}

	/**
	 * 给文本框添加事件监听
	 * @param listener actionlistener动作监听
	 */
	public void addActionListener(ActionListener listener) {
		textField.addActionListener(listener);
	}

	/**
	 * 设置文本框的位置和大小
	 * @param x 文本框水平偏移量
	 * @param y 文本框垂直偏移量
	 * @param width 文本框宽度
	 * @param height 文本框高度
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		textField.setBounds(offsetX, offsetY, width - (offsetX << 1), height - (offsetY << 1));
	}

	/**
	 * 绘制文本框
	 * @param g Graphics 方法重载
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setClip(0, 0, height >> 1, height);
		g2d.drawImage(img, 0, 0, height, height, this);
		g2d.setClip(height >> 1, 0, width - height, height);
		g2d.drawImage(img, -width >> 1, 0, width << 1, height, this);
		g2d.setClip(width - (height >> 1), 0, height >> 1, height);
		g2d.drawImage(img, width - height, 0, height, height, this);
		g2d.setClip(null);
	}
}
