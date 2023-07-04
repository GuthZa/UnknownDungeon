package models;

import main.GamePanel;

import java.awt.*;

public class Enemy extends LivingEntity {

    GamePanel gamePanel;
    int monsterX;
    int monsterY;

    public Enemy(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        monsterX = 120;
        monsterY = 150;
    }

    public void setDefaultValues() {
        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 23;
        speed = 4;
        direction = "down";
    }

    public void update() {
        monsterX+=speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect(monsterX,monsterY,gamePanel.getTileSize(),gamePanel.getTileSize());

        g2.drawRect(monsterX, monsterY, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
