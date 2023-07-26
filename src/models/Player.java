package models;

import engine.KeyHandler;
import lombok.*;
import main.GamePanel;
import objects.ItemCategory;
import objects.Object;

import java.awt.*;
import java.math.BigDecimal;

@Getter
public class Player extends LivingEntity {
    private static final String PLAYER_STARTING_DIRECTION = "right";
    private String desiredDirection = PLAYER_STARTING_DIRECTION;
    private static final BigDecimal PLAYER_DEFAULT_SPEED = BigDecimal.valueOf(4);
    private static final BigDecimal PLAYER_RUNNING_SPEED = PLAYER_DEFAULT_SPEED.add(BigDecimal.valueOf(2));

    private final GamePanel gamePanel;
    private final KeyHandler keyHandler;

    private boolean hasBoots = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                gamePanel.getTileSize().multiply(BigDecimal.valueOf(23)),
                PLAYER_STARTING_DIRECTION,
                EntityType.Player);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setSpeed(PLAYER_DEFAULT_SPEED);

        setScreenX(BigDecimal.valueOf(gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize().intValue() / 2)));
        setScreenY(BigDecimal.valueOf(gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize().intValue() / 2)));
    }

    public void update() {
        if(!characterReachedPosition()) {
            checkSpeed();
            move(getDirection());
            checkNextMovementOvershoot();
            return;
        }
//        if(inCombat) {
//            setInCombat(false);
//        }
        if(keyHandler.isInteractPressed()) {
            checkObjectIsInteractable();
            checkEnemy();
        }
        if(isMovementKeyPressed()){
            checkIfCharacterIsFacingPosition();
        }
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
        g2.drawRect(getScreenX().intValue(), getScreenY().intValue(), gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue());
    }

    private void checkCollision() {
        setDesiredMovement();
        if(
                gamePanel.canMoveTo(getMovementWorldX(), getMovementWorldY()) &&
                !gamePanel.checkObjectAt(getMovementWorldX(), getMovementWorldY()) &&
                !gamePanel.checkEnemyAt(getMovementWorldX(), getMovementWorldY())
        ) {
            move(getDirection());
            return;
        }
        cancelMovement();
    }

    private void checkEnemy() {
        setDesiredMovement();
        if(gamePanel.checkEnemyAt(getMovementWorldX(), getMovementWorldY())) {
            //swing at enemy for damage
        }
        cancelMovement();
    }

    //TODO redo movement
    //if you run, the character moves continuously because it never reaches
    //the casted position
    //ex: 48 with speed of 4 -> 48/4 = 12 frames
    //speed of 6 -> 8 frames
    //if you change speed mid drawing it miss aligns
    // 1 frame at 4 and all at 6 makes 46 -> 52 pixels

    protected void checkSpeed() {
        if(hasBoots && keyHandler.isBackPressed()) {
            setSpeed(PLAYER_RUNNING_SPEED);
            return;
        }
        setSpeed(PLAYER_DEFAULT_SPEED);
    }

    //functions to move / cast desired position to move
    //By calling setDesiredMovement, you make the character move in the direction it's
    //facing
    //By calling setDesiredMovement and cancelMovement, you can check a block in front
    //of the character
    private boolean isMovementKeyPressed() {
        return  keyHandler.isUpPressed() ||
                keyHandler.isDownPressed() ||
                keyHandler.isLeftPressed() ||
                keyHandler.isRightPressed();
    }
    private boolean characterReachedPosition() {
        return getMovementWorldX().compareTo(getWorldX()) == 0 &&
                getMovementWorldY().compareTo(getWorldY()) == 0;
    }
    private void checkNextMovementOvershoot() {
        switch (getDirection()) {
            case "up" -> {
                if(getWorldY().subtract(PLAYER_DEFAULT_SPEED).compareTo(getMovementWorldY()) < 0)
                    setWorldY(getMovementWorldY());
                if(getWorldY().subtract(PLAYER_RUNNING_SPEED).compareTo(getMovementWorldY()) < 0)
                    setWorldY(getMovementWorldY());
            }
            case "down" -> {
                if(getWorldY().add(PLAYER_DEFAULT_SPEED).compareTo(getMovementWorldY()) > 0)
                    setWorldY(getMovementWorldY());
                if(getWorldY().add(PLAYER_RUNNING_SPEED).compareTo(getMovementWorldY()) > 0)
                    setWorldY(getMovementWorldY());
            }
            case "left" -> {
                if(getWorldX().subtract(PLAYER_DEFAULT_SPEED).compareTo(getMovementWorldX()) < 0)
                    setWorldX(getMovementWorldX());
                if(getWorldX().subtract(PLAYER_RUNNING_SPEED).compareTo(getMovementWorldX()) < 0)
                    setWorldX(getMovementWorldX());
            }
            case "right" -> {
                if(getWorldX().add(PLAYER_DEFAULT_SPEED).compareTo(getMovementWorldX()) > 0)
                    setWorldX(getMovementWorldX());
                if(getWorldX().add(PLAYER_RUNNING_SPEED).compareTo(getMovementWorldX()) > 0)
                    setWorldX(getMovementWorldX());
            }
        }
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
    private void setDesiredMovement() {
        switch (getDirection()){
            case "up" -> setMovementWorldY(getWorldY().subtract(gamePanel.getTileSize()));
            case "down" -> setMovementWorldY(getWorldY().add(gamePanel.getTileSize()));
            case "left" -> setMovementWorldX(getWorldX().subtract(gamePanel.getTileSize()));
            case "right" -> setMovementWorldX(getWorldX().add(gamePanel.getTileSize()));
        }
    }
    private void cancelMovement() {
        setMovementWorldX(getWorldX());
        setMovementWorldY(getWorldY());
    }

    //Object manager
    private void checkObjectIsInteractable() {
        setDesiredMovement();
        if (gamePanel.checkObjectAt(getMovementWorldX(), getMovementWorldY())) {
            //Check this function not to give player the ability to remove items


            Object obj = gamePanel.getObjectAt(getMovementWorldX(), getMovementWorldY());

            if(obj!=null) {
                if(obj.getItemCategory() == ItemCategory.Door && getKeyNumber()<=0) {
                    cancelMovement();
                    return;
                }
                gamePanel.givePlayerObject(this, obj);
            } else {
                System.out.println("Object passed is null");
            }
        }
        cancelMovement();
    }

    public void interactObject(Object object) {
        switch (object.getItemCategory()) {
            case Boots -> {
                hasBoots = true;
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
