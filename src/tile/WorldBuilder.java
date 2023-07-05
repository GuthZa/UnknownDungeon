package tile;

import lombok.Getter;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;

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
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("resources/tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("resources/tiles/wall.png"));
            tile[1].setCollision();

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("resources/tiles/water01.png"));
            tile[2].setCollision();

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("resources/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("resources/tiles/tree.png"));
            tile[4].setCollision();

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("resources/tiles/road00.png"));
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
        int worldCol = 0, worldRow = 0;

        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {
            int tileNum = map[worldCol][worldRow];

            BigDecimal worldX = BigDecimal.valueOf(worldCol).multiply(gamePanel.getTileSize());
            BigDecimal worldY = BigDecimal.valueOf(worldRow).multiply(gamePanel.getTileSize());

            BigDecimal screenX = worldX.subtract(gamePanel.getPlayer().getWorldX()).add(gamePanel.getPlayer().getScreenX());

            BigDecimal screenY = worldY.subtract(gamePanel.getPlayer().getWorldY()).add(gamePanel.getPlayer().getScreenY());

            if(
            worldX.add(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().subtract(gamePanel.getPlayer().getScreenX())) > 0 &&
            worldX.subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldX().add(gamePanel.getPlayer().getScreenX())) < 0 &&
            worldY.add(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldY().subtract(gamePanel.getPlayer().getScreenY())) > 0 &&
            worldY.subtract(gamePanel.getTileSize()).compareTo(gamePanel.getPlayer().getWorldY().add(gamePanel.getPlayer().getScreenY())) < 0
            ) {
                int tileSize = gamePanel.getTileSize().intValue();
                g2.drawImage(tile[tileNum].image, screenX.intValue(), screenY.intValue(), tileSize, tileSize, null);
            }
            worldCol++;

            if(worldCol == gamePanel.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public Tile getTile(int index) {
        return index > 0 && index <= tile.length ? tile[index] : null;
    }

    public boolean getTileCollision(int index) {
        return index > 0 && index <= tile.length && tile[index].isColliding();
    }
}
