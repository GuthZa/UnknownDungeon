package models;

import main.GamePanel;

import java.awt.*;
import java.math.BigDecimal;

public class Enemy extends LivingEntity{
    private final GamePanel gamePanel;
    public Enemy(BigDecimal worldX, BigDecimal worldY, String direction, EntityType type, GamePanel gamePanel) {
        super(worldX, worldY, direction, type);
        this.gamePanel = gamePanel;
        checkPositionOnScreen();
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        checkPositionOnScreen();
        g2.setColor(Color.cyan);
        g2.fillRect(getScreenX().intValue(), getScreenY().intValue(),
                gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize().intValue());
        g2.drawRect(getScreenX().intValue(), getScreenY().intValue(),
                gamePanel.getTileSize().intValue(),
                gamePanel.getTileSize().intValue());
    }

    private void checkPositionOnScreen() {
        setScreenX(getWorldX().subtract(gamePanel.getPlayer().getWorldX()).add(gamePanel.getPlayer().getScreenX()));
        setScreenY(getWorldY().subtract(gamePanel.getPlayer().getWorldY()).add(gamePanel.getPlayer().getScreenY()));
    }
}
