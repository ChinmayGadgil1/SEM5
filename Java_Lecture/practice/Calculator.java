import java.awt.*;

public class Calculator extends Frame {
	Button b[];
	TextField tf;
	
	public Calculator() {
		// TODO Auto-generated constructor stub
		b = new Button[16];
		for(int i=0;i<10;i++)
			b[i]=new Button(""+i);
		b[10]=new Button("+");
		b[11]=new Button("-");
		b[12]=new Button("*");
		b[13]=new Button("/");
		b[14]=new Button("=");
		b[15]=new Button("C");
		
		tf = new TextField();
		tf.setBounds(10,10,100,20);
		Panel p = new Panel();
		p.setLayout(new GridLayout(4,4));
		
		for(int i=0;i<16;i++)
			p.add(b[i]);
		
		setSize(200,300);
		add(tf);
		add(p);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();

	}

}