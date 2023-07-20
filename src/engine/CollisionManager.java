package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import objects.Object;

import java.math.BigDecimal;


@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;

    public boolean canMoveTo(BigDecimal worldX, BigDecimal worldY){
        return !gamePanel.getWorldBuilder()
                .getTileAt(worldX, worldY)
                .isCollision();
    }

//    public void checkObjectAt(BigDecimal worldX, BigDecimal worldY) {
//        for (Object object: gamePanel.getObjectManager().getObjects()) {
//            if(object.getWorldX().equals(worldX) &&
//            object.getWorldY().equals(worldY)) {
//                entity.interactObject(object);
//                objectManager.getObjects().remove(object);
//            }
//        }
//    }
}
