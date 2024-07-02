import java.awt.Graphics;
import java.awt.Rectangle;

public class AutoPaddle {
    private int x, y, width, height;
    private int yVelocity;
    private int speed = 5;

    public AutoPaddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.yVelocity = 0;
        this.speed = 5;
    }

    public void move(Ball ball) {
        int ballY = ball.getY();
        int ballCenterY = ball.getY() + ball.getHeight() / 2;
        int paddleCenterY = y + height / 2;

        if (ballCenterY < paddleCenterY) {
            y -= speed;
        } else if (ballCenterY > paddleCenterY) {
            y += speed;
        }

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }
}