package Core.Tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collidable = false;

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}
