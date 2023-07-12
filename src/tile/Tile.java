package tile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Tile {
    private BufferedImage image;
    private boolean collision;

    private Rectangle collisionArea;
}
