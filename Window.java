import javax.swing.*;

public class Window extends JFrame{


	/**
		https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
	*/
	public Window(){
		this.add(new JPanel());
		this.setSize(1250,650);
		this.setTitle("Risiko");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
