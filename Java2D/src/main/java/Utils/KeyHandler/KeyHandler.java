/**
 * KeyHandler is a utility class that implements the KeyListener interface to handle
 * keyboard input for controlling the game characters.
 */
package Utils.KeyHandler;

import Utils.Types.Enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumSet;

public class KeyHandler implements KeyListener {
    private EnumSet<Direction> activeDirections = EnumSet.noneOf(Direction.class);

    /**
     * Returns whether the UP key is pressed.
     *
     * @return true if UP key is pressed, false otherwise.
     */
    public boolean isUpPressed() {
        return activeDirections.contains(Direction.UP);
    }

    /**
     * Returns whether the DOWN key is pressed.
     *
     * @return true if DOWN key is pressed, false otherwise.
     */
    public boolean isDownPressed() {
        return activeDirections.contains(Direction.DOWN);
    }

    /**
     * Returns whether the LEFT key is pressed.
     *
     * @return true if LEFT key is pressed, false otherwise.
     */
    public boolean isLeftPressed() {
        return activeDirections.contains(Direction.LEFT);
    }

    /**
     * Returns whether the RIGHT key is pressed.
     *
     * @return true if RIGHT key is pressed, false otherwise.
     */
    public boolean isRightPressed() {
        return activeDirections.contains(Direction.RIGHT);
    }

    /**
     * An empty implementation of keyTyped, as it is not needed for this class.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Handles keyPressed events by adding the corresponding direction to the activeDirections set.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W -> activeDirections.add(Direction.UP);
            case KeyEvent.VK_A -> activeDirections.add(Direction.LEFT);
            case KeyEvent.VK_S -> activeDirections.add(Direction.DOWN);
            case KeyEvent.VK_D -> activeDirections.add(Direction.RIGHT);
        }
    }

    /**
     * Handles keyReleased events by removing the corresponding direction from the activeDirections set.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W -> activeDirections.remove(Direction.UP);
            case KeyEvent.VK_A -> activeDirections.remove(Direction.LEFT);
            case KeyEvent.VK_S -> activeDirections.remove(Direction.DOWN);
            case KeyEvent.VK_D -> activeDirections.remove(Direction.RIGHT);
        }
    }
}
