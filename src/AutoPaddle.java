import java.awt.Graphics;
import java.awt.Rectangle;



public class AutoPaddle {
    private int x, y, width, height;
    private int yVelocity;
    private final int speed = 5;

    public AutoPaddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        yVelocity = speed;
    }

    public void move () {
        y += yVelocity;
        if(y < 0 || y + height > 600) {
            yVelocity = -yVelocity;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
