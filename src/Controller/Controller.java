package Controller;

import Model.inGame.Map;
import Util.KeyHandler;
import View.GamePanel;

import java.awt.*;

/**
 * A controller, in standard MVC convention that invokes the model
 */

public class Controller {
    private Map map;

    public Controller(GamePanel gamePanel) {
        new KeyHandler(this, gamePanel);
        map = new Map(30,30,900,900);
    }

    public void action(int keyCode){
        System.out.println("You pressed a key with keycode: " + keyCode);
    }

    public void paintMap(Graphics g){
        map.drawMap(g);
    }
}
