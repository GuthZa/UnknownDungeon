package objects;

import lombok.AccessLevel;
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
    enum ItemCategory {
        Key,
        Chest,
        Door,
        Boots
    }
    @Setter(AccessLevel.NONE)
    private BufferedImage image;
    private boolean collision;
    private final BigDecimal worldX, worldY;
    private final Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
    public Object(ItemCategory itemCategory, int worldX, int worldY) {
        this.itemCategory = itemCategory;
        this.worldX = BigDecimal.valueOf(worldX);
        this.worldY = BigDecimal.valueOf(worldY);
        this.collision =
                itemCategory.equals(ItemCategory.Door) ||
                itemCategory.equals(ItemCategory.Chest);
        try {
            this.image = ImageIO.read(new FileInputStream("resources/objects/"+ this.itemCategory +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkObjectInScreen(GamePanel gamePanel) {
        return
                worldX.add(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenX())) > 0 ||
                worldX.subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().subtract(gamePanel.getPlayer().getScreenX())) < 0||
                worldY.add(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY())) > 0 ||
                worldY.subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldY().subtract(gamePanel.getPlayer().getScreenY())) < 0;
    }

    public BigDecimal getScreenLeftX(GamePanel gamePanel) {
        return worldX.subtract(gamePanel.getPlayer().getWorldX().subtract(gamePanel.getPlayer().getScreenY()));
    }
    public BigDecimal getScreenRightX(GamePanel gamePanel) {
        return worldX.subtract(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenY()));
    }
    public BigDecimal getScreenBottomY(GamePanel gamePanel) {
        return worldX.subtract(gamePanel.getPlayer().getWorldY().subtract(gamePanel.getPlayer().getScreenY()));
    }
    public BigDecimal getScreenTopY(GamePanel gamePanel) {
        return worldX.subtract(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY()));
    }
}
