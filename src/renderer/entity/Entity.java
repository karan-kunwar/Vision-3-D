package renderer.entity;

import java.awt.Graphics;
import java.util.List;

import renderer.shapes.Tetrahedron;

public class Entity implements IEntity {

    private List<Tetrahedron> tetrahedrons;

    public Entity(List<Tetrahedron> tetrahedrons){
        this.tetrahedrons = tetrahedrons;
    }

    @Override
    public void render(Graphics g) {
        for(Tetrahedron tetra: this.tetrahedrons){
            tetra.render(g);
        }
    }

    @Override
    public void rotate(boolean clockwise, double xDeg, double yDeg, double zDeg) {
        for(Tetrahedron tetra: this.tetrahedrons){
            tetra.rotate(clockwise, xDeg, yDeg, zDeg);
        }

    }
    
}
