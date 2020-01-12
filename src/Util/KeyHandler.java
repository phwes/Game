package Util;

import Controller.Controller;
import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Handles all keyboard inputs before passing on the request(s) to the controller
 */

public class KeyHandler implements KeyListener {
    private Controller controller;
    private Key move_left;
    private Key move_right;
    private Key move_up;
    private Key move_down;
    private Key select;

    private HashMap<Integer, Key> keyMap;

    public KeyHandler(Controller controller, GamePanel gamePanel) {
        this.controller = controller;
        gamePanel.addKeyListener(this);

        keyMap = new HashMap<Integer, Key>();

        move_up = () -> controller.moveCursor("up");
        move_down = () -> controller.moveCursor("down");
        move_left = () -> controller.moveCursor("left");
        move_right = () -> controller.moveCursor("right");
        select = () -> controller.moveCursor("up");

        keyMap.put(KeyEvent.VK_UP, move_up);
        keyMap.put(KeyEvent.VK_DOWN, move_down);
        keyMap.put(KeyEvent.VK_LEFT, move_left);
        keyMap.put(KeyEvent.VK_RIGHT, move_right);
        keyMap.put(KeyEvent.VK_ENTER, select);

    }

    abstract interface Key{
        public abstract void action();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Do nothing
    }

    //  TODO: Make the solution maintainable for key changes
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Key key = keyMap.get(keyEvent.getKeyCode());
        key.action();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
