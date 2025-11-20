import java.awt.*;
import java.awt.event.*;

public class AppWindow2 extends Frame implements ActionListener {

	Label name,addr,gender,phone;
	TextField nametf, phonetf;
	CheckboxGroup genderrb;
	TextArea addrtf;
	
	
			public AppWindow2() {
				this.setLayout(new GridLayout(8,2));
				this.setSize(500,600);
				
				name = new Label("NAME:");
				name.setSize(100,50);
				addr = new Label("ADDRESS:");
				addr.setSize(100,50);
				gender = new Label("GENDER:");
				gender.setSize(100,50);
				phone = new Label("PHONE NUMBER:");
				phone.setSize(100,50);
				
				nametf = new TextField();
				addrtf = new TextArea();
				genderrb = new CheckboxGroup();
				Checkbox male = new Checkbox("MALE",genderrb,false);
				Checkbox female = new Checkbox("FEMALE",genderrb,false);
				Checkbox other = new Checkbox("OTHER",genderrb,false);
				phonetf = new TextField();
				Button submit = new Button("SUBMIT");
				submit.addActionListener(this);
				
				Button reset = new Button("RESET");
				reset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						nametf.setText("");
						phonetf.setText("");
						addrtf.setText("");
						genderrb.setSelectedCheckbox(null);
					}
				});
				
				
				Panel p = new Panel();
				this.add(name);
				this.add(nametf);
				
				this.add(addr);
				this.add(addrtf);
				
				this.add(gender);
				p.add(male);
				p.add(female);
				p.add(other);
				this.add(p);
				
				this.add(phone);
				this.add(phonetf);
				
				this.add(submit);
				this.add(reset);
				
				
				
				
				
				this.setVisible(true);
				 this.addWindowListener(new WindowAdapter() {
			         public void windowClosing(WindowEvent windowEvent){
			            System.exit(0);
			         }        
			      });
				
				
			}
			
			 public void actionPerformed(ActionEvent e){
				 
				 this.setSize(this.getWidth(),this.getHeight()+400);
				 
				 this.add(new Label("Name:"));
				 this.add(new Label(nametf.getText()));
				 
				 this.add(new Label("Address:"));
				 this.add(new Label(addrtf.getText()));
				 
				 this.add(new Label("Gender:"));
				 this.add(new Label(genderrb.getSelectedCheckbox().getLabel()));
				 
			      
			    }
			
			

			public static void main(String[] args) {
				AppWindow2 appframe = new AppWindow2();

			}


	}

