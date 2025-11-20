import java.awt.*;
import java.awt.event.*;

public class AwtLayoutDemo {
   private Frame mainFrame;
   private Label headerLabel;
   private Label statusLabel;
   private Panel controlPanel;
   private Label msglabel;

   public AwtLayoutDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      AwtLayoutDemo  awtLayoutDemo = new AwtLayoutDemo();  
      awtLayoutDemo.showBorderLayoutDemo();       
   }
      
   private void prepareGUI(){
      mainFrame = new Frame("Java AWT Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new Label();
      headerLabel.setAlignment(Label.CENTER);
      statusLabel = new Label();        
      statusLabel.setAlignment(Label.CENTER);
      statusLabel.setSize(350,100);

      msglabel = new Label();
      msglabel.setAlignment(Label.CENTER);
      msglabel.setText("Welcome to TutorialsPoint AWT Tutorial.");

      controlPanel = new Panel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void showBorderLayoutDemo(){
      headerLabel.setText("Layout in action: BorderLayout");      

      Panel panel = new Panel();
      panel.setBackground(Color.darkGray);
      panel.setSize(300,300);
      BorderLayout layout = new BorderLayout();
      layout.setHgap(10);
      layout.setVgap(10);
      panel.setLayout(layout);        
	  
      panel.add(new Button("Center"),BorderLayout.CENTER);
      panel.add(new Button("Line Start"),BorderLayout.LINE_START); 
      panel.add(new Button("Line End"),BorderLayout.LINE_END);
      panel.add(new Button("East"),BorderLayout.EAST);   
      panel.add(new Button("West"),BorderLayout.WEST); 
      panel.add(new Button("North"),BorderLayout.NORTH); 
      panel.add(new Button("South"),BorderLayout.SOUTH); 

      controlPanel.add(panel);

      mainFrame.setVisible(true);  
   }
}