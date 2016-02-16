/**
  *	Eine Landfläche ist ein zusammenhängender Bereich auf der Spielfläche und wird 
  *	beschrieben durch eine Folge von Koordinaten, die die Eckpunkte der Landfläche 
  *	angeben.
  */
import java.awt.Color;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Polygon;
import java.util.LinkedList;

public class Patch extends Polygon implements Shape{

	final String name;
	private Color color;
	LinkedList<Point> points = new LinkedList<Point>();

	public Patch(String name){
		this.name = name;
		this.color = Color.BLACK;
	}

	public String getName(){
		return this.name;
	}

	public void setColor(Color c){
		this.color = c;
	}

	public Color getColor(){
		return this.color;
	}
	
	public void myAddPoint(Point p){
		this.points.add(p);
	}

	public LinkedList<Point> myGetPoints(){
		return points;
	}
}
