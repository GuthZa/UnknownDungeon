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

@Getter
@Setter
public class ObjectManager {

    private final GamePanel gamePanel;
    private final Object[] objects;

    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.objects = new Object[10];
        loadObjects();
    }

    public void loadObjects() {
        //Keys
        objects[0] = new Object(Object.ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue());
        objects[1] = new Object(Object.ItemCategory.Key,
                23 * gamePanel.getTileSize().intValue(),
                40 * gamePanel.getTileSize().intValue());
        objects[2] = new Object(Object.ItemCategory.Key,
                38 * gamePanel.getTileSize().intValue(),
                8 * gamePanel.getTileSize().intValue());

        //Doors
        objects[4] = new Object(Object.ItemCategory.Door,
                10 * gamePanel.getTileSize().intValue(),
                11 * gamePanel.getTileSize().intValue());
        objects[5] = new Object(Object.ItemCategory.Door,
                8 * gamePanel.getTileSize().intValue(),
                28 * gamePanel.getTileSize().intValue());
        objects[6] = new Object(Object.ItemCategory.Door,
                12 * gamePanel.getTileSize().intValue(),
                2 * gamePanel.getTileSize().intValue());

        //Chest
        objects[7] = new Object(Object.ItemCategory.Chest,
                10 * gamePanel.getTileSize().intValue(),
                7 * gamePanel.getTileSize().intValue());

        //Boots
        objects[8] = new Object(Object.ItemCategory.Boots,
                37 * gamePanel.getTileSize().intValue(),
                42 * gamePanel.getTileSize().intValue());
    }
    public void draw(Graphics2D g2) {
        BigDecimal screenX = worldX.subtract();
        BigDecimal screenY = worldY.subtract(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY()));

        if(
        objects[i].getWorldX().add(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().subtract(gamePanel.getPlayer().getScreenX())) > 0 &&
        objects[i].getWorldX().subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenX())) < 0 &&
        objects[i].getWorldY().subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldY().subtract(gamePanel.getPlayer().getScreenY())) > 0 &&
                        worldY.compareTo(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY())) < 0
        ) {
            g2.drawImage(image, screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue(), null);
        }
   }
}
