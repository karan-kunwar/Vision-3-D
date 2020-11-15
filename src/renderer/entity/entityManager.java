package renderer.entity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;

import renderer.entity.builder.basicEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Mouse;
import renderer.point.PointConverter;

public class entityManager {
    private List<IEntity> entities;
    private int initialX;
    private int initialY;

    public entityManager(){
        this.entities = new ArrayList<IEntity>();
    }

    public void init(){
        this.entities.add(basicEntityBuilder.createDiamond(new Color(200,40,150), 100, 0, 0, 0));
    }

    public void update(Mouse mouse){
        int x= mouse.getX();
		int y= mouse.getY();
		
		if(mouse.isDraging && mouse.getButton() == ClickType.LeftClick) {
			int xDiff = x -this.initialX;
			int yDiff = y -this.initialY;
			this.rotate(true, 0, -(yDiff/1.5), -(xDiff/1.5));
		}
		if(mouse.isDraging && mouse.getButton() == ClickType.RightClick) {
			int xDiff = x -initialX;
			this.rotate(true, -(xDiff/1.5), 0, 0);
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

    public void rotate(boolean clockwise,double xDeg,double yDeg,double zDeg){
        for(IEntity entity: this.entities){
            entity.rotate(clockwise, xDeg, yDeg, zDeg);
        }
    }
}
