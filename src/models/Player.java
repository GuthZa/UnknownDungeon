package models;

import engine.KeyHandler;
import main.GamePanel;

import java.awt.*;

public class Player extends LivingEntity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    int playerXPos;
    int playerYPos;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        playerXPos = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        playerYPos = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                System.out.println("up");
                playerXPos+=speed;
            } else if (keyHandler.downPressed) {
                System.out.println("down");
                playerXPos-=speed;
            } else if (keyHandler.rightPressed) {
                System.out.println("right");
                playerYPos+=speed;
            } else if (keyHandler.leftPressed) {
                System.out.println("left");
                playerYPos-=speed;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(playerXPos, playerYPos, gamePanel.tileSize, gamePanel.tileSize);

    }
}
