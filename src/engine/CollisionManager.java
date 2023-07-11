package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import models.LivingEntity;
import models.Player;
import objects.Object;
import objects.ObjectManager;

import tile.WorldBuilder;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;
    public void checkTile(LivingEntity entity, WorldBuilder worldBuilder) {

        BigDecimal entityLeftWorldX = BigDecimal.valueOf(entity.getCollisionArea().getX());
        BigDecimal entityRightWorldX = entity.getWorldX().add(BigDecimal.valueOf(entity.getCollisionArea().getWidth()));
        BigDecimal entityTopWorldY = BigDecimal.valueOf(entity.getCollisionArea().getY());
        BigDecimal entityBottomWorldY = entity.getWorldY().add(BigDecimal.valueOf(entity.getCollisionArea().getY()));

        switch (entity.getDirection()) {
            case "up" -> {
                playerCastAreaXPosition = entity.getScreenX().add(playerCollisionAreaX);
                playerCastAreaYPosition = entity.getScreenY().add(playerCollisionAreaY).add(entity.getSpeed());

                if(entity.getCastCollisionArea().intersects()) {

                }
            }
            case "down" -> {
                g2.fillRect(
                        player.getScreenX().add(BigDecimal.valueOf(player.getCollisionArea().getX())).intValue(),
                        player.getScreenY().add(BigDecimal.valueOf(player.getCollisionArea().getY())).add(player.getSpeed()).intValue(),
                        (int) player.getCollisionArea().getWidth(),
                        (int) player.getCollisionArea().getHeight());
            }
            case "left" -> {
                g2.fillRect(
                        player.getScreenX().subtract(BigDecimal.valueOf(player.getCollisionArea().getX())).add(player.getSpeed()).intValue(),
                        player.getScreenY().add(BigDecimal.valueOf(player.getCollisionArea().getY())).intValue(),
                        (int) player.getCollisionArea().getWidth(),
                        (int) player.getCollisionArea().getHeight());
            }
            case "right" -> {
                g2.fillRect(
                        player.getScreenX().add(BigDecimal.valueOf(player.getCollisionArea().getX())).add(player.getSpeed()).intValue(),
                        player.getScreenY().add(BigDecimal.valueOf(player.getCollisionArea().getY())).intValue(),
                        (int) player.getCollisionArea().getWidth(),
                        (int) player.getCollisionArea().getHeight());
            }
        }

//        BigDecimal PlayerPredictionXPosition = BigDecimal.valueOf(entity.getCollisionArea().getX());
//        BigDecimal PlayerPredictionYPosition = BigDecimal.valueOf(entity.getCollisionArea().getY());
//        switch (entity.getDirection()) {
//            case "up" -> {
//                PlayerPredictionYPosition = entity.getSpeed().
//                        subtract(BigDecimal.valueOf(entity.getCollisionArea().getY()));
//            }
//            case "down" -> {
//                PlayerPredictionYPosition = entity.getSpeed().
//                        add(BigDecimal.valueOf(entity.getCollisionArea().getY()));
//            }
//            case "left" -> {
//                PlayerPredictionXPosition = entity.getSpeed().subtract(BigDecimal.valueOf(entity.getCollisionArea().getX())).divide(gamePanel.getTileSize(), RoundingMode.DOWN);
//            }
//            case "right" -> {
//                PlayerPredictionXPosition = entity.getSpeed().add(BigDecimal.valueOf(entity.getCollisionArea().getX())).divide(gamePanel.getTileSize(), RoundingMode.DOWN);
//            }
//        }
//
//        if(
//        worldBuilder.getTileTypeAtPos(PlayerPredictionXPosition, PlayerPredictionYPosition) != 0 ||
//        worldBuilder.getTileTypeAtPos(PlayerPredictionXPosition, PlayerPredictionYPosition) != 3 ||
//        worldBuilder.getTileTypeAtPos(PlayerPredictionXPosition, PlayerPredictionYPosition) != 5
//        ) {
//            if(entity.getCollisionArea().intersects(worldBuilder.getTileAtPos(
//                    PlayerPredictionXPosition, PlayerPredictionYPosition
//            ).getCollisionArea())) {
//                System.out.println("collision");
//            }
//        }

//        g2.fillRect(
//                (int) tile[tileNum].getCollisionArea().getX() + screenX.intValue(),
//                (int) tile[tileNum].getCollisionArea().getY() + screenY.intValue(),
//                (int) tile[tileNum].getCollisionArea().getWidth(),
//                (int) tile[tileNum].getCollisionArea().getHeight());





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
                    //entity.setCollision(true);
                }
            }
            case "right" -> {
                entityRightCol = RightWorldX.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityTopRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityBottomRow)) {
                    System.out.println("right collision");
                    //entity.setCollision(true);
                }
            }
            case "up" -> {
                entityTopRow = TopWorldY.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityTopRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityTopRow)) {
                    System.out.println("up collision");
                    //entity.setCollision(true);
                }
            }
            case "down" -> {
                entityBottomRow = TopWorldY.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.DOWN);

                if(gamePanel.getWorldBuilder().checkCollision(entityLeftCol, entityBottomRow) || gamePanel.getWorldBuilder().checkCollision(entityRightCol, entityBottomRow)) {
                    System.out.println("bottom collision");
                    //entity.setCollision(true);
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
