package main;

import engine.KeyHandler;
import models.Enemy;
import models.Player;
import tile.WorldBuilder;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //TODO use a yaml file for global properties

    /*
    TODO
        change public finals to private and make getters
        changed private finals to caps
     */

    //Screen settings
    private final int originalTileSize = 16; //Sprite size
    private final int scale = 3; //scale to resolution

    private final int tileSize = originalTileSize * scale; //48x48 pixel size
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    //16 by 12 of 48x48 pixel sprites
    //Resolution = 768x576
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    int FPS = 60;

    //System
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    WorldBuilder worldBuilder = new WorldBuilder(this);

    //Entinties
    private Player player = new Player(this, keyHandler);
    private Enemy enemy = new Enemy(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); //improves performance by drawing off-screen

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS; //draws the screen every 0.16666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread!=null) {

            currentTime = System.nanoTime(); //returns time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                //Update information
                update();

                //Draw the screen with update information
                repaint(); //calls the painComponent
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
        enemy.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        enemy.draw(g2);

        g2.dispose();
    }

    public Player getPlayer() {
        return player;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getTileSize() {
        return tileSize;
    }
}
