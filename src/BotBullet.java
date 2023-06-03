import java.awt.*;
import java.util.ArrayList;

public class BotBullet extends Bullet {
    public BotBullet(String img, int x, int y, GamePanel gamePanel, Direction direction, String upImg, String rightImg, String leftImg, String downImg) {
        super(img, x, y, gamePanel, direction, upImg, rightImg, leftImg, downImg);
    }

    public void hitPlayer() {
        ArrayList<Tank> playerList = this.gamePanel.players;
        for (Tank player : playerList) {
            if (this.getRec().intersects(player.getRec())) {
                this.gamePanel.blasts.add(new Blast("", player.x, player.y, this.gamePanel));
                this.gamePanel.players.remove(player);
                this.gamePanel.removeList.add(this);
                player.alive = false;
                break;
            }
        }
    }

    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        this.go();
        this.hitPlayer();
        this.hitBase();
        this.removeBorder();
    }

    public Rectangle getRec() {
        return super.getRec();
    }
}
