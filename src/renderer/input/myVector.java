package renderer.input;

import renderer.point.MyPoint;

public class myVector {
    
    public double x,y,z;

    public myVector(){
        this.x=this.y=this.z=0;
    }

    public myVector(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public myVector(MyPoint p1,MyPoint p2){
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
        this.z = p2.z - p1.z;
    }

    public static double dot(myVector v1,myVector v2){
        return v1.x*v2.x + v1.y*v2.y + v1.z *v2.z;
    }

    public static myVector cross(myVector v1,myVector v2){
        return new myVector(
            v1.y*v2.z - v1.z*v2.y,
            v1.z*v2.x - v1.x*v2.z,
            v1.x*v2.y - v1.y*v2.x
        );
    }

    public static myVector normalize(myVector v){
        double magnitude = Math.sqrt(v.x*v.x+v.y*v.y+v.z*v.z);
        return new myVector(
            v.x/magnitude,
            v.y/magnitude,
            v.z/magnitude
        );
    }

}
