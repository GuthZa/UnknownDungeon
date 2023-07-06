package main;

import engine.CollisionManager;
import engine.KeyHandler;
import lombok.*;
import models.Player;
import objects.AssetSetter;
import objects.Object;
import objects.ObjectManager;
import tile.WorldBuilder;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

@Getter
@Setter
public class GamePanel extends JPanel implements Runnable {
    private static final int FPS = 60;

    //TODO use a yaml file for global properties

    /*
    TODO
        change public finals to private and make getters
        changed private finals to caps
     */

    //Screen settings
    private static final int originalTileSize = 16; //Sprite size
    private static final int scale = 3; //scale to resolution

    private final BigDecimal tileSize = BigDecimal.valueOf(originalTileSize * scale); //48x48 pixel size
    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;
    //16 by 12 of 48x48 pixel sprites
    //Resolution = 768x576
    private final int screenWidth = tileSize.intValue() * maxScreenCol;
    private final int screenHeight = tileSize.intValue() * maxScreenRow;

    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    public final BigDecimal worldWidth = BigDecimal.valueOf((long) tileSize.intValue() * maxScreenCol);
    public final BigDecimal worldHeight = BigDecimal.valueOf((long) tileSize.intValue() * maxScreenRow);

    //System
    private KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;
    private WorldBuilder worldBuilder = new WorldBuilder(this);
    private CollisionManager collisionManager = new CollisionManager(this);
    private AssetSetter assetSetter = new AssetSetter(this);

    //Entities
    private final Player player = new Player(this, keyHandler);

    ///Objects
    private ObjectManager[] objectList = new ObjectManager[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improves performance by drawing off-screen

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        worldBuilder.draw(g2);
        player.draw(g2);

        for(ObjectManager object : objectList) {
            if(object != null) {
                object.draw(g2, this);
            }
        }

        g2.dispose();
    }
}
