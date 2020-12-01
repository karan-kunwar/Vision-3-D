package renderer.entity;

import java.awt.Graphics;

import renderer.point.myVector;

public interface IEntity {
    
    void render(Graphics g);
    void rotate(boolean clockwise,double xDeg,double yDeg,double zDeg,myVector lightVector);
    void setLighting(myVector lightVector);
}
