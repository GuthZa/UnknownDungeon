package models;

import engine.KeyHandler;
import lombok.*;
import main.GamePanel;

import java.awt.*;
import java.math.BigDecimal;

@Getter
public class Player extends LivingEntity {
    private static final String PLAYER_STARTING_DIRECTION = "down";

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                PLAYER_STARTING_DIRECTION,
                EntityType.Player);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setScreenX(BigDecimal.valueOf(gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize().intValue() / 2)));
        setScreenY(BigDecimal.valueOf(gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize().intValue() / 2)));
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void update() {
        //updateCollisionArea();
        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
            if (keyHandler.isUpPressed()) {
                setDirection("up");
            }
            else if (keyHandler.isDownPressed()) {
                setDirection("down");
            }
            else if (keyHandler.isRightPressed()) {
                setDirection("right");
            }
            else if (keyHandler.isLeftPressed()) {
                setDirection("left");
            }
        }

        setCollision(false);
        gamePanel.getCollisionManager().checkTile(this);

        gamePanel.getCollisionManager().checkObjects(this, gamePanel.getObjectManager());


        if(keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()) {
            if (!isCollision()) {
                if(keyHandler.isUpPressed()) moveUp();
                else if(keyHandler.isDownPressed()) moveDown();
                else if(keyHandler.isLeftPressed()) moveLeft();
                else if(keyHandler.isRightPressed()) moveRight();
            }
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
        g2.drawRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }
}
