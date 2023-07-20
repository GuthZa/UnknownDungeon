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


    public boolean checkMovementPossible(LivingEntity entity){
        return !gamePanel.getWorldBuilder().getTileAt(
                entity.getMovementWorldX(), entity.getMovementWorldY())
                .isCollision();
    }

    public void checkObject(LivingEntity entity, ObjectManager objectManager) {
        for (Object object: objectManager.getObjects()) {
            System.out.println(object.getItemCategory().name);
            if(object.getWorldX().equals(entity.getMovementWorldX()) &&
            object.getWorldY().equals(entity.getMovementWorldY()) &&
            entity.getEntityType().equals(EntityType.Player)) {
                entity.interactObject(object);
                objectManager.getObjects().remove(object);
            }
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
