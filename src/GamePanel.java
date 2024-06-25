import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 100;
    private final int BALL_SIZE = 20;
    private Paddle leftPaddle, rightPaddle;
    private Ball ball;
    private Timer gameTimer, speedIncreaseTimer, paddleSpeedIncreaseTimer;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLUE);

        leftPaddle = new Paddle(10, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Paddle(WIDTH - 20, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Ball(WIDTH / 2 - BALL_SIZE / 2, HEIGHT / 2 - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);

        gameTimer = new Timer(16, this);
        speedIncreaseTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.increaseSpeed();
            }
        });

        paddleSpeedIncreaseTimer = new Timer (5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPaddle.increaseSpeed();
                rightPaddle.increaseSpeed();
            }
        });

        addKeyListener(this);
        setFocusable(true);
    }

    public void startGame() {
        gameTimer.start();
        speedIncreaseTimer.start();
        paddleSpeedIncreaseTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        ball.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        leftPaddle.move();
        rightPaddle.move();
        ball.move(leftPaddle, rightPaddle);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            leftPaddle.setUp(true);
        } else if (key == KeyEvent.VK_S) {
            leftPaddle.setDown(true);
        } else if (key == KeyEvent.VK_UP) {
            rightPaddle.setUp(true);
        } else if (key == KeyEvent.VK_DOWN) {
            rightPaddle.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            leftPaddle.setUp(false);
        } else if (key == KeyEvent.VK_S) {
            leftPaddle.setDown(false);
        } else if (key == KeyEvent.VK_UP) {
            rightPaddle.setUp(false);
        } else if (key == KeyEvent.VK_DOWN) {
            rightPaddle.setDown(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}