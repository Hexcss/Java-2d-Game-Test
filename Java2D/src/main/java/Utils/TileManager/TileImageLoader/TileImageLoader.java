package Utils.TileManager.TileImageLoader;

import Core.Tile.Tile;
import Utils.Types.Enums.TileType;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.EnumMap;
import java.util.Map;

/**
 * This class is responsible for loading tile images from the file system.
 * It maps each TileType enum value to the path of the corresponding image file.
 */
public class TileImageLoader {
    private static final Map<TileType, String> TILE_IMAGE_PATHS;

    // Initialize the TILE_IMAGE_PATHS map with the paths to the tile images.
    static {
        TILE_IMAGE_PATHS = new EnumMap<>(TileType.class);
        TILE_IMAGE_PATHS.put(TileType.GRASS, "src/main/resources/Tiles/grass.png");
        TILE_IMAGE_PATHS.put(TileType.WATER, "src/main/resources/Tiles/water.png");
        TILE_IMAGE_PATHS.put(TileType.TREE, "src/main/resources/Tiles/tree.png");
        TILE_IMAGE_PATHS.put(TileType.EARTH, "src/main/resources/Tiles/earth.png");
        TILE_IMAGE_PATHS.put(TileType.SAND, "src/main/resources/Tiles/sand.png");
        TILE_IMAGE_PATHS.put(TileType.WALL, "src/main/resources/Tiles/wall.png");
    }

    /**
     * Loads the tile images from the file system.
     *
     * @return A map where the keys are TileType values and the values are Tile objects
     *         created from the corresponding images.
     *
     * @throws Exception If there is an error reading an image file.
     */
    public Map<TileType, Tile> loadTileImages() throws Exception {
        Map<TileType, Tile> tiles = new EnumMap<>(TileType.class);

        for (TileType tileType : TILE_IMAGE_PATHS.keySet()) {
            tiles.put(tileType, new Tile(ImageIO.read(new File(TILE_IMAGE_PATHS.get(tileType))), tileType));
        }

        return tiles;
    }
}
