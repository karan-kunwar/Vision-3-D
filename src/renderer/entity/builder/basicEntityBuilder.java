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
		Tetrahedron tetra = new Tetrahedron(color, true, polygons);
		tetras.add(tetra);
        return new Entity(tetras);
	}

}
