import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
    private int x, y, width, height;
    private int yVelocity;
    private final int SPEED = 5;
    private boolean up, down;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.yVelocity = 0;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void move() {
        if (up) {
            yVelocity = -SPEED;
        } else if (down) {
            yVelocity = SPEED;
        } else {
            yVelocity = 0;
        }
        y += yVelocity;
        if (y < 0) {
            y = 0;
        } else if (y + height > 600) {
            y = 600 - height;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}