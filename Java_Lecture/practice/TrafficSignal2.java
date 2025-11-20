import java.awt.*;
import java.awt.event.*;

public class TrafficSignal2 extends Frame implements Runnable, ActionListener {
    Thread t;
    boolean running = false;
    String currentColor = "RED"; // Initial color

    Button startBtn, stopBtn;

    public TrafficSignal2() {
        setTitle("Traffic Signal Simulation");
        setSize(300, 400);
        setLayout(null);

        startBtn = new Button("START");
        stopBtn = new Button("STOP");

        startBtn.setBounds(50, 300, 80, 30);
        stopBtn.setBounds(150, 300, 80, 30);

        add(startBtn);
        add(stopBtn);

        startBtn.addActionListener(this);
        stopBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(100, 80, 100, 220); // signal box

        // Red Light
        if (currentColor.equals("RED")) g.setColor(Color.RED);
        else g.setColor(Color.GRAY);
        g.fillOval(125, 100, 50, 50);

        // Yellow Light
        if (currentColor.equals("YELLOW")) g.setColor(Color.YELLOW);
        else g.setColor(Color.GRAY);
        g.fillOval(125, 170, 50, 50);

        // Green Light
        if (currentColor.equals("GREEN")) g.setColor(Color.GREEN);
        else g.setColor(Color.GRAY);
        g.fillOval(125, 240, 50, 50);
    }

    public void run() {
        try {
            while (running) {
                currentColor = "RED";
                repaint();
                Thread.sleep(3000);

                currentColor = "YELLOW";
                repaint();
                Thread.sleep(1000);

                currentColor = "GREEN";
                repaint();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            if (t == null || !running) {
                running = true;
                t = new Thread(this);
                t.start();
            }
        } else if (e.getSource() == stopBtn) {
            running = false;
        }
    }

    public static void main(String[] args) {
        new TrafficSignal2();
    }
}