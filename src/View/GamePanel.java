package View;

import Controller.Controller;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Controller controller;
    private int width;
    private int height;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        controller = new Controller(this);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setFocusable(true);
    }
}
