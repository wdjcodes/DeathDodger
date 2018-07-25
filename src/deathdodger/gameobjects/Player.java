package deathdodger.gameobjects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

import deathdodger.displayLayer;
import deathdodger.myShapes.Circle2D;

/**
 * Created by johnswd on 7/16/2018.
 */
public class Player extends GameObject{

    private static Color COLOR = Color.BLUE;
    private static int DIAM = 16;
    private static int MAX_VELOCITY = 5;


    private List<GameObject> gameObjects;

    public Player(int xMax, int yMax, List<GameObject> gameObjects){

        super(new Circle2D( new Point2D.Double( xMax/2, yMax/2 ), DIAM/2 ), gObjectID.player, displayLayer.CHARACTER);
        this.gameObjects = gameObjects;
    }

    public Player(int x, int y, int xMax, int yMax, List<GameObject>gameObjects){
        super(new Circle2D( new Point2D.Double( x, y ), DIAM/2), gObjectID.player, displayLayer.CHARACTER);
        this.gameObjects = gameObjects;
    }

    public void draw(Graphics2D g){
        g.setColor( COLOR );
        g.fill(getObjectShape());
//        g.fillOval( (int) getObjectShape().getX(), (int) getObjectShape().getY(), DIAM, DIAM );
        g.setColor( BORDER_COLOR );
        g.draw(getObjectShape());
//        g.drawOval( (int) getObjectShape().getX(), (int) getObjectShape().getY(), DIAM, DIAM );
    }

    private void move(){
        velocity.add(acceleration).capMagnitude( MAX_VELOCITY );
        getObjectShape().move(velocity.actOnPoint( getObjectShape().getCenter() ));
    }

    public void update(){
        move();
        for(int i = 0; i < gameObjects.size(); i++){
            GameObject tmpObject = gameObjects.get(i);
            if(tmpObject.getID() == gObjectID.enemy && objectCollide( tmpObject )){
                //TODO: Enemy collision code
                return;
            }
        }
    }

    @Override
    public void restart() {
        return;
    }

    private boolean objectCollide(GameObject m){
        return getObjectShape().collides(m.getObjectShape());
    }

    private boolean mapCollide(){
        if( getObjectShape().getCenterX() < mapBounds.getX() + DIAM/2 ){
            getObjectShape().move( mapBounds.getX() + DIAM / 2, getObjectShape().getCenterY() );
        } else if( getObjectShape().getCenterX() > mapBounds.getX() + mapBounds.getWidth() - 1 - DIAM/2){
            getObjectShape().move( mapBounds.getX() + mapBounds.getWidth() - 1 - DIAM/2, getObjectShape().getCenterY() );
        } else if( getObjectShape().getCenterY() < mapBounds.getY() + DIAM/2){
            getObjectShape().move( getObjectShape().getCenterX(), mapBounds.getY() + DIAM/2 );
        } else if( getObjectShape().getCenterY() > mapBounds.getY() + mapBounds.getHeight() - 1 - DIAM/2){
            getObjectShape().move( getObjectShape().getCenterX(), mapBounds.getY() + mapBounds.getHeight() - 1 - DIAM/2 );
        } else{
            return false;
        }
        return true;
    }

    public int getRadius(){
        return DIAM/2;
    }

    public Point2D.Double getLocation(){
        return getObjectShape().getCenter();
    }

    @Override
    public Circle2D getObjectShape(){
        return (Circle2D)objectShape;
    }

}
