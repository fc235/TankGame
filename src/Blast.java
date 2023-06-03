import java.awt.*;

public class Blast extends GameObject {
    static Image[] images = new Image[7];

    static {
        for (int i = 0; i < 7; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("img/blast" + (i + 1) + ".png");
        }
    }

    int index = 0;

    public Blast(String img, int x, int y, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {
        if (index < 7 ){
            g.drawImage(images[index],x,y,null);
            index++;
        }

    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
