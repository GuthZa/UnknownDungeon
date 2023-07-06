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
    private ItemCategory itemCategory;
    enum ItemCategory {
        Key,
        Chest,
        Door,
        Boots
    }
    @Setter(AccessLevel.NONE)
    private BufferedImage image;
    private boolean collision;
    private BigDecimal worldX, worldY;
    private final Rectangle collisionArea = new Rectangle(0, 0, 48, 48);

    protected void setupImage() {
        try {this.image = ImageIO.read(new FileInputStream("resources/objects/"+ itemCategory +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BigDecimal screenX = worldX.subtract(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenX()));
        BigDecimal screenY = worldY.subtract(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY()));

        if(
                worldX.compareTo(gamePanel.getPlayer().getWorldX().subtract(gamePanel.getPlayer().getScreenX())) > 0 &&
                        worldX.compareTo(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenX())) < 0 &&
                        worldY.compareTo(gamePanel.getPlayer().getWorldY().subtract(gamePanel.getPlayer().getScreenY())) > 0 &&
                        worldY.compareTo(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY())) < 0
        ) {
            g2.drawRect(screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
            g2.drawImage(image, screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue(), null);
        }
    }
}
