package objects;


import lombok.Getter;
import lombok.Setter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

@Getter
@Setter
public class Object {
    private final ItemCategory itemCategory;
    private boolean grabbed = false;

    private BufferedImage image;
    private boolean collision;
    private final BigDecimal worldX, worldY;
    private Rectangle collisionArea;
    public Object(ItemCategory itemCategory, BigDecimal worldX, BigDecimal worldY, BigDecimal tileSize) {
        this.itemCategory = itemCategory;
        this.worldX = worldX;
        this.worldY = worldY;
        this.collision =
                itemCategory.equals(ItemCategory.Door) ||
                itemCategory.equals(ItemCategory.Chest);
        try {
            this.image = ImageIO.read(new FileInputStream("resources/objects/"+ this.itemCategory +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collisionArea = new Rectangle(worldX.intValue(), worldY.intValue(), tileSize.intValue(), tileSize.intValue());
    }
    public boolean checkObjectInScreen(GamePanel gamePanel) {
        return
                worldX.add(gamePanel.getTileSize())
                        .compareTo(gamePanel.getPlayer().getWorldX()
                                .subtract(gamePanel.getPlayer().getScreenX())) > 0 ||
                worldX.subtract(gamePanel.getTileSize())
                        .compareTo(gamePanel.getPlayer().getWorldX()
                                .add(gamePanel.getPlayer().getScreenX())) < 0 ||
                worldY.add(gamePanel.getTileSize())
                        .compareTo(gamePanel.getPlayer().getWorldY()
                                .subtract(gamePanel.getPlayer().getScreenY())) > 0 ||
                worldY.subtract(gamePanel.getTileSize())
                        .compareTo(gamePanel.getPlayer().getWorldY()
                                .add(gamePanel.getPlayer().getScreenY())) < 0;
    }
}
