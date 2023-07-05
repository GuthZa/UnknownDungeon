package models;

import lombok.*;

import java.awt.*;
import java.math.BigDecimal;

@Getter
@Setter
public class LivingEntity {

    private static final int ENTITY_DEFAULT_SPEED = 4;

    private BigDecimal worldX, worldY;
    private BigDecimal speed = BigDecimal.valueOf(ENTITY_DEFAULT_SPEED);
    private String direction;
    private final Rectangle collisionArea;

    private boolean collision;

    public LivingEntity(BigDecimal worldX, BigDecimal worldY, Rectangle collisionArea, String direction) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.collisionArea = collisionArea;
        this.direction = direction;
        this.collision = false;
    }

    //Entity movement
    public void moveUp() {
        worldY = worldY.subtract(speed);
    }
    public void moveDown() {
        worldY = worldY.add(speed);
    }
    public void moveRight() {
        worldX = worldX.subtract(speed);
    }
    public void moveLeft() {
        worldX = worldX.add(speed);
    }
}
