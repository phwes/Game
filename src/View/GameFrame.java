package View;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        setTitle("Advanced Conflict");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel(900, 900);
        setContentPane(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
