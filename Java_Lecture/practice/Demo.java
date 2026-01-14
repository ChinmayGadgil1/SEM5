import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class Demo {

 // Main Method
 public static void main(String[] args)
 {

     // Function to set up the window frame.
    //  JFrame.setDefaultLookAndFeelDecorated(false);
     // Creating Object of "JFrame" class
     JFrame frame = new JFrame("BoxLayout Example X_AXIS");
     // Declaration of objects of JButton class.
     JButton jbtn1, jbtn2, jbtn3, jbtn4, jbtn5;
     // Function to set the default
     // close operation of JFrame the.
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // Set the panel to add buttons
     JPanel panel = new JPanel();
     // Creating Object of "boxlayout" in 
     // X_Axis from left to right
     BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
     // to set the box layout
     panel.setLayout(boxlayout);
     // Set border for the panel
    //  panel.setBorder(new EmptyBorder(new Insets(100, 150, 100, 150)));

     // Initialization of object "jb1" of JButton class.
     jbtn1 = new JButton("Button 1");

     // Initialization of object "jb2" of JButton class.
     jbtn2 = new JButton("Button 2");

     // Initialization of object "jb3" of JButton class.
     jbtn3 = new JButton("Button 3");

     // Initialization of object "jb4" of JButton class.
     jbtn4 = new JButton("Button 4");

     // Initialization of object "jb5" of JButton class.
     jbtn5 = new JButton("Button 5");

     // Adding JButton "jb1" on JFrame
     panel.add(jbtn1);

     // Adding JButton "jb2" on JFrame
     panel.add(jbtn2);

     // Adding JButton "jb3" on JFrame
     panel.add(jbtn3);

     // Adding JButton "jb4" on JFrame
     panel.add(jbtn4);

     // Adding JButton "jb5" on JFrame
     panel.add(jbtn5);

     // Function to set the panel  of JFrame.
     frame.add(panel);

     // Function to use the  pack of JFrame.
     frame.pack();

     // Function to set visible status of JFrame.
     frame.setVisible(true);
 }
}