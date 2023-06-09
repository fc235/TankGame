import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Tank {

    public Player(String img, int x, int y, GamePanel gamePanel, String upImg, String rightImg, String leftImg, String downImg) {
        super(img, x, y, gamePanel, upImg, rightImg, leftImg, downImg);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_SPACE:
                attck();
                break;
        }
    }

    /*    public boolean hitBot(int x, int y){
            ArrayList<Bot> bots = this.gamePanel.bots;
            Rectangle next = new Rectangle(x, y, width, height);
            for (Bot bot :bots){
                if (next.intersects(bot.getRec())) return true;
            }
            return false;
        }*/
    public void move() {
        if (left) {
            leftward();
        } else if (right) {
            rightward();
        } else if (up) {
            upward();
        } else if (down) {
            downward();
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        move();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
