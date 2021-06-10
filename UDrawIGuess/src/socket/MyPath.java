package socket;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Path2D;

public class MyPath {
	private Color color;
	private Stroke stroke;
	private Path2D.Float path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
	
	public MyPath(Color color, Stroke stroke, Point origin) {
		this.color = color;
		this.stroke = stroke;
		path.moveTo(origin.x, origin.y);
	}
	
	public void lineTo(Point target) {
		path.lineTo(target.x, target.y);
	}

	public Color getColor() {
		return color;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public Path2D.Float getPath() {
		return path;
	}
}
