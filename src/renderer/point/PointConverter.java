package renderer.point;

import java.awt.Point;

import renderer.Display;

public class PointConverter {
	public static Point convertPoint(MyPoint point3D) {
		int x2d = Display.WIDTH/2+(int)point3D.y;
		int y2d = Display.HEIGHT/2-(int)point3D.z;
		Point point2d = new Point(x2d,y2d);
		return point2d;
	}
}
