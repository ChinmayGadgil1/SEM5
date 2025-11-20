import java.awt.*;
import java.awt.event.*;

public class CardLayoutDemo {
   private Frame mainFrame;
   private Label headerLabel;
   private Label statusLabel;
   private Panel controlPanel;
   private Label msglabel;

   public CardLayoutDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      CardLayoutDemo  awtLayoutDemo = new CardLayoutDemo();  
      awtLayoutDemo.showCardLayoutDemo();       
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

   private void showCardLayoutDemo(){
      headerLabel.setText("Layout in action: CardLayout");      

      final Panel panel = new Panel();
      panel.setBackground(Color.CYAN);
      panel.setSize(300,300);

      CardLayout layout = new CardLayout();
      layout.setHgap(10);
      layout.setVgap(10);
      panel.setLayout(layout);        

      Panel buttonPanel = new Panel(new FlowLayout());

      buttonPanel.add(new Button("OK"));
      buttonPanel.add(new Button("Cancel"));    

      Panel textBoxPanel = new Panel(new FlowLayout());

      textBoxPanel.add(new Label("Name:"));
      textBoxPanel.add(new TextField(20));

      panel.add("Button", buttonPanel);
      panel.add("Text", textBoxPanel);

      Choice choice = new Choice();
      choice.add("Button");
      choice.add("Text");

      choice.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            CardLayout cardLayout = (CardLayout)(panel.getLayout());
            cardLayout.show(panel, (String)e.getItem());
         }
      });
      controlPanel.add(choice);
      controlPanel.add(panel);

      mainFrame.setVisible(true);  
   }
}