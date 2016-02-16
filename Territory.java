/**
  *	Ein Territorium besteht aus mindestens einer Landfläche. Jede Landfläche gehört zu
  *	genau einem Territorium. Ein Territorium hat einen Namen und einen Punkt, der die 
  *	Stelle der Hauptstadt angibt. Ein Territorium ist mit beliebig vielen anderen 
  *	Territorien über einen Land­ oder Seeweg verbunden. Derart verbundene Territorien 
  *	werden als Nachbarn bezeichnet. Dabei ist es egal, ob die Territorien direkt 
  *	nebeneinander liegen oder über weitere Distanzen verbunden sind.
  *
  */
import java.awt.Color;
import java.util.LinkedList;

public class Territory{
	
	String name;
	Capital capital;
	String[] neighbors;
	Color color = Color.BLACK;
	

	LinkedList<Patch> patchList = new LinkedList<Patch>();
	LinkedList<String> neighborsList = new LinkedList<String>();

	public Territory(String name){
		this.name = name;
	}

	public void addPatch(Patch p){
		patchList.add(p);
	}

	public void addNeighbors(String[] n){
		this.neighbors = n;
	}

	public void addNeighbor(String n){
		neighborsList.add(n);
	}

	public Patch getPatch(){
		return patchList.get(1);
	}

	public String getNeighbor(){
		return neighborsList.get(1);
	}

	public void setCapital(Capital c){
		this.capital = c;
	}

	public Capital getCapital(){
		return this.capital;
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
}
