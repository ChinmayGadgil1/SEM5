import java.awt.*;
public class Five extends Frame {

    Five(){
        setSize(500,500);
        setVisible(true);
    }

    public void paint(Graphics g) {
        // Draw cone
        g.setColor(Color.RED);
        g.drawLine(100, 50, 50, 150);  // Left side
        g.drawLine(100, 50, 150, 150); // Right side
        g.drawOval(50, 140, 100, 20);  // Base
        
        // Draw cylinder
        g.setColor(Color.BLUE);
        g.drawOval(200, 50, 100, 20);   // Top
        g.drawOval(200, 150, 100, 20);  // Bottom
        g.drawLine(200, 60, 200, 160);  // Left side
        g.drawLine(300, 60, 300, 160);  // Right side

        g.drawArc(300,50,100,100,0,180);
    }
    public static void main(String[] args) {
        new Five();
    }
}