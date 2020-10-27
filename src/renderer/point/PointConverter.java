package renderer.point;

import java.awt.Point;

import renderer.Display;

public class PointConverter {
	private static double scale =1;
	public static Point convertPoint(MyPoint point3D) {
		double x3d = point3D.y*scale;
		double y3d = point3D.z*scale;
		double depth = point3D.x*scale;
		double[] newvalue = scale(x3d,y3d,depth);
				
		int x2d = (int)(Display.WIDTH/2+newvalue[0]);
		int y2d = (int)(Display.HEIGHT/2-newvalue[1]);
		Point point2d = new Point(x2d,y2d);
		return point2d;
	}
	
	public static double[] scale(double x3d,double y3d,double depth) {
		double dist = Math.sqrt(x3d*x3d+y3d*y3d);
		double theta = Math.atan2(y3d, x3d);
		double depth2 = 15 - depth;
		double localScale = Math.abs(1400/(1400+depth2));
		dist = dist* localScale;
		double[] newCoord = new double[2];
		newCoord[0] = (dist*Math.cos(theta));
		newCoord[1] =   (dist*Math.sin(theta));
		return newCoord;
	}
	public static void rotateAlongX(MyPoint p, boolean clockwise, double degrees) {
//		double radius = Math.sqrt(p.y*p.y+p.z*p.z);
//		double theta = Math.atan2(p.z, p.y);
		double theta = 2*Math.PI/360*degrees*(clockwise?-1:1);
		double tempY=0.0,tempZ=0.0;
		tempY= Math.cos(theta)*p.y-Math.sin(theta)*p.z;
		tempZ= Math.sin(theta)*p.y+Math.cos(theta)*p.z;
		p.y = tempY;
		p.z = tempZ;
	} 
	public static void rotateAlongY(MyPoint p, boolean clockwise, double degrees) {
//		double radius = Math.sqrt(p.x*p.x+p.z*p.z);
//		double theta = Math.atan2(p.x, p.z);
		double theta = 2* (Math.PI/360)*degrees*(clockwise?-1:1);
		double tempX=0.0,tempZ=0.0;
		tempX = Math.cos(theta)*p.x+Math.sin(theta)*p.z;
		tempZ = -Math.sin(theta)*p.x+Math.cos(theta)*p.z;
		p.x = tempX;
		p.z = tempZ;
	} 
	public static void rotateAlongZ(MyPoint p, boolean clockwise, double degrees) {
//		double radius = Math.sqrt(p.y*p.y+p.x*p.x);
//		double theta = Math.atan2(p.y, p.x);
		double theta = 2* (Math.PI/360)*degrees*(clockwise?-1:1);
		double tempX=0.0,tempY=0.0;
		tempX = Math.cos(theta)*p.x-Math.sin(theta)*p.y;
		tempY = Math.sin(theta)*p.x+Math.cos(theta)*p.y;
		p.y = tempY;
		p.x = tempX;
	} 
}

