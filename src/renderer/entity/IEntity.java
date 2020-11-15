package renderer.entity;

import java.awt.Graphics;

public interface IEntity {
    
    void render(Graphics g);
    void rotate(boolean clockwise,double xDeg,double yDeg,double zDeg);
}
