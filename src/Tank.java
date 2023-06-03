import java.awt.*;
import java.util.ArrayList;

public abstract class Tank extends GameObject {
    private final String upImg;
    private final String rightImg;
    private final String leftImg;
    private final String downImg;
    int width = 32;
    int height = 32;
    int speed = 3;
    boolean up, left, right, down;
    boolean alive = true;
    Direction direction = Direction.UP;

    public Tank(String img, int x, int y, GamePanel gamePanel, String upImg, String rightImg, String leftImg, String downImg) {
        super(img, x, y, gamePanel);
        this.upImg = upImg;
        this.downImg = downImg;
        this.leftImg = leftImg;
        this.rightImg = rightImg;
    }

    public void upward() {
        if (y >= 32 && !hitWall(x, y - speed)) y -= speed;
        setImg(upImg);
        direction = Direction.UP;
    }

    public void downward() {
        if (y <= 512 && !hitWall(x, y + speed)) y += speed;
        setImg(downImg);
        direction = Direction.DOWN;
    }

    public void rightward() {
        if (x <= 470 && !hitWall(x + speed, y)) x += speed;
        setImg(rightImg);
        direction = Direction.RIGHT;
    }

    public void leftward() {
        if (x >= 12 && !hitWall(x - speed, y)) x -= speed;
        setImg(leftImg);
        direction = Direction.LEFT;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public void attck() {
        if (alive) {
            Point p = this.getHeadPoint();
            this.gamePanel.bullets.add(new Bullet("img/bulletD.png", p.x, p.y, this.gamePanel, this.direction, "img/bulletU.png", "img/bulletR.png", "img/bulletL.png", "img/bulletD.png"));
        }
    }

    public Point getHeadPoint() {
        switch (direction) {
            case UP:
                return new Point(x + width / 2 - 4, y);
            case DOWN:
                return new Point(x + width / 2 - 4, y + height);
            case LEFT:
                return new Point(x, y + height / 2 - 4);
            case RIGHT:
                return new Point(x + width, y + height / 2 - 4);
        }
        return null;
    }

    public boolean hitWall(int x, int y) {
        ArrayList<Wall> walls = this.gamePanel.walls;
        ArrayList<Steel> steels = this.gamePanel.steels;
        ArrayList<Tank> players = this.gamePanel.players;
        Rectangle next = new Rectangle(x, y, width, height);
        for (Wall wall : walls) {
            if (next.intersects(wall.getRec())) return true;
        }
        for (Steel steel : steels) {
            if (next.intersects(steel.getRec())) return true;
        }
        return false;
    }

    @Override
    public abstract void paintSelf(Graphics g);

    @Override
    public abstract Rectangle getRec();
}