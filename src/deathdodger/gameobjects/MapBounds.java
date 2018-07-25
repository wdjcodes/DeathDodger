package deathdodger.gameobjects;

import deathdodger.displayLayer;
import deathdodger.myColors;

import java.awt.*;

public class MapBounds extends GameObject {

    private static final Color BACKGROUND_COLOR = myColors.CHARCOAL;
    private static final Color BORDER_COLOR = myColors.SNOW;

    private boolean borderVisible = false;

    public MapBounds(int xMax, int yMax){
        super(new Rectangle(0, 0, xMax - 1, yMax - 1), gObjectID.mapBounds, displayLayer.BACKGROUND);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(BACKGROUND_COLOR);
        g.fill(getObjectShape());
        if(borderVisible) {
            g.setColor(BORDER_COLOR);
            g.draw(getObjectShape());
        }
    }

    @Override
    public void update() {
        return;
    }

    @Override
    public void restart() {
        return;
    }

    @Override
    public Shape getObjectShape() {
        return objectShape;
    }
}
