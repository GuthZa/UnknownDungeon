package models;

import lombok.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class LivingEntity {

    private static final int ENTITY_DEFAULT_SPEED = 4;

    private BigDecimal worldX, worldY;
    private BigDecimal speed = BigDecimal.valueOf(ENTITY_DEFAULT_SPEED);
    private String direction;
    private final Rectangle collisionArea;

    private ArrayList<Object> objectList = new ArrayList<>();

    private boolean collision;
    private int keyNumber = 0;

    public LivingEntity(BigDecimal worldX, BigDecimal worldY, Rectangle collisionArea, String direction) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.collisionArea = collisionArea;
        this.direction = direction;
        this.collision = false;
    }

    //Object manager
    public void addKey(int number){
        this.keyNumber += number;
    }

    public void removeKey(int number) {
        this.keyNumber -= number;
    }

    public void addItem(Object object) {
        objectList.add(object);
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
