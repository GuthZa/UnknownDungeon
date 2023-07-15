package engine;

import lombok.Getter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Getter
public class KeyHandler implements KeyListener {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    @Override
    public synchronized void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) upPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) downPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
     }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
