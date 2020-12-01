package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import renderer.point.myVector;
import renderer.point.MyPoint;
import renderer.point.PointConverter;

public class MyPolygons {
	private static final double AmbientLight = 0.40;
	private MyPoint[] points;
	private Color baseColor,lightingColor;

	public MyPolygons(Color color,MyPoint... points) {
		this.baseColor = this.lightingColor = color;
		this.points = new MyPoint[points.length];
		for(int i=0;i<points.length;i++) {
			MyPoint p = points[i];
			this.points[i]= new MyPoint(p.x,p.y,p.z);
		}
	}

	public MyPolygons(MyPoint... points) {
		this.baseColor = this.lightingColor = Color.WHITE;
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
		g.setColor(this.lightingColor);
		g.fillPolygon(poly);
	}

	public void rotate(boolean clockwise, double xDeg,double yDeg,double zDeg,myVector lightVector) {
		for(MyPoint p: this.points) {
			PointConverter.rotateAlongX(p, clockwise, xDeg);
			PointConverter.rotateAlongY(p, clockwise, yDeg);
 			PointConverter.rotateAlongZ(p, clockwise, zDeg);
		}  	
		this.setLighting(lightVector);
	}

	public void setColor(Color color) {
		this.baseColor = this.lightingColor = color;
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


	public void setLighting(myVector lightVector){
		if(this.points.length<3){
			return;
		}
		myVector v1 = new myVector(this.points[0],this.points[1]);
		myVector v2 = new myVector(this.points[1],this.points[2]);
		myVector normal = myVector.normalize(myVector.cross(v2, v1));
		double dot = myVector.dot(normal, lightVector);
		double sign = dot<0 ? -1 : 1;
		dot = sign * dot * dot;
		dot = (dot+1)/2 *(1-AmbientLight);
		double lightRatio = Math.min(1,Math.max(0,AmbientLight + dot));
		this.updateLightingColor(lightRatio);
	}

	private void updateLightingColor(double lightRatio){
		int red = (int) (this.baseColor.getRed()*lightRatio);
		int green = (int) (this.baseColor.getGreen()*lightRatio);
		int blue = (int) (this.baseColor.getBlue()*lightRatio);
		this.lightingColor = new Color(red,green,blue);
	}
	
}
