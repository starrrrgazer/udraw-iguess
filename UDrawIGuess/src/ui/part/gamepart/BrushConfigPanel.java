package ui.part.gamepart;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import socket.ClientAction;
import socket.Config;
import socket.DataPackage;
import socket.ServerAction;
import ui.mergeFace.MainFrame;
import ui.part.TypeLabel;
import ui.part.TypeLabel.Type;
import socket.DataPackage.DataType;

public class BrushConfigPanel extends JPanel implements MouseListener {
	private enum CircleType { COLOR, THICKNESS, TYPE }
	private CircleType circleType = CircleType.COLOR;
	private float progress;
	private int index;
	
	private Color[] colors;
	private PigmentLabel[] pigmentLabels = new PigmentLabel[14];
	
	private ThicknessLabel[] thicknessLabels = new ThicknessLabel[5];
	
	private TypeLabel[] typeLabels = new TypeLabel[3];
	
	public BrushConfigPanel() {		
		initColors();
		initThickness();
		initType();
		
		setLayout(null);
		setOpaque(false);
	}
	
	private void initColors() {
		colors = new Color[] { Color.BLACK, Color.GRAY, Color.LIGHT_GRAY, Color.PINK, Color.MAGENTA,
				Color.RED, Color.BLUE, Color.ORANGE, Color.CYAN, Color.YELLOW, Color.GREEN,
				new Color(192, 255, 0), new Color(150, 150, 0), new Color(150, 100, 50) };
		
		for (int i = 0; i < pigmentLabels.length; i++) {
			pigmentLabels[i] = new PigmentLabel(colors[i], i);
			pigmentLabels[i].setBounds((i & 1) * 27 + 4, (i >> 1) * 27, 25, 25);
			pigmentLabels[i].addMouseListener(this);
			this.add(pigmentLabels[i]);
		}
	}
	
	private void initThickness() {
		for (int i = 0; i < thicknessLabels.length; i++) {
			thicknessLabels[i] = new ThicknessLabel(i + 1, i);
			thicknessLabels[i].setBounds((i & 1) * 26 + 4, (i >> 1) * 26 + 197, 26, 26);
			thicknessLabels[i].addMouseListener(this);
			this.add(thicknessLabels[i]);
		}
	}
	
	private void initType() {
		typeLabels[0] = new TypeLabel(Type.BRUSH, 0);
		typeLabels[0].setBounds(4, 284, 26, 26);
		typeLabels[0].addMouseListener(this);
		this.add(typeLabels[0]);
		
		typeLabels[1] = new TypeLabel(Type.ERASER, 1);
		typeLabels[1].setBounds(30, 284, 26, 26);
		typeLabels[1].addMouseListener(this);
		this.add(typeLabels[1]);
		
		typeLabels[2] = new TypeLabel(Type.CLEAR, 2);
		typeLabels[2].setBounds(4, 315, 26, 26);
		typeLabels[2].addMouseListener(this);
		this.add(typeLabels[2]);
	}
	
	public void initBrush() {		
		setPigment(pigmentLabels[0]);
		setThickness(thicknessLabels[1]);
		setType(typeLabels[0]);
		canvasClear();
	}
	
	public void setPigment(PigmentLabel pigmentLabel) {
		pigmentLabel.choose();
		if (TypeLabel.chosen == 0) {
			Color color = pigmentLabel.getColor();
			if (Config.serving) {
				MainFrame.getMainFrame().getGamePanelOnly().getDrawPanel().setPigment(color);
				ServerAction.sendData(DataPackage.DataType.BRUSH_PIGMENT, color);
			} else {
				ClientAction.sendData(DataPackage.DataType.BRUSH_PIGMENT, color);
			}
		}
	}
	
	private void setThickness(ThicknessLabel thicknessLabel) {
		thicknessLabel.choose();
		int thickness = thicknessLabel.getThickness();
		thickness = thickness * thickness;
		if (Config.serving) {
			MainFrame.getMainFrame().getGamePanelOnly().getDrawPanel().setThickness(thickness);
			ServerAction.sendData(DataType.BRUSH_SIZE, thickness);
		} else {
			ClientAction.sendData(DataType.BRUSH_SIZE, thickness);
		}
	}
	
	private void setType(TypeLabel typeLabel) {
		typeLabel.choose();
		Color color = typeLabel.getType() == Type.BRUSH ? colors[PigmentLabel.chosen] : Color.WHITE;
		if (Config.serving) {
			MainFrame.getMainFrame().getGamePanelOnly().getDrawPanel().setPigment(color);
			ServerAction.sendData(DataType.BRUSH_PIGMENT, color);
		} else {
			ClientAction.sendData(DataType.BRUSH_PIGMENT, color);
		}
	}
	
	private void canvasClear() {
		if (Config.serving) {
			MainFrame.getMainFrame().getGamePanelOnly().getDrawPanel().canvasClear();
			ServerAction.sendData(DataType.CANVAS_CLEAR, null);
		} else {
			ClientAction.sendData(DataType.CANVAS_CLEAR, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!isEnabled()) {
			return;
		}
		if (e.getSource() instanceof PigmentLabel) {
			setPigment((PigmentLabel) e.getSource());
		} else if (e.getSource() instanceof ThicknessLabel) {
			setThickness((ThicknessLabel) e.getSource());
		} else if (e.getSource() instanceof TypeLabel) {
			if (e.getSource() != typeLabels[2]) {
				setType((TypeLabel) e.getSource());
			} else {
				canvasClear();
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
