package deathdodger;

import deathdodger.gameobjects.GameObject;
import deathdodger.gameobjects.MapBounds;
import deathdodger.gameobjects.Player;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class DeathDodger implements Runnable {

    private static final int FRAME_RATE = 45;
    private static int xMax = 600;
    private static int yMax = 600;

    private List<GameObject> gameObjects = new ArrayList<>(  );
    private JFrame frame;
    private Renderer renderer;
    private GameLoop gameLoop;

    private DeathDodger(){}

    @Override
    public void run() {
        frame = new JFrame( "deathdodger.DeathDodger" );
        frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
        renderer = new Renderer( xMax, yMax);
        frame.add(renderer);
        frame.pack();
        frame.setVisible( true );
        frame.addWindowListener( new WindowClosingHandler() );

        gameObjects.add(new MapBounds(xMax, yMax));
        gameObjects.add(new Player(xMax, yMax, gameObjects));

        renderer.addObjects(gameObjects);
        renderer.repaint(  );

        gameLoop = new GameLoop( this, gameObjects, FRAME_RATE );
        new Thread( gameLoop ).start();

    }

    private void exitProcedure(){
        gameLoop.setRunning(false);
        frame.dispose();
        System.exit( 0 );
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater( new DeathDodger() );
    }

    private class WindowClosingHandler extends WindowAdapter{
        @Override
        public void windowClosing( WindowEvent eevent ) {
            exitProcedure();
        }
    }

    public void repaintDisplay(){
        renderer.repaint(  );
    }

}
