package Utils.TileManager;

import Core.Entities.Player.Player;
import Core.Tile.Tile;
import Utils.ScreenSettings.ScreenSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sudoplay.joise.module.*;

public class TileManager {
    private final ScreenSettings screenSettings;
    private final Tile[] tiles;
    private final ModuleFractal noiseGenerator;
    private static final Logger logger = Logger.getLogger(Player.class.getName());
    private final double offsetX, offsetY;

    // Create a 2D array to store the initial tile selection
    Tile[][] tileMap;

    public TileManager(ScreenSettings screenSettings) {
        this.screenSettings = screenSettings;
        tiles = new Tile[6];
        getTileImage();
        noiseGenerator = setupNoiseGenerator();

        Random rand = new Random();
        offsetX = rand.nextDouble() * 1000;
        offsetY = rand.nextDouble() * 1000;

        tileMap = new Tile[screenSettings.getScreenWidth() / screenSettings.getTileSize()][screenSettings.getScreenHeight() / screenSettings.getTileSize()];
        generateTileMap();
    }

    public void getTileImage() {
        new Thread(() -> {
            try {
                tiles[0] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/grass.png")));
                tiles[1] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/water.png")));
                tiles[2] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/tree.png")));
                tiles[3] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/earth.png")));
                tiles[4] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/sand.png")));
                tiles[5] = new Tile(ImageIO.read(new File("src/main/resources/Tiles/wall.png")));
            } catch (Exception e) {
                logError("Error loading tile images", e);
            }
        }).start();
    }

    // Method to log an error message and an exception stack trace
    private void logError(String message, Throwable throwable) {
        // Log the error message and stack trace
        logger.log(Level.SEVERE, message, throwable);
    }

    private void generateTileMap() {
        // Adjust these constants as needed
        double scale = 0.01;
        double treeProbability = 0.15; // 15% chance for each grass tile to become a tree

        // First pass to generate the initial map
        for (int i = 0; i < screenSettings.getScreenWidth(); i += screenSettings.getTileSize()) {
            for (int j = 0; j < screenSettings.getScreenHeight(); j += screenSettings.getTileSize()) {
                double noiseValue = noiseGenerator.get((i + offsetX) * scale, (j + offsetY) * scale, 0);

                Tile tile;
                if (noiseValue < -0.8) {
                    tile = tiles[1]; // water
                } else {
                    tile = tiles[0]; // grass
                }

                tileMap[i / screenSettings.getTileSize()][j / screenSettings.getTileSize()] = tile;
            }
        }

        // Second pass to create the beach (sand tiles)
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] == tiles[0]) { // if current tile is grass
                    // Check surrounding tiles for water
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = i + dx;
                            int ny = j + dy;

                            // Check if indices are in range
                            if (nx >= 0 && nx < tileMap.length && ny >= 0 && ny < tileMap[i].length) {
                                // If a neighboring tile is water, set current tile to sand
                                if (tileMap[nx][ny] == tiles[1]) {
                                    tileMap[i][j] = tiles[4];
                                }
                            }
                        }
                    }
                }
            }
        }

        // Third pass to randomly change some grass tiles to tree tiles
        Random random = new Random();
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] == tiles[0]) { // if current tile is grass
                    // If random number is less than the tree probability, change the tile to a tree
                    if (random.nextDouble() < treeProbability) {
                        tileMap[i][j] = tiles[2]; // tree
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Final pass to draw the tiles
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                Tile tile = tileMap[i][j];
                g2.drawImage(tile.getImage(), i * screenSettings.getTileSize(), j * screenSettings.getTileSize(), screenSettings.getTileSize(), screenSettings.getTileSize(), null);
            }
        }
    }


    private ModuleFractal setupNoiseGenerator() {
        ModuleFractal noiseGenerator = new ModuleFractal();
        noiseGenerator.setType(ModuleFractal.FractalType.MULTI);
        noiseGenerator.setNumOctaves(3);
        noiseGenerator.setFrequency(0.1);

        return noiseGenerator;
    }
}
