package models;

import engine.KeyHandler;
import lombok.*;
import main.GamePanel;

import javax.swing.text.Style;
import java.awt.*;
import java.math.BigDecimal;

@Getter
public class Player extends LivingEntity {
    private static final String PLAYER_STARTING_DIRECTION = "down";
    private String desiredDirection = PLAYER_STARTING_DIRECTION;

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    private boolean isMoving = false;

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
        if(checkCharacterIsMoving()) {
            move(getDirection());
            return;
        }
        if (isKeyPressed()){
            if (checkIfCharacterIsFacingPosition()) {
                //check collision
                setMoving(true);
                move(getDirection());
            } else {
                setDirection(desiredDirection);
            }
        }

        setCollision(false);
        gamePanel.getCollisionManager().checkTile(this);

        gamePanel.getCollisionManager().checkObjects(this, gamePanel.getObjectManager());
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
        g2.drawRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }

    private boolean isKeyPressed() {
        return  keyHandler.isUpPressed() ||
                keyHandler.isDownPressed() ||
                keyHandler.isLeftPressed() ||
                keyHandler.isRightPressed();
    }

    //TODO change to state changer -> moving, standing, fighting, trading

    private boolean checkCharacterIsMoving() {
        System.out.println("-------------------");
        System.out.println(isMoving());
        System.out.println(getMovementWorldX().compareTo(getWorldX()) != 0 );
        System.out.println(getMovementWorldY().compareTo(getWorldY()) != 0 );
        System.out.println(getMovementWorldX());
        System.out.println(getWorldX());

        return isMoving() && getMovementWorldX().compareTo(getWorldX()) != 0 ||
                getMovementWorldY().compareTo(getWorldY()) != 0;
    }

    private boolean checkIfCharacterIsFacingPosition() {
        if (keyHandler.isUpPressed()) {
            desiredDirection = "up";
        } else if (keyHandler.isDownPressed()) {
            desiredDirection = "down";
        } else if (keyHandler.isRightPressed()) {
            desiredDirection = "right";
        } else if (keyHandler.isLeftPressed()) {
            desiredDirection = "left";
        }
        return desiredDirection.equals(getDirection());
    }

    private void setMoving(boolean movement) {
        switch (getDirection()){
            case "up" -> setMovementWorldY(getWorldY().subtract(gamePanel.getTileSize()));
            case "down" -> setMovementWorldY(getWorldY().add(gamePanel.getTileSize()));
            case "left" -> setMovementWorldX(getWorldX().subtract(gamePanel.getTileSize()));
            case "right" -> setMovementWorldX(getWorldX().add(gamePanel.getTileSize()));
        }
        this.isMoving = movement;
    }
}
