package objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Key;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
public class ObjectManager {

    private final GamePanel gamePanel;
    private final ArrayList<Object> objects = new ArrayList<Object>();

    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadObjects();
    }

    public void loadObjects() {
        //Keys
        objects.add(new Object(ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                40 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Key,
                38 * gamePanel.getTileSize().intValue(),
                8 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));

        //Doors
        objects.add(new Object(ItemCategory.Door,
                10 * gamePanel.getTileSize().intValue(),
                11 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Door,
                8 * gamePanel.getTileSize().intValue(),
                28 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Door,
                12 * gamePanel.getTileSize().intValue(),
                24 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));

        //Chest
        objects.add(new Object(ItemCategory.Chest,
                10 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));

        //Boots
        objects.add(new Object(ItemCategory.Boots,
                37 * gamePanel.getTileSize().intValue(),
                42 * gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize()));
    }
    public void draw(Graphics2D g2) {

       for (Object obj : objects) {
           if (obj.checkObjectInScreen(gamePanel)) {
               BigDecimal screenX = obj.getWorldX().subtract(gamePanel.getPlayer().getWorldX()).add(gamePanel.getPlayer().getScreenX());
               BigDecimal screenY = obj.getWorldY().subtract(gamePanel.getPlayer().getWorldY()).add(gamePanel.getPlayer().getScreenY());

               if(!obj.isGrabbed()) {
                   g2.drawImage(obj.getImage(), screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue(), null);
               }
           }
       }
   }

   public Object getObjectByIndex(int index) {
        return index < objects.size() && index >= 0 ? objects.get(index) : null;
   }
}
