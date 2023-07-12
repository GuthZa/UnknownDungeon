package engine;

import lombok.AllArgsConstructor;
import main.GamePanel;
import models.EntityType;
import models.LivingEntity;
import objects.Object;
import objects.ObjectManager;

import tile.WorldBuilder;

import java.math.BigDecimal;


@AllArgsConstructor
public class CollisionManager {
    private GamePanel gamePanel;
    public void checkTile(LivingEntity entity, WorldBuilder worldBuilder) {
        worldBuilder.checkTilesNear(entity);

        switch (entity.getDirection()) {
            case "up" -> {
                entity.getCastCollisionArea().setLocation(
                        (int) entity.getCollisionArea().getX(),
                        BigDecimal.valueOf(
                                entity.getCollisionArea().getY())
                                .subtract(entity.getSpeed())
                                .intValue()
                );

//                System.out.println(worldBuilder.getTileNearPlayer(entity).getCollisionArea().getX());
//                System.out.println(worldBuilder.getTileNearPlayer(entity).getCollisionArea().getY());
//                if(entity.getCastCollisionArea().intersects(
//                        worldBuilder.getTileNearPlayer(entity).getCollisionArea()
//                )) {
//                    System.out.println("up collision");
//                    entity.setCollision(true);
//                }
            }
            case "down" -> {
                entity.getCastCollisionArea().setLocation(
                        (int) entity.getCollisionArea().getX(),
                        BigDecimal.valueOf(
                                entity.getCollisionArea().getY())
                                .add(entity.getSpeed())
                                .intValue()
                );

//                if(entity.getCastCollisionArea().intersects(
//                        worldBuilder.getTileAtWorldPos(
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getY()))
//                                .getCollisionArea()) ||
//
//                        entity.getCastCollisionArea().intersects(
//                                worldBuilder.getTileAtWorldPos(
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getY())
//                                                        .add(BigDecimal.valueOf(entity.getCastCollisionArea().getWidth())))
//                                        .getCollisionArea())
//                ) {
//                    System.out.println("down collision");
//                    entity.setCollision(true);
//                }
            }
            case "left" -> {
                entity.getCastCollisionArea().setLocation(
                        BigDecimal.valueOf(
                                entity.getCollisionArea().getX())
                                .subtract(entity.getSpeed())
                                .intValue(),
                        (int) entity.getCollisionArea().getY()
                );
//                if(entity.getCastCollisionArea().intersects(
//                        worldBuilder.getTileAtWorldPos(
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getY()))
//                                .getCollisionArea()) ||
//
//                        entity.getCastCollisionArea().intersects(
//                                worldBuilder.getTileAtWorldPos(
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getY())
//                                                        .add(BigDecimal.valueOf(entity.getCastCollisionArea().getWidth())))
//                                        .getCollisionArea())
//                ) {
//                    System.out.println("left collision");
//                    entity.setCollision(true);
//                }
            }
            case "right" -> {
                entity.getCastCollisionArea().setLocation(
                        BigDecimal.valueOf(
                                entity.getCollisionArea().getX())
                                .add(entity.getSpeed())
                                .intValue(),
                        (int) entity.getCollisionArea().getY()
                );

//                if(entity.getCastCollisionArea().intersects(
//                        worldBuilder.getTileAtWorldPos(
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                        BigDecimal.valueOf(entity.getCastCollisionArea().getY()))
//                                .getCollisionArea()) ||
//
//                        entity.getCastCollisionArea().intersects(
//                                worldBuilder.getTileAtWorldPos(
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getX()),
//                                                BigDecimal.valueOf(entity.getCastCollisionArea().getY())
//                                                        .add(BigDecimal.valueOf(entity.getCastCollisionArea().getWidth())))
//                                        .getCollisionArea())
//                ) {
//                    System.out.println("right collision");
//                    entity.setCollision(true);
//                }
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
