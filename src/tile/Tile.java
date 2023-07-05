package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    private boolean collision = false;
    public void setCollision() { this.collision = true; }
    public boolean isColliding() {
        return collision;
    }
}
