package models;

import engine.KeyHandler;
import lombok.*;
import main.GamePanel;
import objects.Object;

import java.awt.*;
import java.math.BigDecimal;

@Getter
public class Player extends LivingEntity {
    private static final String PLAYER_STARTING_DIRECTION = "right";
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

    public void update() {
        if(isMoving && !characterReachedPosition()) {
            move(getDirection());
            return;
        }
        if(keyHandler.isInteractPressed()) {
            setDesiredMovement();
            if (gamePanel.getObjectManager()
                    .checkObjectAt(getMovementWorldX(), getMovementWorldY())) {
                //Check this function not to give player the ability to remove items


                Object obj = gamePanel.getObjectManager().
                        getObjectAt(getMovementWorldX(), getMovementWorldY());
                interactObject(obj);
                gamePanel.getObjectManager().removeObject(obj);
            }
            cancelMovement();
        }
        if (isMovementKeyPressed()){ checkIfCharacterIsFacingPosition(); }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
        g2.drawRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }

    private boolean isMovementKeyPressed() {
        return  keyHandler.isUpPressed() ||
                keyHandler.isDownPressed() ||
                keyHandler.isLeftPressed() ||
                keyHandler.isRightPressed();
    }

    //TODO Maybe change to state changer -> moving, standing, fighting, trading

    private boolean characterReachedPosition() {
        return getMovementWorldX().compareTo(getWorldX()) == 0 &&
                getMovementWorldY().compareTo(getWorldY()) == 0;
    }

    private void checkCollision() {
        setDesiredMovement();
        if(gamePanel.getCollisionManager()
                .canMoveTo(getMovementWorldX(), getMovementWorldY()) &&
                !gamePanel.getObjectManager()
                        .checkObjectAt(getMovementWorldX(), getMovementWorldY())) {
            setMoving(true);
            move(getDirection());
            return;
        }
        cancelMovement();
    }

    private void checkIfCharacterIsFacingPosition() {
        if (keyHandler.isUpPressed()) {
            desiredDirection = "up";
        } else if (keyHandler.isDownPressed()) {
            desiredDirection = "down";
        } else if (keyHandler.isRightPressed()) {
            desiredDirection = "right";
        } else if (keyHandler.isLeftPressed()) {
            desiredDirection = "left";
        }
        if(desiredDirection.equals(getDirection())) checkCollision();
        setDirection(desiredDirection);
    }

    private void cancelMovement() {
        setMovementWorldX(getWorldX());
        setMovementWorldY(getWorldY());
    }
    private void setDesiredMovement() {
        switch (getDirection()){
            case "up" -> setMovementWorldY(getWorldY().subtract(gamePanel.getTileSize()));
            case "down" -> setMovementWorldY(getWorldY().add(gamePanel.getTileSize()));
            case "left" -> setMovementWorldX(getWorldX().subtract(gamePanel.getTileSize()));
            case "right" -> setMovementWorldX(getWorldX().add(gamePanel.getTileSize()));
        }
    }
    private void setMoving(boolean movement) {
        this.isMoving = movement;
    }


    //Object manager
    public void interactObject(Object object) {
        switch (object.getItemCategory()) {
            case Boots -> {
                increaseSpeed();
                getObjectList().add(object);
            }
            case Key -> {
                addKey();
            }
            case Door -> {
                removeKey();
            }
        }
    }
}
