import java.awt.*;
import java.awt.event.*;


public class One extends Frame implements ActionListener {
    Button b1,b2;
    TextField t1;

    One(){
        b1=new Button("Hello");
        b2=new Button(" World!");
        setSize(500,500);
        GridLayout l=new GridLayout(3,1);
        setLayout(l);   
        add(b1);
        add(b2);
        t1=new TextField("");
        add(t1);

        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent a){
        if(a.getSource()==b1){
            t1.setText("Hello");
        }
        else {
            t1.setText("World");
        }
    }


    public static void main(String[] args) {
        new One();
    }
}
