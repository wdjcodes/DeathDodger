package deathdodger.gameobjects;

import java.awt.*;
import deathdodger.MotionVector;
import deathdodger.displayLayer;

/**
 * Created by johnswd on 7/18/2018.
 */
public abstract class GameObject {

    protected static Color BORDER_COLOR = Color.BLACK;

    protected MotionVector velocity, acceleration;
    protected Shape objectShape;
    protected gObjectID ID;
    protected Rectangle mapBounds;


    protected displayLayer layer;

    protected GameObject(Shape objectShape, gObjectID ID, displayLayer layer){
        this.objectShape = objectShape;
        this.ID = ID;
        this.layer = layer;
        velocity = new MotionVector(  );
        acceleration = new MotionVector(  );
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void restart();

    public abstract Shape getObjectShape();

    public Rectangle getObjectBounds(){
        return objectShape.getBounds();
    }

    public gObjectID getID(){
        return ID;
    }

    public displayLayer getLayer() {
        return layer;
    }



}