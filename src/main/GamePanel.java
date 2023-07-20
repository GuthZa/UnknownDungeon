package main;

import engine.CollisionManager;
import engine.KeyHandler;
import lombok.*;
import models.LivingEntity;
import models.Player;
import objects.Object;
import objects.ObjectManager;
import tile.WorldBuilder;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private static final int ORIGINAL_TILE_SIZE = 16; //Sprite size
    private static final int SCALE = 3; //scale to resolution

    private final BigDecimal tileSize = BigDecimal.valueOf(ORIGINAL_TILE_SIZE * SCALE); //48x48 pixel size
    private static final int MAX_SCREEN_COL = 16;
    private static final int MAX_SCREEN_ROW = 12;
    //16 by 12 of 48x48 pixel sprites
    //Resolution = 768x576
    private final int screenWidth = tileSize.intValue() * MAX_SCREEN_COL;
    private final int screenHeight = tileSize.intValue() * MAX_SCREEN_ROW;

    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    public final BigDecimal worldWidth = BigDecimal.valueOf((long) tileSize.intValue() * MAX_SCREEN_COL);
    public final BigDecimal worldHeight = BigDecimal.valueOf((long) tileSize.intValue() * MAX_SCREEN_ROW);

    //System
    private KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;
    private WorldBuilder worldBuilder = new WorldBuilder(this);
    private ObjectManager objectManager = new ObjectManager(this);
    private CollisionManager collisionManager = new CollisionManager(this);

    //Entities
    private final Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
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
        //Manage Player Movement
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        worldBuilder.draw(g2);
        objectManager.draw(g2);
        player.draw(g2);

        g2.setColor(Color.red);

        g2.fillRect(
                player.getScreenX().add(BigDecimal.valueOf(8)).intValue(),
                player.getScreenY().add(BigDecimal.valueOf(16)).intValue(),
                (int) player.getCollisionArea().getWidth(),
                (int) player.getCollisionArea().getHeight()
        );
        g2.dispose();
    }
}
