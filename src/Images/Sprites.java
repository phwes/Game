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

    public Sprites() {
        try {
            this.tile_cursor = ImageIO.read(new File("res/black_tile_cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
