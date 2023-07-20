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
public class ObjectManager {

    private final GamePanel gamePanel;
    private final ArrayList<Object> objects = new ArrayList<>();

    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadObjects();
    }

    public void loadObjects() {
        //Keys
        objects.add(new Object(ItemCategory.Key,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(7)),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Key,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(40)),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Key,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(38)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(8)),
                gamePanel.getTileSize()));

        //Doors
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(10)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(11)),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(8)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(28)),
                gamePanel.getTileSize()));
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(12)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(24)),
                gamePanel.getTileSize()));

        //Chest
        objects.add(new Object(ItemCategory.Chest,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(10)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(7)),
                gamePanel.getTileSize()));

        //Boots
        objects.add(new Object(ItemCategory.Boots,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(37)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(42)),
                gamePanel.getTileSize()));
    }
    public void draw(Graphics2D g2) {
       for (Object obj : objects) {
           if (obj.checkObjectInScreen(gamePanel)) {
               BigDecimal screenX = obj.getWorldX()
                       .subtract(gamePanel.getPlayer().getWorldX())
                       .add(gamePanel.getPlayer().getScreenX());
               BigDecimal screenY = obj.getWorldY()
                       .subtract(gamePanel.getPlayer().getWorldY())
                       .add(gamePanel.getPlayer().getScreenY());

               g2.drawImage(obj.getImage(),
                       screenX.intValue(), screenY.intValue(),
                       gamePanel.getTileSize().intValue(),
                       gamePanel.getTileSize().intValue(), null);
           }
       }
   }

   public boolean checkObjectAt(BigDecimal worldX, BigDecimal worldY) {
       for (Object obj : objects) {
           if(worldX.equals(obj.getWorldX()) && worldY.equals(obj.getWorldY())) {
               return true;
           }
       }
       return false;
   }
}
