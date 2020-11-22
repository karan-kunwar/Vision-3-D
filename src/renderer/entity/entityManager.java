package renderer.entity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;

import renderer.entity.builder.basicEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Mouse;
import renderer.input.myVector;
import renderer.point.PointConverter;

public class entityManager {
    private List<IEntity> entities;
    private int initialX;
    private int initialY;
    private myVector lightVector =  myVector.normalize(new myVector(1,1,1));
    public static double speedx=1.0;
    public static double speedy=1.0;
    public static double speedz=1.0;

    public entityManager(){
        this.entities = new ArrayList<IEntity>();
    }

    public void init(String shape){
        if(shape=="Cube")	
            this.entities.add(basicEntityBuilder.createCube(200, 0, 0, 0));
        else if(shape=="Diamond")
            this.entities.add(basicEntityBuilder.createDiamond(new Color(200,40,150), 250, 0, 0, 0));
        else if(shape=="Pyramid")
            this.entities.add(basicEntityBuilder.createPyramid(250, 0, 0, 0));
        else if(shape=="Octahedron")
            this.entities.add(basicEntityBuilder.createOctahedron(150, 0, 0, 0));
        else if(shape=="Icosahedron")
            this.entities.add(basicEntityBuilder.createIcosahedron(250, 0, 0, 0));
        else if (shape == "Dodecahedron")
            this.entities.add(basicEntityBuilder.createDodecahedron(200, 0, 0, 0));
        this.setLighting();
    }

    public void update(){
    	this.rotate(true, speedx, speedy, speedz, lightVector);
    }

    public void update(Mouse mouse){
        int x= mouse.getX();
		int y= mouse.getY();
		
		if(mouse.isDraging && mouse.getButton() == ClickType.LeftClick) {
			int xDiff = x -this.initialX;
			int yDiff = y -this.initialY;
			this.rotate(true, 0, -(yDiff/1.5), -(xDiff/1.5),lightVector);
		}
		if(mouse.isDraging && mouse.getButton() == ClickType.RightClick) {
			int xDiff = x -initialX;
			this.rotate(true, -(xDiff/1.5), 0, 0,lightVector);
		}
		initialX= x; 
		initialY= y;
		if(mouse.isScrollingUp()) {
			PointConverter.zoomOut();
		}else if(mouse.isScrollingDown()) {
			PointConverter.zoomIn();
		}
		mouse.resetScroll();
    }

    public void render(Graphics g){
        for(IEntity entity: this.entities){
            entity.render(g);
        }
    }

    public void rotate(boolean clockwise,double xDeg,double yDeg,double zDeg,myVector lightVector){
        for(IEntity entity: this.entities){
            entity.rotate(clockwise, xDeg, yDeg, zDeg,lightVector);
        }
    }

    private void setLighting(){
        for(IEntity entity: this.entities){
            entity.setLighting(this.lightVector);
        }
    }
}
