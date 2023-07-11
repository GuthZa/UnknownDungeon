package models;

import lombok.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
public class LivingEntity {

    private static final int ENTITY_DEFAULT_SPEED = 4;

    private static final int PLAYER_COLLISION_X = 8;
    private static final int PLAYER_COLLISION_Y = 16;
    private static final int PLAYER_COLLISION_WIDTH = 32;
    private static final int PLAYER_COLLISION_HEIGHT = 32;

    private BigDecimal worldX, worldY;
    private BigDecimal screenX, screenY;

    private BigDecimal speed = BigDecimal.valueOf(ENTITY_DEFAULT_SPEED);
    private String direction;
    private final Rectangle collisionArea;
    private Rectangle castCollisionArea;

    private ArrayList<Object> objectList = new ArrayList<>();

    private boolean collision;
    private int keyNumber = 0;

    public LivingEntity(BigDecimal worldX, BigDecimal worldY, String direction) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.collision = false;

        //TODO Change the collision area and cast collision are when adding monsters
        this.collisionArea = new Rectangle(PLAYER_COLLISION_X, PLAYER_COLLISION_Y, PLAYER_COLLISION_WIDTH, PLAYER_COLLISION_HEIGHT);
        this.castCollisionArea = new Rectangle(0, 0, PLAYER_COLLISION_WIDTH, PLAYER_COLLISION_HEIGHT);
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
