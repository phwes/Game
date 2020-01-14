package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class that stores all sprites needed
 */

public class Sprites {
    public BufferedImage tile_cursor;
    public BufferedImage men_at_arms;
    public BufferedImage available_movement;

    public Sprites() {
        try {
            this.tile_cursor = ImageIO.read(new File("res/black_tile_cursor.png"));
            this.men_at_arms = ImageIO.read(new File("res/basic_men_at_arms.png"));
            this.available_movement = ImageIO.read(new File("res/pink_lines_movement_sloppy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
