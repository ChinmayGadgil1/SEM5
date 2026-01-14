import java.awt.*;
import java.awt.event.*;

class One extends Frame implements ActionListener {
    TextField t1, t2, t3;
    Button b;

    One() {
        setLayout(new FlowLayout());

        t1 = new TextField(10);
        t2 = new TextField(10);
        t3 = new TextField(10);
        b = new Button("Add");

        add(t1); add(t2); add(b); add(t3);

        b.addActionListener(this);
        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int a = Integer.parseInt(t1.getText());
        int b = Integer.parseInt(t2.getText());
        t3.setText(String.valueOf(a + b));
    }

    public static void main(String[] args) {
        new One();
    }
}
