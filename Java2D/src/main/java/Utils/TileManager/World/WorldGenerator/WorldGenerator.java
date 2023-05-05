package Utils.TileManager.World.WorldGenerator;

import Core.Tile.Tile;
import Utils.ScreenSettings.ScreenSettings;
import Utils.Types.Enums.TileType;
import Utils.Types.Interfaces.INoiseGenerator;

import java.util.Map;
import java.util.Random;

/**
 * The WorldGenerator class is responsible for generating the tile map of the world.
 *
 * The world generation includes creating the initial map, adding beaches around water bodies, and placing trees on grass tiles.
 */
public class WorldGenerator {
    private final ScreenSettings screenSettings;  // Screen settings object used to define the dimensions of the tile map
    private final Map<TileType, Tile> tiles;  // Map to hold the different types of tiles that can be placed on the tile map
    private final INoiseGenerator noiseGenerator;  // Noise generator used to create noise for the initial map generation
    private final double offsetX, offsetY;  // Randomly generated offsets used to create noise in the initial map generation
    private final Tile[][] tileMap;  // Two-dimensional array representing the tile map
    private final Random random;  // Random object used in the generation of trees

    /**
     * Constructor for the WorldGenerator class.
     *
     * @param screenSettings   the screen settings used to define the dimensions of the tile map
     * @param noiseGenerator   the noise generator used to create noise for the initial map generation
     * @param tiles            the map of tile types and their corresponding Tile objects
     */
    public WorldGenerator(ScreenSettings screenSettings, INoiseGenerator noiseGenerator, Map<TileType, Tile> tiles) {
        this.screenSettings = screenSettings;
        this.noiseGenerator = noiseGenerator;
        this.tiles = tiles;
        this.random = new Random();

        offsetX = random.nextDouble() * 1000;
        offsetY = random.nextDouble() * 1000;

        tileMap = new Tile[screenSettings.getScreenWidth() / screenSettings.getTileSize()][screenSettings.getScreenHeight() / screenSettings.getTileSize()];
        generateTileMap();
    }

    /**
     * Generates the tile map by creating the initial map, adding beaches, and placing trees.
     */
    private void generateTileMap() {
        generateInitialMap();
        generateBeaches();
        generateTrees();
    }

    /**
     * Generates the initial map using noise.
     *
     * The generated map consists of water and grass tiles.
     */
    private void generateInitialMap() {
        for (int i = 0; i < screenSettings.getScreenWidth(); i += screenSettings.getTileSize()) {
            for (int j = 0; j < screenSettings.getScreenHeight(); j += screenSettings.getTileSize()) {
                double noiseValue = noiseGenerator.getNoise((i + offsetX) * 0.01, (j + offsetY) * 0.01, 0);

                Tile tile = noiseValue < -0.8 ? tiles.get(TileType.WATER) : tiles.get(TileType.GRASS);
                tileMap[i / screenSettings.getTileSize()][j / screenSettings.getTileSize()] = tile;
            }
        }
    }

    /**
     * Adds beaches to the tile map.
     *
     * A beach is added around a water tile if there is a grass tile in its immediate neighborhood.
     */
    private void generateBeaches() {
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] == tiles.get(TileType.WATER)) {
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = i + dx;
                            int ny = j + dy;

                            if (nx >= 0 && nx < tileMap.length && ny >= 0 && ny < tileMap[i].length) {
                                if (tileMap[nx][ny] == tiles.get(TileType.GRASS)) {
                                    tileMap[nx][ny] = tiles.get(TileType.SAND);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds trees to the tile map.
     *
     * A tree is added to a grass tile with a probability of 15%.
     */
    private void generateTrees() {
        final double TREE_PROBABILITY = 0.15;
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] == tiles.get(TileType.GRASS)) {
                    if (random.nextDouble() < TREE_PROBABILITY) {
                        tileMap[i][j] = tiles.get(TileType.TREE);
                    }
                }
            }
        }
    }

    /**
     * Returns the generated tile map.
     *
     * @return the tile map
     */
    public Tile[][] getTileMap() {
        return tileMap;
    }
}

