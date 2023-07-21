package objects;


import lombok.Getter;
import lombok.Setter;
import main.GamePanel;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

@Getter
@Setter
public class Object {
    private final ItemCategory itemCategory;

    private BufferedImage image;
    private final BigDecimal worldX, worldY;
    public Object(ItemCategory itemCategory, BigDecimal worldX, BigDecimal worldY) {
        this.itemCategory = itemCategory;
        this.worldX = worldX;
        this.worldY = worldY;
        try {
            this.image = ImageIO.read(new FileInputStream("resources/objects/"+ this.itemCategory +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
