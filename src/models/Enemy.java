package models;

import main.GamePanel;

import java.awt.*;

public class Enemy extends LivingEntity {

    GamePanel gamePanel;
    int monsterX;
    int monsterY;

    public Enemy(GamePanel gamePanel) {
        this.setDefaultValues(gamePanel.getTileSize().intValue() * 23, gamePanel.getTileSize().intValue() * 23, 4, "down");
        this.gamePanel = gamePanel;

        monsterX = 120;
        monsterY = 150;
    }

    public void update() {}

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect(getWorldX().intValue(),getWorldY().intValue(),gamePanel.getTileSize().intValue(),gamePanel.getTileSize().intValue());

        g2.drawRect(getWorldX().intValue(), getWorldY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }
}
