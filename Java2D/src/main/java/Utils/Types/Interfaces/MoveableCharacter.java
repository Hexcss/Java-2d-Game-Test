package Utils.Types.Interfaces;

import Utils.KeyHandler.KeyHandler;

public interface MoveableCharacter {
    void updatePosition(KeyHandler keyHandler);
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
}
