import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JFrame {
    int width = 512;
    int height = 551;
    //贴图大小
    int SIZE = 32;
    int state, a = 1;
    int y = 420 - 28;
    int count, botCount = 0;
    Image select = Toolkit.getDefaultToolkit().getImage("img/p1tankR2.png");
    Image offScreemImage = null;
    Player player = new Player("img/p1Umove.gif", 5 * SIZE, 16 * SIZE, this, "img/p1Umove.gif", "img/p1Rmove.gif", "img/p1Lmove.gif", "img/p1Dmove.gif");
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Bot> bots = new ArrayList<>();
    ArrayList<Bullet> removeList = new ArrayList<>();
    ArrayList<Tank> players = new ArrayList<>();
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Steel> steels = new ArrayList<>();
    ArrayList<Base> bases = new ArrayList<>();
    ArrayList<Blast> blasts = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        GamePanel gp = new GamePanel();
        gp.createWindow();
    }

    public void createWindow() throws InterruptedException {
        setTitle("坦克大战");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addKeyListener(new GamePanel.KeyMonitor());
        for (int i = 0; i < 7; i++) {
            walls.add(new Wall("img/wall.png", 2 * SIZE, (i + 3) * SIZE, this));
        }
        for (int i = 0; i < 7; i++) {
            walls.add(new Wall("img/wall.png", 12 * SIZE, (i + 3) * SIZE, this));
        }
        for (int i = 0; i < 5; i++) {
            walls.add(new Wall("img/wall.png", (i + 5) * SIZE, 3 * SIZE, this));
        }
        for (int i = 0; i < 5; i++) {
            walls.add(new Wall("img/wall.png", (i + 5) * SIZE, 11 * SIZE, this));
        }
        for (int i = 0; i < 5; i++) {
            steels.add(new Steel("img/steel2.png", (i + 5) * SIZE, 7 * SIZE, this));
        }
        walls.add(new Wall("img/wall.png", 6 * SIZE, 16 * SIZE, this));
        walls.add(new Wall("img/wall.png", 6 * SIZE, 15 * SIZE, this));
        walls.add(new Wall("img/wall.png", 7 * SIZE, 15 * SIZE, this));
        walls.add(new Wall("img/wall.png", 8 * SIZE, 15 * SIZE, this));
        walls.add(new Wall("img/wall.png", 8 * SIZE, 16 * SIZE, this));
        bases.add(new Base("img/home.png", 7 * SIZE, 16 * SIZE, this));

        while (true) {
            if (bots.size() == 0 && botCount == 10) {
                state = 3;
            }
            if ((players.size() == 0 && (state == 1 || state == 2)) || bases.size() == 0) {
                state = 4;
            }
            if (count % 100 == 1 && botCount < 10) {
                Random randomX = new Random();
                int rx = randomX.nextInt(458) + 12;
                bots.add(new Bot("img/enemy3Dmove.gif", rx, 32, this, "img/enemy3Umove.gif", "img/enemy3Rmove.gif", "img/enemy3Lmove.gif", "img/enemy3Dmove.gif"));
                botCount++;
            }
            repaint();
            Thread.sleep(25);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offScreemImage == null) {
            offScreemImage = this.createImage(width, height);
        }
        Graphics graphics = offScreemImage.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("宋体", Font.BOLD, 32));
        if (state == 0) {
            //graphics.drawImage(Toolkit.getDefaultToolkit().getImage("img/start.png"),0,0,null );
            graphics.drawString("开始游戏", 180, 420);
            graphics.drawString("退出游戏", 180, 500);
            graphics.drawImage(select, 148, y, null);
        } else if (state == 1) {
            for (Tank player : players) {
                player.paintSelf(graphics);
            }
            for (Bullet bullet : bullets) {
                bullet.paintSelf(graphics);
            }
            bullets.removeAll(removeList);
            for (Bot bot : bots) {
                bot.paintSelf(graphics);
            }
            for (Wall wall : walls) {
                wall.paintSelf(graphics);
            }
            for (Steel steel : steels) {
                steel.paintSelf(graphics);
            }
            for (Base base : bases) {
                base.paintSelf(graphics);
            }
            for (Blast blast : blasts) {
                blast.paintSelf(graphics);
            }
            count++;
        } else if (state == 2) {
            System.exit(0);
        } else if (state == 3) {
            graphics.drawString("游戏胜利", 180, 200);
        } else if (state == 4) {
            graphics.drawString("游戏失败", 180, 200);
            graphics.drawImage(Toolkit.getDefaultToolkit().getImage("img/over.gif"), 200, 300, null);
        }
        g.drawImage(offScreemImage, 0, 0, null);
    }


    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    a = 1;
                    y = 420 - 28;
                    break;
                case KeyEvent.VK_DOWN:
                    a = 2;
                    y = 500 - 28;
                    break;
                case KeyEvent.VK_ENTER:
                    state = a;
                    players.add(player);
                    player.alive = true;
                    break;
                default:
                    player.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
