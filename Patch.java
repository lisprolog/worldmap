/**
  *	country class for WorldMap
  *	coordinates saved in a LinkedList<Points>
  */

import java.util.LinkedList;

public class Patch{

	final String name;
	private int x;
	private int y;
	private String color = "Black";
	LinkedList<Point> pointList = new LinkedList<Point>();

	public Patch(String name){
		this.name = name;
	}

	public void addPoint(Point p){
		pointList.add(p);
	}

	public LinkedList<Point> getCoordinates(){
		return pointList;
	}
	
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public String getName(){
		return this.name;
	}

	public void setColor(String color){
		this.color = color;
	}
}
