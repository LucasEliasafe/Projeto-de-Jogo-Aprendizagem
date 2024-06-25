import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
    private int x, y, width, height;
    private int xVelocity, yVelocity;
    private final int INITIAL_SPEED = 5;
    private final int SPEED_INCREMENT = 1;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xVelocity = INITIAL_SPEED;
        this.yVelocity = INITIAL_SPEED;
    }

    public void move(Paddle leftPaddle, Paddle rightPaddle) {
        x += xVelocity;
        y += yVelocity;

        if (y <= 0 || y >= 600 - height) {
            yVelocity = -yVelocity;
        }

        // Verificar colisão com o paddle esquerdo
        if (getBounds().intersects(leftPaddle.getBounds())) {
            xVelocity = Math.abs(xVelocity);  // Garantir que a bola se mova para a direita
            x = leftPaddle.getX() + leftPaddle.getWidth();  // Empurrar a bola para fora do paddle
        }

        // Verificar colisão com o paddle direito
        if (getBounds().intersects(rightPaddle.getBounds())) {
            xVelocity = -Math.abs(xVelocity);  // Garantir que a bola se mova para a esquerda
            x = rightPaddle.getX() - width;  // Empurrar a bola para fora do paddle
        }

        if (x <= 0 || x >= 800 - width) {
            reset();
        }
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void increaseSpeed() {
        if (xVelocity > 0) {
            xVelocity += SPEED_INCREMENT;
        } else {
            xVelocity -= SPEED_INCREMENT;
        }

        if (yVelocity > 0) {
            yVelocity += SPEED_INCREMENT;
        } else {
            yVelocity -= SPEED_INCREMENT;
        }
    }

    public void reset() {
        x = 400 - width / 2;
        y = 300 - height / 2;
        xVelocity = INITIAL_SPEED;
        yVelocity = INITIAL_SPEED;
    }
}