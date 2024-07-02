import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 100;
    private final int BALL_SIZE = 20;
    private Paddle leftPaddle, rightPaddle;
    private Ball ball;
    private Scoreboard scoreboard;
    private Timer gameTimer, speedIncreaseTimer, paddleSpeedIncreaseTimer;
    private JButton resetButton;
    private AutoPaddle autoPaddle1, autoPaddle2;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        leftPaddle = new Paddle(10, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Paddle(WIDTH - 20, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Ball(WIDTH / 2 - BALL_SIZE / 2, HEIGHT / 2 - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);
        scoreboard = new Scoreboard();

        autoPaddle1 = new AutoPaddle(WIDTH / 2 - PADDLE_WIDTH - 100, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        autoPaddle2 = new AutoPaddle(WIDTH / 2 + 100, HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);

        gameTimer = new Timer(16, this);
        speedIncreaseTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.increaseSpeed();
                leftPaddle.setSpeed(ball.getSpeed());
                rightPaddle.setSpeed(ball.getSpeed());
            }
        });

        paddleSpeedIncreaseTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPaddle.increaseSpeed();
                rightPaddle.increaseSpeed();
            }
        });

        resetButton = new JButton("Reset Score") {
            @Override
            public void paintComponent(Graphics g) {
                if (getModel().isRollover()) {
                    super.paintComponent(g);
                } else {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.f));
                    super.paintComponent(g);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
            }
        };

        resetButton.setBounds(WIDTH / 2 - 75, HEIGHT - 590, 150, 40);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreboard.reset();
                ball.reset();
                requestFocusInWindow();
                leftPaddle.setSpeed(5);
                rightPaddle.setSpeed(5);
                requestFocusInWindow();
            }
        });

        resetButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                requestFocusInWindow();
            }
        });

        setLayout(null);
        add(resetButton);

        addKeyListener(this);
        setFocusable(true);
    }

    public void startGame() {
        gameTimer.start();
        speedIncreaseTimer.start();
        paddleSpeedIncreaseTimer.start();
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        ball.draw(g);
        autoPaddle1.draw(g);
        autoPaddle2.draw(g);
        scoreboard.draw(g, WIDTH, HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        leftPaddle.move();
        rightPaddle.move();
        autoPaddle1.move(ball);
        autoPaddle2.move(ball);
        ball.move(leftPaddle, rightPaddle, autoPaddle1, autoPaddle2);

        if (ball.getX() <= 0) {
            scoreboard.player2Scores();
            ball.reset();
        } else if (ball.getX() >= WIDTH - ball.getWidth()) {
            scoreboard.player1Scores();
            ball.reset();
        }

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
        } else if (key == KeyEvent.VK_R) {
            scoreboard.reset();
            ball.reset();
            leftPaddle.setSpeed(5);
            rightPaddle.setSpeed(5);
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