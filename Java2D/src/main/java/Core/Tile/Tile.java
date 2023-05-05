package Core.Tile;

import Utils.Types.Enums.TileType;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collidable = false;
    private TileType tileType;

    public Tile(BufferedImage image, TileType tileType) {
        this.image = image;
        this.tileType = tileType;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean isCollidable() {
        return this.collidable;
    }

    public TileType getTileType() {
        return this.tileType;
    }
}
