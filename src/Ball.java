import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private int x, y, width, height;
    private int xVelocity, yVelocity;
    private final int SPEED = 5;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xVelocity = SPEED;
        this.yVelocity = SPEED;
    }

    public void move(Paddle leftPaddle, Paddle rightPaddle) {
        x += xVelocity;
        y += yVelocity;

        if (y <= 0 || y >= 600 - height) {
            yVelocity = -yVelocity;
        }

        if (getBounds().intersects(leftPaddle.getBounds()) || getBounds().intersects(rightPaddle.getBounds())) {
            xVelocity = -xVelocity;
        }

        if (x <= 0 || x >= 800 - width) {
            x = 400 - width / 2;
            y = 300 - height / 2;
            xVelocity = -xVelocity;
        }
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}