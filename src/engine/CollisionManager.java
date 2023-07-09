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

        BigDecimal TopLeftCorner = BigDecimal.valueOf(entity.getCollisionArea().getX());
        BigDecimal BottomLeftCorner =
                BigDecimal.valueOf(entity.getCollisionArea().getX())
                .add(BigDecimal.valueOf(entity.getCollisionArea().getWidth()));
        BigDecimal TopRightCorner =
                BigDecimal.valueOf(entity.getCollisionArea().getY())
                .add(BigDecimal.valueOf(entity.getCollisionArea().getHeight()));
        BigDecimal BottomRightCorner = TopRightCorner.add(BottomLeftCorner);

        Tile tileAtLeft = gamePanel.getWorldBuilder().getTileAtPos(TopLeftCorner.add(gamePanel.getTileSize()), TopLeftCorner);


        //TODO Add collision area to tiles ?


//        if(TopLeftCorner.compareTo(gamePanel.getWorldBuilder().getTile()))




//        switch (entity.getDirection()) {
//            case "left" -> {
//                System.out.println("collision left");
//            }
//            case "right" -> {
//                System.out.println("collision right");
//            }
//            case "up" -> {
//                System.out.println("collision up");
//            }
//            case "down" -> {
//                System.out.println("collision bottom");
//            }
//        }
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
