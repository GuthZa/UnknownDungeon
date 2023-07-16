package models;

import lombok.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import main.GamePanel;
import objects.Object;

@Getter
@Setter
public class LivingEntity {

    private static final int ENTITY_DEFAULT_SPEED = 4;

    private static final int PLAYER_COLLISION_X = 8;
    private static final int PLAYER_COLLISION_Y = 16;
    private static final int PLAYER_COLLISION_WIDTH = 32;
    private static final int PLAYER_COLLISION_HEIGHT = 32;

    private static final int BOOTS_SPEED_INCREASE = 2;

    private BigDecimal worldX, worldY;
    private BigDecimal screenX, screenY;

    private BigDecimal speed = BigDecimal.valueOf(ENTITY_DEFAULT_SPEED);
    private String direction;
    private final Rectangle collisionArea;
    private final Rectangle castCollisionArea;

    private ArrayList<Object> objectList = new ArrayList<>();

    private boolean collision;
    private int keyNumber = 0;

    private EntityType entityType;

    public LivingEntity(BigDecimal worldX, BigDecimal worldY, String direction, EntityType type) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.collision = false;
        this.entityType = type;

        //TODO Change the collision area and cast collision are when adding monsters

        //The collision area is based of Screen Position
        this.collisionArea = new Rectangle(
                PLAYER_COLLISION_X, PLAYER_COLLISION_Y,
                PLAYER_COLLISION_WIDTH, PLAYER_COLLISION_HEIGHT
        );
        this.castCollisionArea = new Rectangle(0, 0,
                PLAYER_COLLISION_WIDTH, PLAYER_COLLISION_HEIGHT);
    }

    /*

   TODO RE-DO MOVEMENT BASED ON POKEMON RED//BLUE

   move a block with each click
   speed increases the time that passes with each movement

   need to remake time calculation function?

     */

    //Object manager
    private void addKey(){
        this.keyNumber++;
    }

    private void removeKey() {
        this.keyNumber--;
    }

    public void interactObject(Object object) {
        switch (object.getItemCategory()) {
            case Boots -> {
                increaseSpeed();
                objectList.add(object);
            }
            case Key -> {
                addKey();
            }
            case Door -> {
                removeKey();
            }
        }
    }

    //Collision management
    public BigDecimal getLeftWorldX() {
        return this.getWorldX()
                .add(BigDecimal.valueOf(
                        this.getCollisionArea().getX()));
    }

    public BigDecimal getRightWorldX() {
        return this.getWorldX()
                .add(BigDecimal.valueOf(
                        this.getCollisionArea().getWidth()));
    }

    public BigDecimal getTopWorldY() {
        return this.getWorldY()
                .add(BigDecimal.valueOf(
                        this.getCollisionArea().getY()));
    }
    public BigDecimal getBottomWorldY() {
        return this.getWorldY()
                .add(BigDecimal.valueOf(
                        this.getCollisionArea().getHeight()));
    }

    //Entity movement
    public void move(String direction) {
        switch (direction) {
            case "up" -> worldY = worldY.subtract(speed);
            case "down" -> worldY = worldY.add(speed);
            case "left" -> worldX = worldX.subtract(speed);
            case "right" -> worldX = worldX.add(speed);
        }
    }
    private void increaseSpeed() {
         speed = speed.add(BigDecimal.valueOf(LivingEntity.BOOTS_SPEED_INCREASE));
    }
}
