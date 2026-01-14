import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Three extends Frame implements ActionListener,Runnable {
    Thread t;
    int turn=0;
    public Three(){
        setSize(500,500);
        setVisible(true);
        t = new Thread(this);
        t.start();
    }


    public void paint(Graphics g){

        if(turn==0) {
            g.setColor(Color.green);
        }else{
            g.setColor(Color.BLACK);
        }
        g.fillOval(20,50,50,50);

        if(turn==1) {
            g.setColor(Color.YELLOW);
        }else{
            g.setColor(Color.BLACK);
        }
        g.fillOval(20,130,50,50);
        if(turn==2) {
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.BLACK);
        }
        g.fillOval(20,210,50,50);
        
    }


    public void run(){
        try{
            while (true) {
                turn=0;
                repaint();
                Thread.sleep(1000);
                turn=1;
                repaint();
                Thread.sleep(1000);
                turn=2;
                repaint();
                Thread.sleep(1000);
            }
        }
        catch(Exception e){
            System.out.println("bfsjfbwei");
        }
    }
    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args) {
        new Three();
    }
}
