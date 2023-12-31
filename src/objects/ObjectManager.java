package objects;

import lombok.Getter;
import main.GamePanel;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

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
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(7))));
        objects.add(new Object(ItemCategory.Key,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(40))));
        objects.add(new Object(ItemCategory.Key,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(38)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(8))));

        //Doors
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(10)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(11))));
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(8)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(28))));
        objects.add(new Object(ItemCategory.Door,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(12)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(24))));

        //Chest
        objects.add(new Object(ItemCategory.Chest,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(10)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(7))));

        //Boots
        objects.add(new Object(ItemCategory.Boots,
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(37)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(42))));
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

   public Object getObjectAt(BigDecimal worldX, BigDecimal worldY) {
       for (Object obj : objects) {
           if(worldX.equals(obj.getWorldX()) && worldY.equals(obj.getWorldY())) {
               return obj;
           }
       }
       return null;
   }

   public void removeObject(Object object) {
        objects.remove(object);
   }
}
