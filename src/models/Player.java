package models;

import engine.KeyHandler;
import main.GamePanel;

import java.awt.*;
import java.math.BigDecimal;

public class Player extends LivingEntity {
    private static final int PLAYER_DEFAULT_SPEED = 4;
    private static final String PLAYER_STARTING_DIRECTION = "down";
    private final int tileSize;
    GamePanel gamePanel;
    KeyHandler keyHandler;

    private final BigDecimal screenX;
    private final BigDecimal screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        tileSize = gamePanel.getTileSize().intValue();

        screenX = BigDecimal.valueOf(gamePanel.getScreenWidth() / 2 - (tileSize / 2));
        screenY = BigDecimal.valueOf(gamePanel.getScreenHeight() / 2 - (tileSize / 2));

        this.setDefaultValues(tileSize * 23, tileSize * 23, PLAYER_DEFAULT_SPEED, PLAYER_STARTING_DIRECTION);
    }
    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) { this.moveUp(); }
            if (keyHandler.downPressed) { this.moveDown(); }
            if (keyHandler.rightPressed) { this.moveLeft(); }
            if (keyHandler.leftPressed) { this.moveRight(); }
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(screenX.intValue(), screenY.intValue(), tileSize, tileSize);
        g2.drawRect(screenX.intValue(), screenY.intValue(), tileSize, tileSize);
    }

    public BigDecimal getScreenX() {
        return screenX;
    }

    public BigDecimal getScreenY() {
        return screenY;
    }


}
