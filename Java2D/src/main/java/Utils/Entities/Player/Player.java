/**
 * Player represents the main character in the game. It is responsible for handling the player's
 * position, speed, and rendering on the screen.
 */
package Utils.Entities.Player;

import Utils.Entities.Entity.Entity;
import Utils.KeyHandler.KeyHandler;
import Utils.ScreenSettings.ScreenSettings;

import java.awt.*;

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
    }

    /**
     * Updates the player's position based on the current key states and screen settings.
     *
     * @param keyHandler A KeyHandler object that contains the current key states.
     */
    public void updatePosition(KeyHandler keyHandler) {
        if (keyHandler.isUpPressed()) {
            position.y = Math.max(position.y - speed, 0);
        }
        if (keyHandler.isDownPressed()) {
            position.y = Math.min(position.y + speed, screenSettings.getScreenHeight() - screenSettings.getTileSize());
        }
        if (keyHandler.isLeftPressed()) {
            position.x = Math.max(position.x - speed, 0);
        }
        if (keyHandler.isRightPressed()) {
            position.x = Math.min(position.x + speed, screenSettings.getScreenWidth() - screenSettings.getTileSize());
        }
    }

    /**
     * Renders the player on the screen using the provided Graphics2D object.
     *
     * @param g2d A Graphics2D object used for rendering the player on the screen.
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(position.x, position.y, screenSettings.getTileSize(), screenSettings.getTileSize());
    }
}
