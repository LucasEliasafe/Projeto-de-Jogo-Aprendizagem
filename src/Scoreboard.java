import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Scoreboard {
    private int player1Score;
    private int player2Score;

    public Scoreboard() {
        reset();
    }

    public void draw(Graphics g, int width, int height) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 36));


        g.drawString(String.valueOf(player1Score), width / 4, 50);
        g.drawString(String.valueOf(player2Score), 3 * width / 4, 50);
    }

    public void player1Scores() {
        player1Score++;
    }

    public void player2Scores() {
        player2Score++;
    }

    public void reset() {
        player1Score = 0;
        player2Score = 0;
    }
}