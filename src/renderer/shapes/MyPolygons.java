package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

public class MyPolygons {
	private MyPoint[] points;
	private Color color;
	public MyPolygons(Color color,MyPoint... points) {
		this.color = color;
		this.points = new MyPoint[points.length];
		for(int i=0;i<points.length;i++) {
			MyPoint p = points[i];
			this.points[i]= new MyPoint(p.x,p.y,p.z);
		}
	}
	public MyPolygons(MyPoint... points) {
		this.color = Color.WHITE;
		this.points = new MyPoint[points.length];
		for(int i=0;i<points.length;i++) {
			MyPoint p = points[i];
			this.points[i]= new MyPoint(p.x,p.y,p.z);
		}
	}
	public void render(Graphics g) {
		
		Polygon poly= new Polygon();
		for(int i=0;i<this.points.length;i++) {
			Point p = PointConverter.convertPoint(this.points[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);
	}
	public void rotate(boolean clockwise, double xDeg,double yDeg,double zDeg) {
		for(MyPoint p: points) {
			PointConverter.rotateAlongX(p, clockwise, xDeg);
			PointConverter.rotateAlongY(p, clockwise, yDeg);
			PointConverter.rotateAlongZ(p, clockwise, zDeg);
		}
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getAvgX() {
		double sum=0;
		for(MyPoint p: this.points) {
			sum+=p.x;
		}
		return sum/this.points.length;
	}
	public static MyPolygons[] sortPolygons(MyPolygons[] polygons) {
		List<MyPolygons> polygonList = new ArrayList<MyPolygons>();
		for(MyPolygons poly: polygons) {
			polygonList.add(poly);
		}
		Collections.sort(polygonList, new Comparator<MyPolygons>() {
			@Override 
			public int compare(MyPolygons p1,MyPolygons p2) {
				return (p2.getAvgX()-p1.getAvgX())<0?1:-1;
			}
		});
		for(int i=0;i<polygons.length;i++) {
			polygons[i]= polygonList.get(i);
		}
		return polygons;
	}
	
}
