package socket;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Path2D;

/**
 * ���Ƶ�������
 */
public class MyPath {
	private Color color;
	private Stroke stroke;
	private Path2D.Float path = new Path2D.Float(Path2D.WIND_EVEN_ODD);

	/**
	 * ���캯��
	 * @param color ������ɫ����
	 * @param stroke Graphics2D��stroke���ԣ���������
	 * @param origin ��������ʼ��
	 * @see java.awt.Graphics2D
	 * @see Point
	 */
	public MyPath(Color color, Stroke stroke, Point origin) {
		this.color = color;
		this.stroke = stroke;
		path.moveTo(origin.x, origin.y);
	}

	/**
	 * ����ʼ�㵽�������������
	 * @param target �����Ľ�����
	 * @see Point
	 */
	public void lineTo(Point target) {
		path.lineTo(target.x, target.y);
	}

	/**
	 * ��ȡ������ɫ
	 * @return ������ɫ
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * ��ȡ��������
	 * @return ��������
	 * @see java.awt.Graphics2D
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * ��ȡ����
	 * @return ����
	 * @see Path2D
	 */
	public Path2D.Float getPath() {
		return path;
	}
}
