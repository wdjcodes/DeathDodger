package deathdodger.gameobjects;

import deathdodger.displayLayer;
import deathdodger.myColors;

import java.awt.*;

public class Enemy extends GameObject {

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final Color FILL_COLOR = myColors.LAVA;

    protected Enemy(int x, int y, gObjectID ID, Rectangle mapBounds){
        super(new Rectangle( x, y, WIDTH, HEIGHT), gObjectID.enemy, displayLayer.CHARACTER);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(FILL_COLOR);
        g.fill(getObjectShape());
        g.setColor(BORDER_COLOR);
        g.draw(getObjectShape());
    }

    @Override
    public void update() {

    }

    @Override
    public void restart() {

    }

    @Override
    public Shape getObjectShape() {
        return objectShape;
    }
}
