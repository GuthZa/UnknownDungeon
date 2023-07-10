package models;

import engine.KeyHandler;
import lombok.*;
import main.GamePanel;

import java.awt.*;
import java.math.BigDecimal;

@Getter
public class Player extends LivingEntity {
    private static final String PLAYER_STARTING_DIRECTION = "down";
    private static final int PLAYER_COLLISION_X = 8;
    private static final int PLAYER_COLLISION_Y = 16;
    private static final int PLAYER_COLLISION_WIDTH = 32;
    private static final int PLAYER_COLLISION_HEIGHT = 32;

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    private final BigDecimal screenX;
    private final BigDecimal screenY;



    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                new Rectangle(PLAYER_COLLISION_X, PLAYER_COLLISION_Y,
                        PLAYER_COLLISION_WIDTH, PLAYER_COLLISION_HEIGHT),
                PLAYER_STARTING_DIRECTION);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = BigDecimal.valueOf(gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize().intValue() / 2));
        screenY = BigDecimal.valueOf(gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize().intValue() / 2));
    }
    public void update() {
        //this.getCollisionArea().setLocation(getWorldX().intValue() + PLAYER_COLLISION_X, getWorldY().intValue() + PLAYER_COLLISION_Y);

        this.setCollision(false);
        gamePanel.getCollisionManager().checkTile(this);
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                setDirection("up");
                if(!isCollision()) this.moveUp();
            }
            if (keyHandler.downPressed) {
                setDirection("down");
                if(!isCollision()) this.moveDown();
            }
            if (keyHandler.rightPressed) {
                setDirection("right");
                if(!isCollision()) this.moveLeft();
            }
            if (keyHandler.leftPressed) {
                setDirection("left");
                if(!isCollision()) this.moveRight();
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
        g2.drawRect(screenX.intValue(), screenY.intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }
}
