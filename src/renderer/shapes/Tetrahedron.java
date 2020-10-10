package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private MyPolygons[] polygons;
	private Color color;
	public Tetrahedron(Color color, MyPolygons... polygons) {
		this.color = color;
		this.polygons = polygons;
		this.setPolygonColor();
	}
	public void render(Graphics g) {
		for(MyPolygons mypoly: this.polygons) {
			mypoly.render(g);
		}
	}
	public void setPolygonColor() {
		for(MyPolygons mypoly: this.polygons) {
			mypoly.setColor(this.color);
		}
	}
}
