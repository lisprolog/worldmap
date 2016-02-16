import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class Panel extends JPanel implements MouseListener, MouseMotionListener{

	Graphics2D g2; 
	GeneralPath path;
	Color color = Color.BLACK;
	Polygon p;

	public Panel(){
	        addMouseListener(this);
	        addMouseMotionListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
	        g2 = (Graphics2D) g;
		super.paintComponent(g2);
		path = new GeneralPath(GeneralPath.WIND_NON_ZERO);

// 		first try: change the color of rectangle by clicking
		g2.setPaint(color);
	        g2.setStroke(new BasicStroke(0.1f));
		path.moveTo(50,50);
		path.lineTo(100,50);
		path.lineTo(100,100);
		path.lineTo(50,100);
		path.lineTo(50,50);
		g2.draw(path);

//		second try: 
		path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		int[] listX = {150, 200, 200, 250, 250, 200, 200, 150, 150, 100, 100, 150, 150};
		int[] listY = {100, 100, 150, 150, 200, 200, 250, 250, 200, 200, 150, 150, 100};
		p = new Polygon(listX, listY, 13);
		g2.setPaint(color);
		g2.setStroke(new BasicStroke(1.0f));
		path.moveTo(listX[0], listY[0]);
		for(int i = 1; i < listX.length; i++){
			path.lineTo(listX[i], listY[i]);
		}
		g2.fill(path);
	}

    	public void mousePressed(MouseEvent me) {
		System.out.println("press " + me.getPoint());
    	}

  	public void mouseExited(MouseEvent me) {
		System.out.println("exit " + me.getPoint());
   	}

   	public void mouseEntered(MouseEvent me) {
		System.out.println("enter " + me.getPoint());
   	}

   	public void mouseReleased(MouseEvent me) {
		System.out.println("up at " + me.getPoint());
    	}

    	public void mouseClicked(MouseEvent me) {
        	System.out.println("click at " + me.getPoint());
    	}

    	public void mouseMoved(MouseEvent me) {
        	System.out.println("moved to " + me.getPoint());
    	}

    	public void mouseDragged(MouseEvent me) {
        	System.out.println("dragged to " + me.getPoint());
    	}
}
