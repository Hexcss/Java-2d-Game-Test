package Utils.Entities.Entity;

import Utils.ScreenSettings.ScreenSettings;
import Utils.Types.Enums.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    protected final Point position;
    protected final int speed;
    protected final ScreenSettings screenSettings;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction = Direction.DOWN;

    public Entity(Point position, int speed, ScreenSettings screenSettings) {
        this.position = position;
        this.speed = speed;
        this.screenSettings = screenSettings;
    }

}
