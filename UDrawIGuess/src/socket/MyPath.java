package socket;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Path2D;

/**
 * 绘制的线条类
 */
public class MyPath {
	private Color color;
	private Stroke stroke;
	private Path2D.Float path = new Path2D.Float(Path2D.WIND_EVEN_ODD);

	/**
	 * 构造函数
	 * @param color 线条颜色属性
	 * @param stroke Graphics2D的stroke属性，线条属性
	 * @param origin 线条的起始点
	 * @see java.awt.Graphics2D
	 * @see Point
	 */
	public MyPath(Color color, Stroke stroke, Point origin) {
		this.color = color;
		this.stroke = stroke;
		path.moveTo(origin.x, origin.y);
	}

	/**
	 * 从起始点到结束点绘制线条
	 * @param target 线条的结束点
	 * @see Point
	 */
	public void lineTo(Point target) {
		path.lineTo(target.x, target.y);
	}

	/**
	 * 获取线条颜色
	 * @return 线条颜色
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 获取线条属性
	 * @return 线条属性
	 * @see java.awt.Graphics2D
	 */
	public Stroke getStroke() {
		return stroke;
	}

	/**
	 * 获取线条
	 * @return 线条
	 * @see Path2D
	 */
	public Path2D.Float getPath() {
		return path;
	}
}
