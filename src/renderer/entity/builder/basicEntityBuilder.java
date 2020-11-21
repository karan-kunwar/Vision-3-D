package renderer.entity.builder;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.shapes.*;
import renderer.point.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class basicEntityBuilder {
    
    public static IEntity createCube(double size,double centerX, double centerY, double centerZ){
		MyPoint p1 = new MyPoint(centerX+size/2,centerY-size/2,centerZ-size/2);
		MyPoint p2 = new MyPoint(centerX+size/2,centerY+size/2,centerZ-size/2);
		MyPoint p3 = new MyPoint(centerX+size/2,centerY+size/2,centerZ+size/2);
		MyPoint p4 = new MyPoint(centerX+size/2,centerY-size/2,centerZ+size/2);
		MyPoint p5 = new MyPoint(centerX-size/2,centerY-size/2,centerZ-size/2);
		MyPoint p6 = new MyPoint(centerX-size/2,centerY+size/2,centerZ-size/2);
		MyPoint p7 = new MyPoint(centerX-size/2,centerY+size/2,centerZ+size/2);
		MyPoint p8 = new MyPoint(centerX-size/2,centerY-size/2,centerZ+size/2);
		
		Tetrahedron tetra = new Tetrahedron(
				new MyPolygons(Color.BLUE,p5,p6,p7,p8),
				new MyPolygons(Color.GREEN,p1,p2,p6,p5 ),
				new MyPolygons(Color.YELLOW,p4,p3,p7,p8),
				new MyPolygons(Color.WHITE,p1,p5,p8,p4),
				new MyPolygons(Color.ORANGE,p2,p6,p7,p3),
				new MyPolygons(Color.RED,p1,p2,p3,p4)
                );
        List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
        tetras.add(tetra);
        return new Entity(tetras);
	}
	
	public static IEntity createDiamond(Color color,double size,double centerX, double centerY, double centerZ){
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		
		int edges = 10;
		double inFactor = 0.8;
		MyPoint bottom = new MyPoint(centerX, centerY, centerZ-size/2);
		MyPoint[] outerPoints = new MyPoint[edges];
		MyPoint[] innerPoints = new MyPoint[edges];

		for(int i=0;i<edges;i++){
			double theta = 2*Math.PI /edges * i;
			double xPos = -Math.sin(theta)*size/2; 
			double yPos = Math.cos(theta)*size/2;
			double zPos = size/2;
			outerPoints[i] = new MyPoint(centerX+xPos, centerY+yPos, centerZ+zPos*inFactor);
			innerPoints[i] = new MyPoint(centerX+xPos*inFactor, centerY+yPos*inFactor, centerZ+zPos);
		}

		MyPolygons[] polygons = new MyPolygons[2*edges+1];
		for(int i=0;i<edges;i++){
			polygons[i] = new MyPolygons(outerPoints[i%edges],bottom,outerPoints[(i+1)%edges]);
		}
		for(int i=0;i<edges;i++){
			polygons[i+edges] = new MyPolygons(outerPoints[i%edges],innerPoints[i], innerPoints[(i+1)%edges],outerPoints[(i+1)%edges]);
		}
		polygons[2*edges] = new MyPolygons(innerPoints);
		Tetrahedron tetra = new Tetrahedron(color,polygons);
		tetras.add(tetra);
        return new Entity(tetras);
	}

	public static IEntity createPyramid(double size,double centerX,double centerY,double centerZ){
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		double base = 100.0;
		MyPoint top = new MyPoint(centerX, centerY, centerZ+size/2);
		MyPoint[] basePoints = new MyPoint[4];
		basePoints[0] = new MyPoint(centerX+base/2, centerY+base/2, centerZ-size/2);
		basePoints[1] = new MyPoint(centerX+base/2, centerY-base/2, centerZ-size/2);
		basePoints[2] = new MyPoint(centerX-base/2, centerY-base/2, centerZ-size/2);
		basePoints[3] = new MyPoint(centerX-base/2, centerY+base/2, centerZ-size/2);
		MyPolygons[] polygons = new MyPolygons[5];
		polygons[0] = new MyPolygons(basePoints[0],basePoints[1],top);
		polygons[1] = new MyPolygons(basePoints[1],basePoints[2],top);
		polygons[2] = new MyPolygons(basePoints[2],basePoints[3],top);
		polygons[3] = new MyPolygons(basePoints[3],basePoints[0],top);
		polygons[4] = new MyPolygons(basePoints);
		Tetrahedron tetra = new Tetrahedron(new Color(237,201,175), polygons);
		tetras.add(tetra);
        return new Entity(tetras);
	}
	
	public static IEntity createOctahedron(double size, double centerX, double centerY, double centerZ) {
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		double base = 100.0;
		MyPoint top = new MyPoint(centerX, centerY, centerZ + size / 2);
		MyPoint[] basePoints = new MyPoint[4];
		basePoints[0] = new MyPoint(centerX + base / 2, centerY + base / 2, centerZ - size / 2);
		basePoints[1] = new MyPoint(centerX + base / 2, centerY - base / 2, centerZ - size / 2);
		basePoints[2] = new MyPoint(centerX - base / 2, centerY - base / 2, centerZ - size / 2);
		basePoints[3] = new MyPoint(centerX - base / 2, centerY + base / 2, centerZ - size / 2);
		MyPolygons[] polygons = new MyPolygons[9];
		polygons[0] = new MyPolygons(basePoints[0], basePoints[1], top);
		polygons[1] = new MyPolygons(basePoints[1], basePoints[2], top);
		polygons[2] = new MyPolygons(basePoints[2], basePoints[3], top);
		polygons[3] = new MyPolygons(basePoints[3], basePoints[0], top);
		polygons[4] = new MyPolygons(basePoints);

		MyPoint bottom = new MyPoint(centerX, centerY, centerZ - (3 * size) / 2);
		polygons[5] = new MyPolygons(basePoints[0], basePoints[1], bottom);
		polygons[6] = new MyPolygons(basePoints[1], basePoints[2], bottom);
		polygons[7] = new MyPolygons(basePoints[2], basePoints[3], bottom);
		polygons[8] = new MyPolygons(basePoints[3], basePoints[0], bottom);
		Tetrahedron tetra2 = new Tetrahedron(new Color(96, 83, 65), polygons);
		tetras.add(tetra2);
		return new Entity(tetras);
	}

	public static IEntity createIcosahedron(double size,double centerX, double centerY, double centerZ)
	{
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		double c0= 0.809016994374947424102293417183;
		c0*=size/2;

		MyPoint p1 = new MyPoint(centerX+size/4,centerY,centerZ+c0);
		MyPoint p2 = new MyPoint(centerX+size/4,centerY,centerZ-c0);
		MyPoint p3 = new MyPoint(centerX-size/4,centerY,centerZ+c0);
		MyPoint p4 = new MyPoint(centerX-size/4,centerY,centerZ-c0);
		MyPoint p5 = new MyPoint(centerX+c0,centerY+size/4,centerZ);
		MyPoint p6 = new MyPoint(centerX+c0,centerY-size/4,centerZ);
		MyPoint p7 = new MyPoint(centerX-c0,centerY+size/4,centerZ);
		MyPoint p8 = new MyPoint(centerX-c0,centerY-size/4,centerZ);
		MyPoint p9 = new MyPoint(centerX,centerY+c0,centerZ+size/4);
		MyPoint p10 = new MyPoint(centerX,centerY+c0,centerZ-size/4);
		MyPoint p11 = new MyPoint(centerX,centerY-c0,centerZ+size/4);
		MyPoint p12 = new MyPoint(centerX,centerY-c0,centerZ-size/4);

		MyPolygons[] polygons = new MyPolygons[20];

		polygons[0] = new MyPolygons(p1,p3,p11);
		polygons[1] = new MyPolygons(p1,p11,p6);
		polygons[2] = new MyPolygons(p1,p6,p5);
		polygons[3] = new MyPolygons(p1,p5,p9);
		polygons[4] = new MyPolygons(p1,p9,p3);
		
		polygons[5] = new MyPolygons(p4,p2,p12);
		polygons[6] = new MyPolygons(p4,p12,p8);
		polygons[7] = new MyPolygons(p4,p8,p7);
		polygons[8] = new MyPolygons(p4,p7,p10);
		polygons[9] = new MyPolygons(p4,p10,p2);

		polygons[10] = new MyPolygons(p3,p7,p8);
		polygons[11] = new MyPolygons(p3,p8,p11);

		polygons[12] = new MyPolygons(p11,p8,p12);
		polygons[13] = new MyPolygons(p11,p12,p6);

		polygons[14] = new MyPolygons(p6,p12,p2);
		polygons[15] = new MyPolygons(p6,p2,p5);

		polygons[16] = new MyPolygons(p5,p2,p10);
		polygons[17] = new MyPolygons(p5,p10,p9);

		polygons[18] = new MyPolygons(p9,p10,p7);
		polygons[19] = new MyPolygons(p9,p7,p3);

		Tetrahedron tetra = new Tetrahedron(Color.RED, polygons);
        tetras.add(tetra);
        return new Entity(tetras);	
	}

	public static IEntity createDodecahedron(double size, double centerX, double centerY, double centerZ) {
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();

		double c0 = (1 + Math.sqrt(5)) / 4;
		double c1 = (3 + Math.sqrt(5)) / 4;
		c0 *= size / 2;
		c1 *= size / 2;
		MyPoint[] points = new MyPoint[20];
		points[0] = new MyPoint(centerX, centerY + size / 4, centerZ + c1);
		points[1] = new MyPoint(centerX, centerY + size / 4, centerZ - c1);
		points[2] = new MyPoint(centerX, centerY - size / 4, centerZ + c1);
		points[3] = new MyPoint(centerX, centerY - size / 4, centerZ - c1);
		points[4] = new MyPoint(centerX + c1, centerY, centerZ + size / 4);
		points[5] = new MyPoint(centerX + c1, centerY, centerZ - size / 4);
		points[6] = new MyPoint(centerX - c1, centerY, centerZ + size / 4);
		points[7] = new MyPoint(centerX - c1, centerY, centerZ - size / 4);
		points[8] = new MyPoint(centerX + size / 4, centerY + c1, centerZ);
		points[9] = new MyPoint(centerX + size / 4, centerY - c1, centerZ);
		points[10] = new MyPoint(centerX - size / 4, centerY + c1, centerZ);
		points[11] = new MyPoint(centerX - size / 4, centerY - c1, centerZ);
		points[12] = new MyPoint(centerX + c0, centerY + c0, centerZ + c0);
		points[13] = new MyPoint(centerX + c0, centerY + c0, centerZ - c0);
		points[14] = new MyPoint(centerX + c0, centerY - c0, centerZ + c0);
		points[15] = new MyPoint(centerX + c0, centerY - c0, centerZ - c0);
		points[16] = new MyPoint(centerX - c0, centerY + c0, centerZ + c0);
		points[17] = new MyPoint(centerX - c0, centerY + c0, centerZ - c0);
		points[18] = new MyPoint(centerX - c0, centerY - c0, centerZ + c0);
		points[19] = new MyPoint(centerX - c0, centerY - c0, centerZ - c0);

		MyPolygons[] polygons = new MyPolygons[12];

		polygons[0] = new MyPolygons(points[0], points[2], points[14], points[4], points[12]);
		polygons[1] = new MyPolygons(points[0], points[12], points[8], points[10], points[16]);
		polygons[2] = new MyPolygons(points[0], points[16], points[6], points[18], points[2]);
		polygons[3] = new MyPolygons(points[7], points[6], points[16], points[10], points[17]);
		polygons[4] = new MyPolygons(points[7], points[17], points[1], points[3], points[19]);
		polygons[5] = new MyPolygons(points[7], points[19], points[11], points[18], points[6]);
		polygons[6] = new MyPolygons(points[9], points[11], points[19], points[3], points[15]);
		polygons[7] = new MyPolygons(points[9], points[15], points[5], points[4], points[14]);
		polygons[8] = new MyPolygons(points[9], points[14], points[2], points[18], points[11]);
		polygons[9] = new MyPolygons(points[13], points[1], points[17], points[10], points[8]);
		polygons[10] = new MyPolygons(points[13], points[8], points[12], points[4], points[5]);
		polygons[11] = new MyPolygons(points[13], points[5], points[15], points[3], points[1]);

		Tetrahedron tetra = new Tetrahedron(Color.cyan, polygons);
		tetras.add(tetra);
		return new Entity(tetras);
	}
}
