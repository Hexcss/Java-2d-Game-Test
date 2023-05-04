package Utils.Entities.Entity;

import Utils.ScreenSettings.ScreenSettings;

import java.awt.*;

public abstract class Entity {
    protected final Point position;
    protected final int speed;
    protected final ScreenSettings screenSettings;

    public Entity(Point position, int speed, ScreenSettings screenSettings) {
        this.position = position;
        this.speed = speed;
        this.screenSettings = screenSettings;
    }

}
