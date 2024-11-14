import javax.swing.*;
import java.awt.*;

public class TrainDrawing extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.fillRect(50, 100, 150, 50);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(150, 60, 50, 90);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(80, 60, 20, 40);

        g2d.setColor(Color.BLACK);
        g2d.fillOval(60, 140, 30, 30);
        g2d.fillOval(100, 140, 30, 30);
        g2d.fillOval(140, 140, 30, 30);
        g2d.fillOval(180, 140, 30, 30);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(160, 70, 20, 20);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(90, 30, 20, 20);
        g2d.drawOval(110, 20, 20, 20);
        g2d.drawOval(130, 10, 20, 20);
        g2d.drawOval(150, 0, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Train Drawing");
        TrainDrawing panel = new TrainDrawing();
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
