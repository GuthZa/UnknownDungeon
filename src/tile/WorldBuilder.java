package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class WorldBuilder {

    GamePanel gamePanel;
    private int[][] map;

    private static final Tile[] TILES = new Tile[10];

    public WorldBuilder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        map = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        loadTiles();
        loadMap("resources/maps/world01.txt");
    }
    private void loadTiles() {
        try {
            TILES[0].setImage(ImageIO.read(new File("resources/tiles/grass00.png")));
            TILES[1].setImage(ImageIO.read(new FileInputStream("resources/tiles/road00.png")));
            TILES[2].setImage(ImageIO.read(new FileInputStream("resources/tiles/tree.png")));
            TILES[2].setCollisionTrue();
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

            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();

            int screenX = worldX - gamePanel.getPlayer().getScreenX() + gamePanel.getPlayer().getWorldX();
            int screenY = worldY - gamePanel.getPlayer().getScreenY() + gamePanel.getPlayer().getWorldY();

            /*




             */

            if(worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
            ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }
    public Tile getTile(int index) {
        return index > 0 && index <= TILES.length ? TILES[index] : null;
    }

    public boolean getTileCollision(int index) {
        return index > 0 && index <= TILES.length && TILES[index].isColliding();
    }
}
