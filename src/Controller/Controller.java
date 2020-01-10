package Controller;

import Model.inGame.Map;
import Util.KeyHandler;
import View.GamePanel;

/**
 * A controller, in standard MVC convention that invokes the model
 */

public class Controller {
    private Map map;

    public Controller(GamePanel gamePanel) {
        new KeyHandler(this, gamePanel);
    }

    public void action(int keyCode){
        System.out.println("You pressed a key with keycode: " + keyCode);
    }
}
