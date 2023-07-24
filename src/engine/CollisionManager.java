package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;

import java.math.BigDecimal;


@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;

    public boolean canMoveTo(BigDecimal worldX, BigDecimal worldY){
        return !gamePanel.getTileAt(worldX, worldY)
                .isCollision();
    }

    public boolean checkEnemyAt(BigDecimal worldX, BigDecimal worldY) {
        return gamePanel.getEnemy().getWorldX().compareTo(worldX) == 0 &&
        gamePanel.getEnemy().getWorldY().compareTo(worldY) == 0;
    }
}
