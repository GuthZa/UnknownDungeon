package tile;

import lombok.Getter;
import main.GamePanel;
import models.LivingEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class WorldBuilder {
    private final GamePanel gamePanel;
    private final Tile[] tile;
    private final int[][] map;

    public WorldBuilder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        map = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        loadTilesImage();
        loadMap("resources/maps/world01.txt");
    }
    private void loadTilesImage() {
        try {
            tile[0] = new Tile(ImageIO.read(new File("resources/tiles/grass00.png")), false, null);

            tile[1] = new Tile(ImageIO.read(new File("resources/tiles/wall.png")), true, new Rectangle(0, 0, gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue()));

            tile[2] = new Tile(ImageIO.read(new File("resources/tiles/water01.png")),true, new Rectangle(0, 0, gamePanel.getTileSize().intValue(), gamePanel.getTileSize().intValue()));;

            tile[3] = new Tile(ImageIO.read(new File("resources/tiles/earth.png")), false, null);

            tile[4] = new Tile(ImageIO.read(new File("resources/tiles/tree.png")), true, new Rectangle(16, 18, 16, 30));

            tile[5] = new Tile(ImageIO.read(new File("resources/tiles/road00.png")), false, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            int col = 0, row = 0;

            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
                String line = bufferedReader.readLine();
                while (col < gamePanel.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    map[col][row] = num;
                    col++;
                }
                if(col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {
            int tileNum = map[worldCol][worldRow];

            BigDecimal worldX = BigDecimal.valueOf(worldCol).multiply(gamePanel.getTileSize());
            BigDecimal worldY = BigDecimal.valueOf(worldRow).multiply(gamePanel.getTileSize());

            BigDecimal screenX = worldX.subtract(gamePanel.getPlayer().getWorldX()).add(gamePanel.getPlayer().getScreenX());

            BigDecimal screenY = worldY.subtract(gamePanel.getPlayer().getWorldY()).add(gamePanel.getPlayer().getScreenY());

            if(
            worldX.add(gamePanel.getTileSize())
                    .compareTo(gamePanel.getPlayer().getWorldX()
                            .subtract(gamePanel.getPlayer().getScreenX())) > 0 &&
            worldX.subtract(gamePanel.getTileSize())
                    .compareTo(gamePanel.getPlayer().getWorldX()
                            .add(gamePanel.getPlayer().getScreenX())) < 0 &&
            worldY.add(gamePanel.getTileSize())
                    .compareTo(gamePanel.getPlayer().getWorldY()
                            .subtract(gamePanel.getPlayer().getScreenY())) > 0 &&
            worldY.subtract(gamePanel.getTileSize())
                    .compareTo(gamePanel.getPlayer().getWorldY()
                            .add(gamePanel.getPlayer().getScreenY())) < 0
            ) {
                int tileSize = gamePanel.getTileSize().intValue();
                g2.drawImage(tile[tileNum].getImage(), screenX.intValue(), screenY.intValue(), tileSize, tileSize, null);
            }
            worldCol++;

            if(worldCol == gamePanel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile getTile(int index) {
        return tile[index];
    }

    public int getTileTypeAtPos(BigDecimal x, BigDecimal y) {
        return map[x.intValue()][y.intValue()];
    }
}
