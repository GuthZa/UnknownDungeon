package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import models.EntityType;
import models.LivingEntity;
import objects.Object;
import objects.ObjectManager;

import java.math.BigDecimal;
import java.math.RoundingMode;


@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;
    public void checkTile(LivingEntity entity) {

        BigDecimal entityLeftWorldX = entity.getWorldX()
                .add(BigDecimal.valueOf(entity.getCollisionArea().getX()));
        BigDecimal entityRightWorldX = entity.getWorldX()
                .add(BigDecimal.valueOf(entity.getCollisionArea().getWidth()));
        BigDecimal entityTopWorldY = entity.getWorldY()
                .add(BigDecimal.valueOf(entity.getCollisionArea().getY()));
        BigDecimal entityBottomWorldY = entity.getWorldY()
                .add(BigDecimal.valueOf(entity.getCollisionArea().getHeight()));

        BigDecimal entityLeftCol = entityLeftWorldX.divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityRightCol = entityRightWorldX.divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityTopRow = entityTopWorldY.divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityBottomRow = entityBottomWorldY.divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

        BigDecimal tileNum1, tileNum2;


        switch (entity.getDirection()) {
            case "up" -> {
                entityTopRow = entityTopWorldY.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityTopRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityTopRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
            case "down" -> {
                entityBottomRow = entityTopWorldY.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityBottomRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
            case "left" -> {
                entityLeftCol = entityLeftWorldX.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityTopRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
            case "right" -> {
                entityRightCol = entityRightWorldX.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityTopRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
        }
    }

    public void checkObjects(LivingEntity entity, ObjectManager objectManager) {
        for (Object object: objectManager.getObjects()) {
            if (entity.getEntityType().equals(EntityType.Player)) {
                if (checkIntersection(entity, object)) {
                        entity.interactObject(object);
                        objectManager.getObjects().remove(object);
                }
            }
        }
    }

    private boolean checkIntersection(LivingEntity entity, Object object) {
        return entity.getCollisionArea().intersects(object.getCollisionArea());
    }


}
