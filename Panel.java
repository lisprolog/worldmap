/**
  *	creates a JPanel that
  */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import java.awt.geom.GeneralPath;

public class Panel extends JPanel implements MouseListener, MouseMotionListener{

	Graphics2D g2; 
        GeneralPath path;
	LinkedList<Patch> patches;
	LinkedList<Point> points;
	ListIterator<Patch> it;
	ListIterator<Point> it2; 

	int x;
	int y;

	Patch pa;
	

	public Panel(){

		addMouseListener(this);
		addMouseMotionListener(this);
	}

//	@Override
	public void paintComponent(Graphics g){
	        g2 = (Graphics2D) g;
		super.paintComponent(g2);

		Scanner sc = new Scanner(System.in);

        	path = new GeneralPath(GeneralPath.WIND_NON_ZERO);

		patches = new LinkedList<Patch>();
		points = new LinkedList<Point>();
		ListIterator<Patch> it;
		ListIterator<Point> it2; 

		String name = "";
		String token = "";

		while(sc.hasNext()){
			token = sc.next();
			if(token.equals("patch-of")){
				name = sc.next();
				if(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
				if(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
				if(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
				pa = new Patch(name);
				patches.add(pa);
				//read coordinates
				while(sc.hasNextInt()){
					x = sc.nextInt();
					y = sc.nextInt();
					Point p = new Point(x,y);
					pa.addPoint(p);
				}
			}
		}
	        g2.setPaint(Color.BLACK);
	        g2.setStroke(new BasicStroke(0.1f));
		path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		it = patches.listIterator(0);
		while(it.hasNext()){
			pa = it.next();
			Point p1;
			Point p2;
			points.addAll(pa.getCoordinates());
			p1 = points.getFirst();
			path.moveTo(p1.getX(), p1.getY());
			points.removeFirst();
			while(!points.isEmpty()){
				p2 = points.getFirst(); 
				points.removeFirst();
				path.lineTo(p2.getX(), p2.getY());
			}
			path.lineTo(p1.getX(), p1.getY());
		}
		g2.draw(path);
//		g2.fill(path);
	}

	public void mousePressed(MouseEvent me){
	}

	public void mouseExited(MouseEvent me){
	}
	
	public void mouseEntered(MouseEvent me){
	}

	public void mouseReleased(MouseEvent me){
	}

	public void mouseClicked(MouseEvent me){
	}

	public void mouseMoved(MouseEvent me){
	}
	
	public void mouseDragged(MouseEvent me){
	}
}
