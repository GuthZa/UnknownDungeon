package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import models.LivingEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;

    public void checkTile(LivingEntity entity) {
        BigDecimal entityLeftWorldX = entity.getWorldX().add(BigDecimal.valueOf(entity.getCollisionArea().getX()));
        BigDecimal entityRightWorldX = entity.getWorldX().add(BigDecimal.valueOf(entity.getCollisionArea().getWidth()));
        BigDecimal entityTopWorldY = entity.getWorldY().add(BigDecimal.valueOf(entity.getCollisionArea().getY()));
        BigDecimal entityBottomWorldY = entity.getWorldY().add(BigDecimal.valueOf(entity.getCollisionArea().getHeight()));

        BigDecimal entityLeftCol = entityLeftWorldX.divide(gamePanel.getTileSize(), RoundingMode.CEILING);
        BigDecimal entityRightCol = entityRightWorldX.divide(gamePanel.getTileSize(), RoundingMode.CEILING);
        BigDecimal entityTopRow = entityTopWorldY.divide(gamePanel.getTileSize(), RoundingMode.CEILING);
        BigDecimal entityBottomRow = entityBottomWorldY.divide(gamePanel.getTileSize(), RoundingMode.CEILING);

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "left" -> {
                entityLeftCol = entityLeftWorldX.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.CEILING);
                tileNum1 = gamePanel.getWorldBuilder().getMap()[entityLeftCol.intValue()][entityTopRow.intValue()];
                tileNum2 = gamePanel.getWorldBuilder().getMap()[entityLeftCol.intValue()][entityBottomRow.intValue()];
                if(gamePanel.getWorldBuilder().getTile(tileNum1).isCollision() ||
                gamePanel.getWorldBuilder().getTile(tileNum2).isCollision()) {
                    entity.setCollision(true);
                }
            }
            case "right" -> {
                entityRightCol = entityRightWorldX.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.CEILING);
                tileNum1 = gamePanel.getWorldBuilder().getMap()[entityRightCol.intValue()][entityTopRow.intValue()];
                tileNum2 = gamePanel.getWorldBuilder().getMap()[entityRightCol.intValue()][entityBottomRow.intValue()];
                if(gamePanel.getWorldBuilder().getTile(tileNum1).isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2).isCollision()) {
                    entity.setCollision(true);
                }
            }
            case "up" -> {
                entityTopRow = entityTopWorldY.subtract(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.CEILING);
                tileNum1 = gamePanel.getWorldBuilder().getMap()[entityLeftCol.intValue()][entityTopRow.intValue()];
                tileNum2 = gamePanel.getWorldBuilder().getMap()[entityRightCol.intValue()][entityTopRow.intValue()];
                if(gamePanel.getWorldBuilder().getTile(tileNum1).isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2).isCollision()) {
                    entity.setCollision(true);
                }
            }
            case "down" -> {
                entityBottomRow = entityBottomWorldY.add(entity.getSpeed()).divide(gamePanel.getTileSize(), RoundingMode.CEILING);
                tileNum1 = gamePanel.getWorldBuilder().getMap()[entityLeftCol.intValue()][entityBottomRow.intValue()];
                tileNum2 = gamePanel.getWorldBuilder().getMap()[entityRightCol.intValue()][entityBottomRow.intValue()];
                if(gamePanel.getWorldBuilder().getTile(tileNum1).isCollision() ||
                        gamePanel.getWorldBuilder().getTile(tileNum2).isCollision()) {
                    entity.setCollision(true);
                }
            }
        }
    }
}
