package panels;

import javax.swing.*;
import java.awt.*;

public class GameOverText extends JPanel {
	private static final long serialVersionUID = 1L;
	private final String text;
	private static String score = "";
	
    public GameOverText() {
        text = "Game Over";
        setBackground(new Color(0, 0, 0));
    }

    public static void setScore(String _score) {
    	score = "You scored " +  _score + " points";
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 720);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int x = ((getWidth() - fm.stringWidth(text)) / 2);
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y - 25);
        g2d.drawString(score, x - 100, y + 25);
        g2d.dispose();
    }
}
