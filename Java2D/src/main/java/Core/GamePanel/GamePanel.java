/**
 * GamePanel represents the main game area and handles the game loop, rendering, and updates.
 * It extends JPanel and implements Runnable for running the game loop in a separate thread.
 */
package Core.GamePanel;

import Utils.KeyHandler.KeyHandler;
import Core.Entities.Player.Player;
import Utils.ScreenSettings.ScreenSettings;
import Utils.TileManager.JoiseNoiseGenerator.JoiseNoiseGenerator;
import Utils.TileManager.TileManager;
import Utils.Types.Enums.TileType;
import Utils.Types.Interfaces.INoiseGenerator;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final ScreenSettings screenSettings;
    private final KeyHandler keyHandler;
    private final Player player;
    private final TileManager tileManager;

    /**
     * Constructor for the GamePanel class. Initializes the screen settings, key handler, and player.
     */
    public GamePanel() {
        screenSettings = new ScreenSettings(16, 3, 16, 12, 60);
        keyHandler = new KeyHandler();
        INoiseGenerator noiseGenerator = new JoiseNoiseGenerator();
        tileManager = new TileManager(screenSettings, noiseGenerator);
        Point playerStartPos = tileManager.findStartPosition(TileType.GRASS);

        player = new Player(playerStartPos, 4, screenSettings);

        setupPanel();
    }

    /**
     * Sets up the panel with the necessary configurations, including dimensions, background color,
     * double buffering, and key listener.
     */
    private void setupPanel() {
        setPreferredSize(screenSettings.getDimension());
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    /**
     * Starts the game loop by creating a new Thread and running it.
     */
    public void startGameThread() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game loop, which runs continuously while the game is active. It updates the game state,
     * repaints the screen, and controls the frame rate.
     */
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                Thread.sleep(1000 / screenSettings.getFPS());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();

            repaint();
        }
    }

    /**
     * Updates the game state by calling the update method of the Player class.
     */
    private void update() {
        player.updatePosition(keyHandler);
    }

    /**
     * Overrides the paintComponent method of JPanel to draw the game objects on the screen.
     *
     * @param g A Graphics object for rendering the game objects.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
