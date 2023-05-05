package Utils.TileManager.World.WorldRenderer;

import Core.Tile.Tile;
import Utils.ScreenSettings.ScreenSettings;

import java.awt.*;

/**
 * The WorldRenderer class is responsible for rendering the tile map of the world.
 *
 * It draws each tile of the map according to the screen settings and the provided Graphics2D object.
 */
public class WorldRenderer {
    private final ScreenSettings screenSettings;  // Screen settings object used to define the dimensions of the tile map
    private final Tile[][] tileMap;  // Two-dimensional array representing the tile map

    /**
     * Constructor for the WorldRenderer class.
     *
     * @param screenSettings   the screen settings used to define the dimensions of the tile map
     * @param tileMap          the tile map to be rendered
     */
    public WorldRenderer(ScreenSettings screenSettings, Tile[][] tileMap) {
        this.screenSettings = screenSettings;
        this.tileMap = tileMap;
    }

    /**
     * Draws the tile map on the provided Graphics2D object.
     *
     * Each tile is drawn at its appropriate location and with its designated size.
     *
     * @param g2   the Graphics2D object on which the tile map is drawn
     */
    public void draw(Graphics2D g2) {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tileMap[i][j];
                g2.drawImage(tile.getImage(), i * screenSettings.getTileSize(), j * screenSettings.getTileSize(), screenSettings.getTileSize(), screenSettings.getTileSize(), null);
            }
        }
    }
}

