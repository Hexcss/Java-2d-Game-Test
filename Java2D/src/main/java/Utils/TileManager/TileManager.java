package Utils.TileManager;

import Core.Tile.Tile;
import Utils.ScreenSettings.ScreenSettings;
import Utils.TileManager.TileImageLoader.TileImageLoader;
import Utils.TileManager.World.WorldGenerator.WorldGenerator;
import Utils.TileManager.World.WorldRenderer.WorldRenderer;
import Utils.Types.Enums.TileType;
import Utils.Types.Interfaces.INoiseGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The TileManager class manages the tile set, world generation, and rendering of the world.
 *
 * It loads tile images, generates a world based on the loaded tiles, and then renders this world.
 */
public class TileManager {
    private final Map<TileType, Tile> tiles;  // Map that stores the loaded tiles
    private final WorldGenerator worldGenerator;  // The WorldGenerator used to generate the tile map
    private final WorldRenderer worldRenderer;  // The WorldRenderer used to render the tile map

    private final ScreenSettings screenSettings;  // The screen settings used in world generation and rendering
    private static final Logger logger = Logger.getLogger(TileManager.class.getName());  // Logger for error handling

    /**
     * Constructor for the TileManager class.
     *
     * @param screenSettings   the screen settings used in world generation and rendering
     * @param noiseGenerator   the noise generator used in world generation
     */
    public TileManager(ScreenSettings screenSettings, INoiseGenerator noiseGenerator) {
        this.screenSettings = screenSettings;
        Map<TileType, Tile> tiles1;

        TileImageLoader tileImageLoader = new TileImageLoader();
        try {
            tiles1 = tileImageLoader.loadTileImages();
        } catch (Exception e) {
            logError("Error loading tile images", e);
            tiles1 = null;
        }

        this.tiles = tiles1;

        // Create the WorldGenerator and WorldRenderer
        this.worldGenerator = new WorldGenerator(screenSettings, noiseGenerator, tiles);
        this.worldRenderer = new WorldRenderer(screenSettings, worldGenerator.getTileMap());
    }

    public Point findStartPosition(TileType desiredTileType) {
        ArrayList<Point> possibleStartPositions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < worldGenerator.getTileMap().length; i++) {
            for (int j = 0; j < worldGenerator.getTileMap()[i].length; j++) {
                if (worldGenerator.getTileMap()[i][j].getTileType() == desiredTileType) {
                    possibleStartPositions.add(new Point(i * screenSettings.getTileSize(), j * screenSettings.getTileSize()));
                }
            }
        }

        if (possibleStartPositions.isEmpty()) {
            return null;  // if no desired tile type is found, return null
        } else {
            return possibleStartPositions.get(random.nextInt(possibleStartPositions.size()));
        }
    }

    /**
     * Logs the occurrence of an error.
     *
     * @param message   the error message to be logged
     * @param throwable the throwable associated with the error
     */
    private void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    /**
     * Renders the generated world on the provided Graphics2D object.
     *
     * @param g2   the Graphics2D object on which the world is rendered
     */
    public void draw(Graphics2D g2) {
        worldRenderer.draw(g2);
    }
}
