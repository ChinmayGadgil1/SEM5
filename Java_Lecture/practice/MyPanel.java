import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyPanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame( "Frame with panel");
		Panel panel = new Panel(  );
		frame.resize(200, 200); 
		frame.setBackground (Color.blue);
		frame.setLayout (null); 		// override default layout 
		panel.resize (100, 100) ; 
		panel.setBackground (Color.yellow );
		frame.add (panel); 		//  add the panel into the frame 
		frame.show ( );			//   display the panel
		frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });  

	}

}