package ui;

import main.GamePanel;
import objects.Object;
import objects.ItemCategory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class UI {
    GamePanel gamePanel;
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        Object key = new Object(ItemCategory.Key);
        keyImage = key.getImage();
    }

    public void showInventory(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.setBackground(Color.gray);
        g2.fillRect(50, 30, 680, 500);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage,
                gamePanel.getTileSize()
                        .divide(BigDecimal.valueOf(2), RoundingMode.DOWN).intValue(),
                gamePanel.getTileSize()
                        .divide(BigDecimal.valueOf(2), RoundingMode.DOWN).intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue(), null);
        g2.drawString(" " + gamePanel.getPlayer().getKeyNumber(), 74, 65);
    }
}
