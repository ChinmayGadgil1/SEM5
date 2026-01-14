import java.awt.*;
import java.awt.event.*;

public class AppWindow extends Frame {

	public AppWindow() {
		this.setLayout(new GridLayout(8,2));
		
		Label name = new Label("NAME:");
		Label addr = new Label("ADDRESS:");
		Label gender = new Label("GENDER:");
		Label phone = new Label("PHONE NUMBER:");
		TextField nametf = new TextField();
		TextArea addrtf = new TextArea();
		CheckboxGroup genderrb = new CheckboxGroup();
		Checkbox male = new Checkbox("MALE",genderrb,false);
		Checkbox female = new Checkbox("FEMALE",genderrb,false);
		Checkbox other = new Checkbox("OTHER",genderrb,false);
		TextField phonetf = new TextField();
		Button submit = new Button("SUBMIT");
		Button reset = new Button("RESET");
		
		this.add(name);
		this.add(nametf);
		this.add(addr);
		this.add(addrtf);
		this.add(gender);
		this.add(male);
		this.add(female);
		this.add(other);
		this.add(phone);
		this.add(phonetf);
		this.add(submit);
		this.add(reset);
		this.setSize(500,700);
		this.setVisible(true);
		 this.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      }
		);
		
		
	}

	public static void main(String[] args) {
		new AppWindow();
	}

}