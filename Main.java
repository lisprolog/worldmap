/**
	creates a 1250*650 JFrame
*/
import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame{

	public Main(){
		this.add(new Panel());
		this.setSize(1250,650);
		this.setTitle("WorldMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Main window = new Main();
				window.setVisible(true);
			}
		});
	}
}
