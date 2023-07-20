package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;

import java.math.BigDecimal;


@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;

    public boolean canMoveTo(BigDecimal worldX, BigDecimal worldY){
        return !gamePanel.getWorldBuilder()
                .getTileAt(worldX, worldY)
                .isCollision();
    }
}
