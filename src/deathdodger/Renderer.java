package deathdodger;

import deathdodger.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer extends JPanel{

    private Map<displayLayer, List<GameObject>> layeredObjects;

    public Renderer(int x, int y){
        setPreferredSize(new Dimension(x, y));
        initializeLayers();
    }

    public Renderer(int x, int y, List<GameObject> gameObjects){
        setPreferredSize(new Dimension(x, y));
        initializeLayers();
        addObjects(gameObjects);
    }

    private void initializeLayers(){
        layeredObjects = new HashMap<>();
        for(int i = 0; i < displayLayer.values().length; i++){
            layeredObjects.put(displayLayer.values()[i], new ArrayList<>());
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if( layeredObjects != null){
            for( int i = 0; i < displayLayer.values().length; i++){
                List<GameObject> layer = layeredObjects.get(displayLayer.values()[i]);
                for(int j = 0; j < layer.size(); j++){
                    layer.get(j).draw(g2);
                }
            }
        }
    }

    public void removeObject(GameObject gameObject){
        List<GameObject> objectLayer = layeredObjects.get(gameObject.getLayer());
        objectLayer.remove(gameObject);
    }

    public void addObject(GameObject gameObject){
        List<GameObject> objectLayer = layeredObjects.get(gameObject.getLayer());
        objectLayer.add(gameObject);
    }

    public void addObjects(List<GameObject> gameObjects){
        for(int i = 0; i < gameObjects.size(); i++ ){
            GameObject newObject = gameObjects.get(i);
            List<GameObject> objectLayer = layeredObjects.get(newObject.getLayer());
            objectLayer.add(newObject);
        }
    }

}
