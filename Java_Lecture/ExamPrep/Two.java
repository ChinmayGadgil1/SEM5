import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Two extends JFrame {
    private JButton button;
    private JLabel label;
    private int clickCount = 0;
    
    public Two() {
        setTitle("Delegate Event Model Demo");
        setLayout(new FlowLayout());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create components
        button = new JButton("Click Me!");
        label = new JLabel("Clicks: 0");
        
        // Step 1: Create listener (using anonymous inner class)
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                label.setText("Clicks: " + clickCount);
                
                // Get event information
                System.out.println("Event Source: " + e.getSource());
                System.out.println("Action Command: " + e.getActionCommand());
                System.out.println("When: " + e.getWhen());
            }
        };
        
        // Step 2: Register listener with event source
        button.addActionListener(listener);
        
        // Add second listener (multiple listeners possible)
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Second listener: Button clicked!");
            }
        });
        
        // Add components to frame
        add(button);
        add(label);
        
        setVisible(true);
    }
    
    // Alternative: Using separate listener class
    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Button clicked from separate class!");
        }
    }
    
    public static void main(String[] args) {
        new Two();
    }
}