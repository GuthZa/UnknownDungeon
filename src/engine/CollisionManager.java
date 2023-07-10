package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import models.LivingEntity;
import objects.Object;
import objects.ObjectManager;
import tile.Tile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Key;
import java.util.ArrayList;

@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;

    public void checkTile(LivingEntity entity) {

        BigDecimal LeftWorldX = entity.getWorldX().add(BigDecimal.valueOf(entity.getCollisionArea().getX()));
        BigDecimal RightWorldX = entity.getWorldX().add(BigDecimal.valueOf(entity.getCollisionArea().getX()))
                .add(BigDecimal.valueOf(entity.getCollisionArea().getWidth()));
        BigDecimal TopWorldY = entity.getWorldY().add(BigDecimal.valueOf(entity.getCollisionArea().getY()));
        BigDecimal BottomWorldY = entity.getWorldY().add(BigDecimal.valueOf(entity.getCollisionArea().getY())).add(BigDecimal.valueOf(entity.getCollisionArea().getHeight()));

        BigDecimal entityLeftCol = LeftWorldX.divide(gamePanel.getTileSize(), RoundingMode.DOWN);
        BigDecimal entityRightCol = RightWorldX.divide(gamePanel.getTileSize(), RoundingMode.DOWN);
        BigDecimal entityTopRow = TopWorldY.divide(gamePanel.getTileSize(), RoundingMode.DOWN);
        BigDecimal entityBottomRow = BottomWorldY.divide(gamePanel.getTileSize(), RoundingMode.DOWN);

        //TODO Add collision area to tiles ?
        switch (entity.getDirection()) {
            case "left" -> {
                entityLeftCol = LeftWorldX.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);


                if(gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityTopRow) || gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityBottomRow)) {
                    System.out.println("left collision");
                    entity.setCollision(true);
                }
            }
            case "right" -> {
                entityRightCol = RightWorldX.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityTopRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityBottomRow)) {
                    System.out.println("right collision");
                    entity.setCollision(true);
                }
            }
            case "up" -> {
                entityTopRow = TopWorldY.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityTopRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityTopRow)) {
                    System.out.println("up collision");
                    entity.setCollision(true);
                }
            }
            case "down" -> {
                entityBottomRow = TopWorldY.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityBottomRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityBottomRow)) {
                    System.out.println("bottom collision");
                    entity.setCollision(true);
                }
            }
        }
    }


    public void checkObjects(LivingEntity entity, ObjectManager objectManager, boolean isPlayer) {

        for (Object object: objectManager.getObjects()) {
            if (isPlayer) {
                if (entity.getCollisionArea().intersects(object.getCollisionArea())) {
                    switch (object.checkCategory()) {
                        case "Key" -> {
                            if (!object.isGrabbed()) {
                                object.setGrabbed(true);
                                entity.addKey(1);
                                System.out.println(entity.getKeyNumber());
                            }
                        }
                        case "Boots" -> {
                            if (!object.isGrabbed()) {
                                object.setGrabbed(true);
                                entity.addKey(1);
                                entity.setSpeed(entity.getSpeed().add(BigDecimal.valueOf(2))); //Increases player speed
                                entity.addItem(object);
                                System.out.println(entity.getKeyNumber());
                            }
                        }
                        case "Door" -> {
                            if(entity.getKeyNumber() > 0) {
                                object.setGrabbed(true);
                                object.setCollision(false);
                                entity.removeKey(1);
                            }
                        }
                    }
                }
            }
        }


    }
}
