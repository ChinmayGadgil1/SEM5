import java.awt.*;
import javax.swing.*;// For setting colors

    public class GraphicsDemo extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Always call super.paintComponent() first
            
            // Now you can use the 'g' (Graphics) object to draw
            g.setColor(Color.RED);
            g.fillRect(50, 50, 100, 75); // Draw a filled red rectangle

            g.setColor(Color.BLUE);
            g.drawOval(200, 100, 80, 80); // Draw a blue oval outline

            g.setColor(Color.GREEN);
            g.drawString("Hello Graphics!", 100, 200); // Draw text
        }
        
        public static void main(String args[]) {
        	new GraphicsDemo();
        }
    }