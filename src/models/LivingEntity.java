package models;

import java.math.BigDecimal;

abstract class LivingEntity {

    private BigDecimal worldX, worldY;
    private BigDecimal speed;
    private String direction;

    public void setDefaultValues(int worldX, int worldY, int speed, String direction) {
        this.worldX = BigDecimal.valueOf(worldX);
        this.worldY = BigDecimal.valueOf(worldY);
        this.speed = BigDecimal.valueOf(speed);
        this.direction = direction;
    }
    public BigDecimal getWorldX() { return worldX; }
    public void setWorldX(int worldX) { this.worldX = BigDecimal.valueOf(worldX); }
    public BigDecimal getWorldY() { return worldY; }
    public void setWorldY(int worldY) { this.worldY = BigDecimal.valueOf(worldY); }

    //Entity movement
    public void moveUp() {
        worldY = worldY.subtract(speed);
    }
    public void moveDown() {
        worldY = worldY.add(speed);
    }
    public void moveRight() {
        worldX = worldX.subtract(speed);
    }
    public void moveLeft() {
        worldX = worldX.add(speed);
    }
    public BigDecimal getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) { this.speed = BigDecimal.valueOf(speed); }
    public String getDirection() {
        return direction;
    }
    public void faceDirection(String direction) {
        this.direction = direction;
    }


}
