package deathdodger.myShapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle2D extends Ellipse2D.Double {

    Point2D.Double center;
    double radius;


    public Circle2D( Point2D.Double center, double radius){
        super(center.getX() - radius, center.getY() - radius, radius*2, radius*2);
        this.center = center;
        this.radius = radius;
    }

    public Circle2D move(Point2D.Double center){
        this.center = center;
        x = center.getX() - radius;
        y = center.getY() - radius;
        return this;
    }

    public Circle2D move(double x, double y){
        center = new Point2D.Double( x, y );
        this.x = center.getX() - radius;
        this.y = center.getY() - radius;
        return this;
    }

    public Circle2D resize(double radius){
        this.radius = radius;
        x = center.getX() - radius;
        y = center.getY() - radius;
        return this;
    }

    public boolean intersects(Circle2D c){
        return center.distance( c.getCenter() ) <= (radius + c.getRadius());
    }


    public boolean collides(Shape s){
        String cName = s.getClass().getSimpleName();
        switch (cName){
            case "Circle2D":
                return intersects( (Circle2D)s );
            case "Rectangle":
                Rectangle r = (Rectangle)s;
                return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            default:
                System.out.println( cName );
                return intersects( s );
        }
    }

    public boolean intersects(Shape s ){
        throw new IllegalArgumentException("Object type " + s.getClass() + " is not supported");
    }

    public Point2D.Double getCenter(){
        return center;
    }

    public double getRadius(){
        return radius;
    }
}
