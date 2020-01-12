package Controller;

import Model.inGame.Map;
import States.Scene;
import Util.KeyHandler;
import View.GamePanel;

import java.awt.*;

/**
 * A controller, in standard MVC convention that invokes the model
 */

public class Controller {
    private Map map;
    private GamePanel gamePanel;
    private Scene scene;

    public Controller(GamePanel gamePanel) {
        new KeyHandler(this, gamePanel);
        map = new Map(30,30,900,900);
        this.gamePanel = gamePanel;
        scene = new Scene("map_scene");
    }

    public void action(){
        System.out.println("You pressed a key.");
    }

    public void paintMap(Graphics g){
        map.drawMap(g);
    }

    public void moveCursor(String direction){
        switch (direction){
            case "up":
                map.moveCursor('u');
                break;
            case "down":
                map.moveCursor('d');
                break;
            case "left":
                map.moveCursor('l');
                break;
            case "right":
                map.moveCursor('r');
                break;
        }
        // update the map when cursor has moved
        gamePanel.repaint();
    }
}
