package deathdodger;

import deathdodger.gameobjects.GameObject;

import javax.swing.*;
import java.util.List;

/**
 * Created by johnswd on 7/20/2018.
 */
public class GameLoop implements Runnable{

    private static final int MS = 1000;

    private final DeathDodger DeathDodger;
    private final List<GameObject> gameObjects;

    private volatile boolean running;

    private int frameDelayMS;

    public GameLoop(DeathDodger DeathDodger, List<GameObject> gameObjects, int frameRate ){
        this.DeathDodger = DeathDodger;
        this.gameObjects = gameObjects;
        this.frameDelayMS = MS/frameRate;
        setRunning( false );
    }

    @Override
    public void run() {
        setRunning( true );
        while(running) {
            updateObjects();
            drawMap();
            sleep();
        }
    }

    private void updateObjects(){
        for(int i = 0; i < gameObjects.size(); i++){
            gameObjects.get( i ).update();
        }
    }

    private void drawMap(){
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                DeathDodger.repaintDisplay();
            }
        } );
    }

    private void sleep(){
        try{
            Thread.sleep( frameDelayMS );
        } catch ( InterruptedException e ){

        }
    }

    public synchronized void setRunning(boolean running){
        this.running = running;
    }
}
