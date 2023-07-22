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
    private boolean interactPressed;
    private boolean backPressed;
    private boolean inventoryPressed = false;

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            upPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
            downPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = true;
        else if(e.getKeyCode() == KeyEvent.VK_J)
            interactPressed = true;
        else if(e.getKeyCode() == KeyEvent.VK_K)
            backPressed = true;
        else if(e.getKeyCode() == KeyEvent.VK_I)
            inventoryPressed = !inventoryPressed;
     }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
            upPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
            downPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = false;
        else if(e.getKeyCode() == KeyEvent.VK_J)
            interactPressed = false;
        else if(e.getKeyCode() == KeyEvent.VK_K)
            backPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
