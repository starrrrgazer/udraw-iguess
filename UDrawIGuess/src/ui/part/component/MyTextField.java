package ui.part.component;

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




public class MyTextField extends JPanel {
	
	private JTextField textField;
	private int offsetX = 6;
	private int offsetY;
	
	private Font font = FontManager.getDefaultFontManager().getInputFieldFont();
	private Image img = ImageManager.getDefaultImageManager().getInputFieldBg();
	
	public MyTextField() {
		this(null);
	}
	
	public MyTextField(String text) {
		textField = new JTextField(text);
		textField.setFont(font);
		textField.setOpaque(false);
		textField.setBorder(null);
		
		add(textField);
		setLayout(null);
		setOpaque(false);
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
	
	@Override
	public String getToolTipText() {
		return textField.getToolTipText();
	}
	
	@Override
	public void setToolTipText(String text) {
		textField.setToolTipText(text);
	}
	
	public void addActionListener(ActionListener listener) {
		textField.addActionListener(listener);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		textField.setBounds(offsetX, offsetY, width - (offsetX << 1), height - (offsetY << 1));
	}
	
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
