package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private MyPolygons[] polygons;
	private Color color;
	public Tetrahedron(Color color,boolean decayColor,MyPolygons... polygons) {
		this.color = color;
		this.polygons = polygons;
		if(decayColor){
			this.setDecayPolygonColor();
		} else{
			this.setPolygonColor();
		}
		this.sortPolygons();
	}
	public Tetrahedron(MyPolygons... polygons) {
		this.color = Color.WHITE;
		this.polygons = polygons;
	}
	public void render(Graphics g) {
		for(MyPolygons mypoly: this.polygons) {
			mypoly.render(g);
		}
	}

	public void rotate(boolean clockwise,double xDeg,double yDeg,double zDeg) {
		for(MyPolygons p: this.polygons) {
			p.rotate(clockwise, xDeg, yDeg, zDeg);
		}
		this.sortPolygons();
	}

	public void sortPolygons() { 
		MyPolygons.sortPolygons(this.polygons);
	}

	public void setPolygonColor() {
		for(MyPolygons mypoly: this.polygons) {
			mypoly.setColor(this.color);
		}
	}

	public void setDecayPolygonColor(){
		double decayingF = 0.95;
		for(MyPolygons mypoly: this.polygons) {
			mypoly.setColor(this.color);
			int r = (int) (this.color.getRed() * decayingF);
			int g = (int) (this.color.getGreen() * decayingF);
			int b = (int) (this.color.getBlue() * decayingF);
			this.color = new Color(r,g,b);
		}
	}

}
