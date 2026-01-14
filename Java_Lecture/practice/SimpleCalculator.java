import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    TextField t1, t2, t3;
    Button add, sub, mul, div, clear;

    public SimpleCalculator() {
        setLayout(new FlowLayout());

        // Text fields
        t1 = new TextField(10);
        t2 = new TextField(10);
        t3 = new TextField(15);
        t3.setEditable(false);

        // Buttons
        add = new Button("Add");
        sub = new Button("Sub");
        mul = new Button("Mul");
        div = new Button("Div");
        clear = new Button("Clear");

        // Add components
        add(new Label("Num 1:"));
        add(t1);
        add(new Label("Num 2:"));
        add(t2);
        add(new Label("Result:"));
        add(t3);

        add(add);
        add(sub);
        add(mul);
        add(div);
        add(clear);

        // Register listeners
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        clear.addActionListener(this);

        // Frame settings
        setTitle("Simple Calculator");
        setSize(300, 200);
        setVisible(true);

        // Window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double n1 = Double.parseDouble(t1.getText());
            double n2 = Double.parseDouble(t2.getText());
            double result = 0;

            if (e.getSource() == add) result = n1 + n2;
            else if (e.getSource() == sub) result = n1 - n2;
            else if (e.getSource() == mul) result = n1 * n2;
            else if (e.getSource() == div) result = n1 / n2;
            else if (e.getSource() == clear) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                return;
            }

            t3.setText(String.valueOf(result));
        } catch (Exception ex) {
            t3.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
