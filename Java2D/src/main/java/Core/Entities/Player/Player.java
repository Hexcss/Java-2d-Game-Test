/**
 * Player represents the main character in the game. It is responsible for handling the player's
 * position, speed, and rendering on the screen.
 */
package Core.Entities.Player;

import Core.Entities.Entity.Entity;
import Utils.KeyHandler.KeyHandler;
import Utils.ScreenSettings.ScreenSettings;
import Utils.Types.Enums.Direction;
import Utils.Types.Interfaces.MoveableCharacter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Entity implements MoveableCharacter {
    // Logger for this class
    private static final Logger logger = Logger.getLogger(Player.class.getName());

    /**
     * Constructor for the Player class.
     *
     * @param position The initial position of the player on the screen as a Point object (x, y).
     * @param speed The speed at which the player moves in pixels per frame.
     * @param screenSettings A ScreenSettings object that contains information about the screen's dimensions.
     */
    public Player(Point position, int speed, ScreenSettings screenSettings) {
        super(position, speed, screenSettings);
        loadPlayerImages();
    }

    // Method to load the player's images
    private void loadPlayerImages() {
        new Thread(() -> {
            try {
                up1 = ImageIO.read(new File("src/main/resources/Player/up1.png"));
                up2 = ImageIO.read(new File("src/main/resources/Player/up2.png"));
                down1 = ImageIO.read(new File("src/main/resources/Player/down1.png"));
                down2 = ImageIO.read(new File("src/main/resources/Player/down2.png"));
                left1 = ImageIO.read(new File("src/main/resources/Player/left1.png"));
                left2 = ImageIO.read(new File("src/main/resources/Player/left2.png"));
                right1 = ImageIO.read(new File("src/main/resources/Player/right1.png"));
                right2 = ImageIO.read(new File("src/main/resources/Player/right2.png"));
            } catch (IOException e) {
                logError("Error loading player images", e);
            }
        }).start();
    }

    // Method to log an error message and an exception stack trace
    private void logError(String message, Throwable throwable) {
        // Log the error message and stack trace
        logger.log(Level.SEVERE, message, throwable);
    }

    // Helper method to update position and direction
    private void updatePositionAndDirection(Direction direction, boolean isPressed, Runnable movement) {
        if (isPressed) {
            movement.run();
            this.direction = direction;
        }
    }

    /**
     * Updates the player's position based on the current key states and screen settings.
     *
     * @param keyHandler A KeyHandler object that contains the current key states.
     */
    @Override
    public void updatePosition(KeyHandler keyHandler) {
        if (keyHandler != null) {
            updatePositionAndDirection(Direction.UP, keyHandler.isUpPressed(), this::moveUp);
            updatePositionAndDirection(Direction.DOWN, keyHandler.isDownPressed(), this::moveDown);
            updatePositionAndDirection(Direction.LEFT, keyHandler.isLeftPressed(), this::moveLeft);
            updatePositionAndDirection(Direction.RIGHT, keyHandler.isRightPressed(), this::moveRight);
        }
    }

    /**
     * Moves the player up, respecting the screen's vertical boundaries.
     */
    @Override
    public void moveUp() {
        position.y = Math.max(position.y - speed, 0);
    }

    /**
     * Moves the player down, respecting the screen's vertical boundaries.
     */
    @Override
    public void moveDown() {
        position.y = Math.min(position.y + speed, screenSettings.getScreenHeight() - screenSettings.getTileSize());
    }

    /**
     * Moves the player left, respecting the screen's horizontal boundaries.
     */
    @Override
    public void moveLeft() {
        position.x = Math.max(position.x - speed, 0);
    }

    /**
     * Moves the player right, respecting the screen's horizontal boundaries.
     */
    @Override
    public void moveRight() {
        position.x = Math.min(position.x + speed, screenSettings.getScreenWidth() - screenSettings.getTileSize());
    }

    /**
     * Renders the player on the screen using the provided Graphics2D object.
     *
     * @param g2d A Graphics2D object used for rendering the player on the screen.
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = switch (direction) {
            case UP -> (position.y % ANIMATION_CYCLE_LENGTH < ANIMATION_FRAME_THRESHOLD) ? up1 : up2;
            case DOWN -> (position.y % ANIMATION_CYCLE_LENGTH < ANIMATION_FRAME_THRESHOLD) ? down1 : down2;
            case LEFT -> (position.x % ANIMATION_CYCLE_LENGTH < ANIMATION_FRAME_THRESHOLD) ? left1 : left2;
            case RIGHT -> (position.x % ANIMATION_CYCLE_LENGTH < ANIMATION_FRAME_THRESHOLD) ? right1 : right2;
        };
        g2d.drawImage(image, position.x, position.y, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
    }
}
