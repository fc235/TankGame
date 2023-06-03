import java.awt.*;

public abstract class GameObject {
    Image img;
    int x, y;
    GamePanel gamePanel;

    public GameObject(String img, int x, int y, GamePanel gamePanel) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public abstract void paintSelf(Graphics g);

    public abstract Rectangle getRec();
}
