package Model.mainMenu;

import java.awt.*;

public class MainMenu {
    private MenuCursor menuCursor;
    private int frameHeight;
    private int frameWidth;

    private static final Color buttonColor = Color.ORANGE;

    public MainMenu(int frameHeight, int frameWidth) {
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        menuCursor = new MenuCursor(1);
    }

    public void drawMainMenu(Graphics g){
        // Background
        g.setColor(Color.GRAY);
        g.fillRect(0,0, frameWidth, frameHeight);

        drawButtons(g);
    }

    // TODO: Make button class
    private void drawButtons(Graphics g){
        int buttonWidth = 400;
        int buttonHeight = 100;
        drawButton(g, (frameWidth-buttonWidth)/2, (frameHeight-buttonHeight)/3, buttonWidth,buttonHeight);
        drawButton(g, (frameWidth-buttonWidth)/2, (frameHeight-buttonHeight)/3*2, buttonWidth,buttonHeight);
    }

    private void drawButton(Graphics g, int x, int y, int width, int height){
        g.setColor(buttonColor);
        g.fillRect(x,y,width,height);
    }
}
