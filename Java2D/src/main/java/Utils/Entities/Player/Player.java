/**
 * Player represents the main character in the game. It is responsible for handling the player's
 * position, speed, and rendering on the screen.
 */
package Utils.Entities.Player;

import Utils.Entities.Entity.Entity;
import Utils.KeyHandler.KeyHandler;
import Utils.ScreenSettings.ScreenSettings;
import Utils.Types.Enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    /**
     * Constructor for the Player class.
     *
     * @param position The initial position of the player on the screen as a Point object (x, y).
     * @param speed The speed at which the player moves in pixels per frame.
     * @param screenSettings A ScreenSettings object that contains information about the screen's dimensions.
     */
    public Player(Point position, int speed, ScreenSettings screenSettings) {
        super(position, speed, screenSettings);
        getPlayerImages();
    }

    /**
     * Updates the player's position based on the current key states and screen settings.
     *
     * @param keyHandler A KeyHandler object that contains the current key states.
     */
    public void updatePosition(KeyHandler keyHandler) {
        if (keyHandler.isUpPressed()) {
            direction = Direction.UP;
            position.y = Math.max(position.y - speed, 0);
        }
        if (keyHandler.isDownPressed()) {
            direction = Direction.DOWN;
            position.y = Math.min(position.y + speed, screenSettings.getScreenHeight() - screenSettings.getTileSize());
        }
        if (keyHandler.isLeftPressed()) {
            direction = Direction.LEFT;
            position.x = Math.max(position.x - speed, 0);
        }
        if (keyHandler.isRightPressed()) {
            direction = Direction.RIGHT;
            position.x = Math.min(position.x + speed, screenSettings.getScreenWidth() - screenSettings.getTileSize());
        }
    }

    /**
     * Renders the player on the screen using the provided Graphics2D object.
     *
     * @param g2d A Graphics2D object used for rendering the player on the screen.
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = switch (direction) {
            case UP -> (position.y % 30 < 15) ? up1 : up2;
            case DOWN -> (position.y % 30 < 15) ? down1 : down2;
            case LEFT -> (position.x % 30 < 15) ? left1 : left2;
            case RIGHT -> (position.x % 30 < 15) ? right1 : right2;
        };
        g2d.drawImage(image, position.x, position.y, screenSettings.getTileSize(), screenSettings.getTileSize(), null);
    }

    public void getPlayerImages() {
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
            e.printStackTrace();
        }
    }
}
