import java.awt.*;
import java.util.Random;

public class Bot extends Tank {
    int move = 0;

    public Bot(String img, int x, int y, GamePanel gamePanel, String upImg, String rightImg, String leftImg, String downImg) {
        super(img, x, y, gamePanel, upImg, rightImg, leftImg, downImg);
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.LEFT;
        }
        return null;
    }

    public void go() {
        attack();
        if (move >= 20) {
            direction = getRandomDirection();
            move = 0;
        } else {
            move++;
        }
        switch (direction) {
            case RIGHT:
                rightward();
                break;
            case LEFT:
                leftward();
                break;
            case DOWN:
                downward();
                break;
            case UP:
                upward();
                break;
        }
    }

    public void attack() {
        Point p = getHeadPoint();
        Random r = new Random();
        int pr = r.nextInt(100);
        if (pr < 3) {
            this.gamePanel.bullets.add(new BotBullet("img/bulletD.png", p.x, p.y, this.gamePanel, this.direction, "img/bulletU.png", "img/bulletR.png", "img/bulletL.png", "img/bulletD.png"));
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}