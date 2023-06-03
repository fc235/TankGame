import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GameObject {
    private final String upImg;
    private final String rightImg;
    private final String leftImg;
    private final String downImg;
    int width = 32;
    int height = 32;
    int speed = 10;
    Direction direction;

    public Bullet(String img, int x, int y, GamePanel gamePanel, Direction direction, String upImg, String rightImg, String leftImg, String downImg) {
        super(img, x, y, gamePanel);
        this.direction = direction;
        this.upImg = upImg;
        this.rightImg = rightImg;
        this.leftImg = leftImg;
        this.downImg = downImg;
    }

    public void upward() {
        y -= speed;
        setImg(upImg);
    }

    public void downward() {
        y += speed;
        setImg(downImg);
    }

    public void rightward() {
        x += speed;
        setImg(rightImg);
    }

    public void leftward() {
        x -= speed;
        setImg(leftImg);
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public void go() {
        switch (direction) {
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
        }
        this.hitWall();
        this.hitSteel();
        this.hitBase();
    }

    public void hitBot() {
        ArrayList<Bot> botList = this.gamePanel.bots;
        for (Bot bot : botList) {
            if (this.getRec().intersects(bot.getRec())) {
                this.gamePanel.blasts.add(new Blast("", bot.x, bot.y, this.gamePanel));
                this.gamePanel.bots.remove(bot);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitWall() {
        ArrayList<Wall> wall1s = this.gamePanel.walls;
        for (Wall wall : wall1s) {
            if (this.getRec().intersects(wall.getRec())) {
                this.gamePanel.walls.remove(wall);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void hitSteel() {
        ArrayList<Steel> steels = this.gamePanel.steels;
        for (Steel steel : steels) {
            if (this.getRec().intersects(steel.getRec())) {
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }

    public void removeBorder() {
        if (x < 0 || x > this.gamePanel.getWidth()) {
            this.gamePanel.removeList.add(this);
        }
        if (y < 0 || y > this.gamePanel.getHeight()) {
            this.gamePanel.removeList.add(this);
        }
    }

    public void hitBase() {
        ArrayList<Base> bases = this.gamePanel.bases;
        for (Base base : bases) {
            if (this.getRec().intersects(base.getRec())) {
                this.gamePanel.bases.remove(base);
                this.gamePanel.removeList.add(this);
                break;
            }
        }
    }


    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        this.go();
        removeBorder();
        hitBot();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
