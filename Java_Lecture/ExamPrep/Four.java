import java.awt.*;
import java.awt.event.*;

public class Four extends Frame implements ActionListener {
    TextField num1;
    TextField num2;
    TextField ans;
    Button add;
    public Four(){
        setSize(500,500);
        setLayout(new GridLayout(4,1));
        num1=new TextField(4);
        num2=new TextField(4);
        add=new Button("+");
        ans=new TextField(4);

        add.addActionListener(this);

        add(num1);
        add(num2);
        add(add);
        add(ans);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==add){
            int a=Integer.parseInt(num1.getText());
            int b=Integer.parseInt(num2.getText());
            ans.setText(String.valueOf(a+b));
        }
    }

    public static void main(String[] args) {
        new Four();
    }
}
