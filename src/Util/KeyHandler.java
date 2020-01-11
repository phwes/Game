package Util;

import Controller.Controller;
import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    public KeyHandler(Controller controller, GamePanel gamePanel) {
        this.controller = controller;
        gamePanel.addKeyListener(this);

        move_up = new Key();
        move_down = new Key();
        move_left = new Key();
        move_right = new Key();
        select = new Key();

    }

    class Key{
        private Boolean pressed = false;

        public void setPressed(Boolean pressed, int keyCode) {
            this.pressed = pressed;
            controller.action(keyCode);
        }
        public void setPressed(Boolean pressed) {
            this.pressed = pressed;
        }

        public Boolean getPressed() {
            return pressed;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Do nothing
    }

    //  TODO: Make the solution maintainable for key changes
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                if(!move_up.getPressed()){
                    move_up.setPressed(true, KeyEvent.VK_UP);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!move_down.getPressed()){
                    move_down.setPressed(true,  KeyEvent.VK_DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!move_left.getPressed()){
                    move_left.setPressed(true,  KeyEvent.VK_LEFT);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!move_right.getPressed()){
                    move_right.setPressed(true,  KeyEvent.VK_RIGHT);
                }
                break;
            case KeyEvent.VK_ENTER:
                if(!select.getPressed()){
                    select.setPressed(true,  KeyEvent.VK_ENTER);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (move_up.getPressed()) {
                    move_up.setPressed(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (move_down.getPressed()) {
                    move_down.setPressed(false);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (move_left.getPressed()) {
                    move_left.setPressed(false);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (move_right.getPressed()) {
                    move_right.setPressed(false);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (select.getPressed()) {
                    select.setPressed(false);
                }
                break;
        }
    }
}
