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

        BigDecimal entityLeftCol = entity.getLeftWorldX()
                .divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityRightCol = entity.getRightWorldX()
                .divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityTopRow = entity.getTopWorldY()
                .divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
        BigDecimal entityBottomRow = entity.getBottomWorldY()
                .divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

        BigDecimal tileNum1, tileNum2;

        if(entity.getDirection().equals("up")) {
            entityTopRow = entity.getTopWorldY().subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

            tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityTopRow));

            tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityTopRow));

            entity.setCollision(
                    gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                            .isCollision() ||
                    gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                            .isCollision());
        }
        if(entity.getDirection().equals("down")) {
                entityBottomRow = entity.getBottomWorldY().add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityBottomRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
        if(entity.getDirection().equals("left")) {
                entityLeftCol = entity.getLeftWorldX().add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);
                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityTopRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityLeftCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
            }
        if(entity.getDirection().equals("right")) {
                entityRightCol = entity.getRightWorldX().add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.HALF_EVEN);

                tileNum1 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityTopRow));

                tileNum2 = BigDecimal.valueOf(gamePanel.getWorldBuilder().getTileTypeAtPos(entityRightCol, entityBottomRow));

                entity.setCollision(
                        gamePanel.getWorldBuilder().getTile(tileNum1.intValue())
                                .isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2.intValue())
                                .isCollision());
        }
    }

    public void checkObjects(LivingEntity entity, ObjectManager objectManager) {
        for (Object object: objectManager.getObjects()) {
            if (entity.getEntityType().equals(EntityType.Player) &&
                    checkIntersection(entity, object))
            {
                entity.interactObject(object);
                objectManager.getObjects().remove(object);
            }
        }
    }

    private boolean checkIntersection(LivingEntity entity, Object object) {
        return entity.getCollisionArea().intersects(object.getCollisionArea());
    }
}
