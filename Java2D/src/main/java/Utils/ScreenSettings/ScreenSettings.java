/**
 * ScreenSettings represents the configuration of the game screen. It is responsible for
 * handling the screen's dimensions, tile size, scale, and frame rate.
 */
package Utils.ScreenSettings;

import java.awt.*;

public class ScreenSettings {
    private final int tileSize;
    private final int screenWidth;
    private final int screenHeight;
    private final int FPS;

    /**
     * Constructor for the ScreenSettings class.
     *
     * @param originalTileSize The original size of a tile in pixels.
     * @param scale The scaling factor for the screen and tile size.
     * @param maxScreenTilesWidth The maximum number of tiles that can be displayed horizontally on the screen.
     * @param maxScreenTilesHeight The maximum number of tiles that can be displayed vertically on the screen.
     * @param FPS The desired frame rate (frames per second) for the game.
     */
    public ScreenSettings(int originalTileSize, int scale, int maxScreenTilesWidth, int maxScreenTilesHeight, int FPS) {
        this.tileSize = originalTileSize * scale;
        this.screenWidth = tileSize * maxScreenTilesWidth;
        this.screenHeight = tileSize * maxScreenTilesHeight;
        this.FPS = FPS;
    }

    /**
     * Returns the Dimension object representing the screen's width and height.
     *
     * @return A Dimension object with the screen's width and height.
     */
    public Dimension getDimension() {
        return new Dimension(screenWidth, screenHeight);
    }

    /**
     * Returns the frame rate (frames per second) for the game.
     *
     * @return The frame rate (FPS) as an integer.
     */
    public int getFPS() {
        return FPS;
    }

    /**
     * Returns the size of a tile in pixels after applying the scale factor.
     *
     * @return The size of a tile in pixels.
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Returns the screen's width in pixels.
     *
     * @return The screen's width in pixels.
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Returns the screen's height in pixels.
     *
     * @return The screen's height in pixels.
     */
    public int getScreenHeight() {
        return screenHeight;
    }
}
