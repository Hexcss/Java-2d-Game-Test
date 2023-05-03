import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16x16 pixels
    final int scale = 3; //3x scale
    final int tileSize = originalTileSize * scale; //48x48 pixels
    final int maxScreenTilesWidth = 16; //16 tiles wide
    final int maxScreenTilesHeight = 12; //12 tiles tall
    final int screenWidth = tileSize * maxScreenTilesWidth; //768 pixels wide
    final int screenHeight = tileSize * maxScreenTilesHeight; //576 pixels tall
    final int FPS = 60; //60 Frames per second


    //Game Settings
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //Player Settings
    int[] playerPosition = {100, 100}; //x, y
    int playerSpeed = 4; //4 pixels per frame
    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {

            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            update();

            repaint();
        }
    }

    public void update() {
        //Update Information such as Character Positions
        if (keyHandler.upPressed) {
            if (playerPosition[1] > 0) {
                playerPosition[1] -= playerSpeed;
            }
        }
        if (keyHandler.downPressed) {
            if (playerPosition[1] < screenHeight - tileSize) {
                playerPosition[1] += playerSpeed;
            }
        }
        if (keyHandler.leftPressed) {
            if (playerPosition[0] > 0) {
                playerPosition[0] -= playerSpeed;
            }
        }
        if (keyHandler.rightPressed) {
            if (playerPosition[0] < screenWidth - tileSize) {
                playerPosition[0] += playerSpeed;
            }
        }
    }

    public void paintComponent(Graphics g) {
        //Draw the screen with the updated information
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);

        g2d.fillRect(playerPosition[0], playerPosition[1], tileSize, tileSize);

        g2d.dispose();
    }
}
