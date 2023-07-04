package models;

import engine.KeyHandler;
import main.GamePanel;

import java.awt.*;

public class Player extends LivingEntity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    private int screenX;
    private int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.getTileSize() / 2);

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 23;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                screenY -=speed;
            } else if (keyHandler.downPressed) {
                screenY +=speed;
            } else if (keyHandler.rightPressed) {
                screenX +=speed;
            } else if (keyHandler.leftPressed) {
                screenX -=speed;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(screenX, screenY,gamePanel.getTileSize(),gamePanel.getTileSize());

        g2.drawRect(screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }


}
