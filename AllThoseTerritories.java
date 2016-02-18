import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.geom.GeneralPath;
import java.util.Arrays;

public class AllThoseTerritories{
	


    public static void main(String[] args){

        PolygonPanel polygonPanel = new PolygonPanel();
        PolygonSelector selector = new PolygonSelector(polygonPanel);
        polygonPanel.addMouseListener(selector);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(polygonPanel);
        f.setSize(1250,650);
	f.setTitle("All those Territories");
        f.setLocation(0,200);
        f.setVisible(true);
    }
}

class PolygonPanel extends JPanel{

    boolean gameOn = true;

    HashMap<String, Territory> territoryMap;
    LinkedList<Territory> territories;
    LinkedList<Patch> polygons = new LinkedList<Patch>();
    LinkedList<Point> points;

    Color[] colors;
    ListIterator<Patch> it = null;
    ListIterator<Point> itP;
    ListIterator<Territory> itT;
    Point mouse;
    Scanner sc = new Scanner(System.in);
    Graphics2D g2;
    GeneralPath path;
    int x;
    int y;
    Patch pa;
    Territory t;
    Continent c;
    String token;

    public PolygonPanel(){

        setBackground(Color.white);
    }
  
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        if(polygons.size() == 0){ // because polygons already initialised!
            initPolygons();
	}

	String pauseToken = "";
	
	path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
	// create iterator of polygons
	it = polygons.listIterator(0);
	while(it.hasNext()){
		pa = it.next();
		Point p1;
		Point p2;
		itP = pa.myGetPoints().listIterator(0);
		p1 = itP.next();
		path.moveTo(p1.getX(), p1.getY());
		while(itP.hasNext()){
			p2 = itP.next(); 
			path.lineTo(p2.getX(), p2.getY());
		}
		path.lineTo(p1.getX(), p1.getY());
		g2.setPaint(pa.getColor());
		g2.setStroke(new BasicStroke(0.1f));
		g2.fill(pa);
	}

	while(gameOn){
		System.out.println("Phase Landerwerb: ");
//		while(isNotOccupied()){
			System.out.println("Choose a country!");
//			pauseToken = sc.next();
//		}
		System.out.println("Phase Angreifen und Bewegen: ");
		System.out.println("Phase Eroberungen: ");

		gameOn = false;		
	}
    }

    public boolean isNotOccupied(){
	it = polygons.listIterator(0);
	boolean occupied = true;
	while(it.hasNext()){
		pa = it.next();
		if(pa.getColor() != Color.BLACK){
			occupied = false;
		}
	}
	return occupied;
    }

    public boolean computerChoose(){
	it = polygons.listIterator(0);
	boolean occupied = true;
	while(it.hasNext()){
		pa = it.next();
		if(pa.getColor() == Color.BLACK){
			occupied = false;
		}
	}
	return occupied;
    }

    private void initPolygons(){

		territories = new LinkedList<Territory>();
		territoryMap = new HashMap<String, Territory>();
		points = new LinkedList<Point>();
//		ListIterator<Patch> it;
		ListIterator<Point> it2;
//		ListIterator<Territory> itT; 

		String lastName = "";
		String name = "";
		String patchName = "";
		String neighborName = "";
		String buffr = "";
		token = sc.next();

		do{
			if(token.equals("patch-of")){
				name = sc.next();
				while(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
				// next Patch vs next Territory
				if(lastName.equals(name)){
//					System.out.println("startNewPatch");
					pa = new Patch(name);
					while(sc.hasNextInt()){
//						System.out.println(" hasNextInt()");
						x = sc.nextInt();
//						System.out.println(" x: " + x);
						y = sc.nextInt();
//						System.out.println(" y: " + y);
						pa.addPoint(x, y);
//						System.out.println("Test1");
						Point pp = new Point(x,y);
//						System.out.println("Test2");
						pa.myAddPoint(pp);
//						System.out.println(" Point added");
					}
				}else{
//					System.out.println("startNewTerritory");
					pa = new Patch(name);
					t = new Territory(name);
					//read coordinates
					while(sc.hasNextInt()){
//						System.out.println(" hasNextInt()");
						x = sc.nextInt();
//						System.out.println(" x: " + x);
						y = sc.nextInt();
//						System.out.println(" y: " + y);
						pa.addPoint(x, y);
//						System.out.println("Test1");
						Point pp = new Point(x,y);
//						System.out.println("Test2");
						pa.myAddPoint(pp);
//						System.out.println(" Point added");
					}
				}

				polygons.add(pa);
				t.addPatch(pa);
				territories.add(t);
//				System.out.println("------->"+name +" "+ t);
				territoryMap.put(name, t);
				lastName = name;
//				System.out.println("Patch " + pa.getName()+" created");
				token = sc.next();

			}

			if(token.equals("capital-of")){

//				System.out.println("startNewCapital");

				name = sc.next();
				while(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
				System.out.println(name);
				Capital c = new Capital(sc.nextInt(),sc.nextInt());
				System.out.println("capital-of:" + name);
				t = territoryMap.get(name);
//				System.out.println("Map:getTerritory: "+t.getName());
				t.setCapital(c);
				territoryMap.put(name, t);
//				System.out.println("setCapitalMaptest: "+territoryMap.get(name).getCapital());
				token = sc.next();
			}

			if(token.equals("neighbors-of")){
//				System.out.println("scNext():" + token);
				patchName = getTerritoryName();
//				System.out.println("NEIGHBORSOF:Territory:"+ patchName);
//				System.out.println("#"+patchName+"#");
//				System.out.println("contains: "+territoryMap.containsKey(patchName));
				t = territoryMap.get(patchName);
//				System.out.println(territoryMap.get(patchName));
//				System.out.println("t:" + t);
				// get name of neighbors
				String[] neighborNamesArray;
				neighborNamesArray = getNeighborName();
//				System.out.println("ArraySize: "+neighborNamesArray.length);
				t.addNeighbors(neighborNamesArray);
//				System.out.println("Neighbors added!");				
			}// end neighbors-of

			if(token.equals("continent")){
//				System.out.println("#############startContinents###############");
				name = sc.next();
				while(!sc.hasNextInt()){
					name +=" " + sc.next();
				}
//				System.out.println("Continent:"+ name);
				int neigNumb = sc.nextInt();
				buffr = sc.next();
//				System.out.println("buffr->" + buffr);
				String[] neighborNamesArray;
				neighborNamesArray = getContinentName();
//				System.out.println("ArraySize: "+neighborNamesArray.length);
				Continent c = new Continent(name, neigNumb);
				c.addTerritories(neighborNamesArray);
//				System.out.println("Terr. added!");						 
				
			}// end continent
		}while(sc.hasNext());// end while(sc.hasNext())
//		FILE INPUT END		

//		fill the map the first time
		path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		// create iterator of polygons
		it = polygons.listIterator(0);
//		System.out.println("start drawing");
		while(it.hasNext()){
			pa = it.next();
			Point p1;
			Point p2;
			itP = pa.myGetPoints().listIterator(0);
			p1 = itP.next();
			path.moveTo(p1.getX(), p1.getY());
			while(itP.hasNext()){
				p2 = itP.next(); 
				path.lineTo(p2.getX(), p2.getY());
			}
			path.lineTo(p1.getX(), p1.getY());
			g2.setPaint(Color.BLACK);
			g2.setStroke(new BasicStroke(0.1f));
			g2.fill(pa);
		}
 	}// end initPolygon()
  
/**	the Territory names can have 1(ex "Japan") 2 (ex: "Central America") or 3 tokens(ex: "Western United States")
	therefore I use this function to read the tokens until a ":" appears.
*/
	private String getTerritoryName(){
		String tToken = sc.next();
		String tName = ""; 
		do{
			if(tName.length() == 0){
				tName += tToken;
				tToken = sc.next();
//				System.out.println(tToken);
			}else if(!tToken.equals(":")){
				tName += " " + tToken;
				tToken = sc.next();
//				System.out.println(tToken);
			}
		}while(!tToken.equals(":"));
//		System.out.println("TerritoryName = " + tName);
		return tName;
	}

/**	the Neighbor names can have 1(ex "Japan") 2 (ex: "Central America") or 3 tokens(ex: "Western United States")
	therefore I use this function to read the tokens until a "continent" appears.
	this function returns a String Array containing all neighbors of one Territory
*/
	private String[] getNeighborName(){
//		System.out.println("startNeighborNameFunktion!");
		LinkedList<String> list = new LinkedList<String>();
		String[] s;
		token = "";// = sc.next();
		String nName = "";// = ""; //nToken; 
		while(!token.equals("neighbors-of")&& !token.equals("continent")&& sc.hasNext()){
			do{
//			System.out.println("test!" + nName);
				token = sc.next();
//				System.out.println(token);
				if(!token.equals("-") && !token.equals("neighbors-of")&& !token.equals("continent") && !token.equals("")){
					nName += token + " ";
//					System.out.println("Name: "+nName);
//					System.out.println("test2");
				}
			}while(!token.equals("-") && !token.equals("neighbors-of")&& !token.equals("continent") && !token.equals("continent") && sc.hasNext());
//			System.out.println("NeighborName = " + nName);
			list.add(nName);
			nName = "";
		}
		s = new String[list.size()];
		return list.toArray(s);
	}

/**	the Continent names can have 1(ex "Europe") 2 (ex: "South America")
	therefore I use this function to read the tokens until a ":" appears.
	this function returns a String Array containing all neighbors of one Territory
*/	
	private String[] getContinentName(){
		LinkedList<String> list = new LinkedList<String>();
		String[] s;
		token = "";// = sc.next();
		String nName = "";// = ""; //nToken; 
		while(!token.equals("neighbors-of")&& !token.equals("continent")&& sc.hasNext()){
			do{
				token = sc.next();
				if(!token.equals("-") && !token.equals("neighbors-of")&& !token.equals("continent") && sc.hasNext()){
					nName += token + " ";
				}
			}while(!token.equals("-") && !token.equals("neighbors-of")&& !token.equals("continent")&& sc.hasNext());
//			System.out.println("NeighborName = " + nName);
			list.add(nName);
			nName = "";
		}
		s = new String[list.size()];
		return list.toArray(s);
	}
}

/**
  *	
  */

class PolygonSelector extends MouseAdapter{

    PolygonPanel polygonPanel;

    Color nextColor = Color.BLUE;
  
    public PolygonSelector(PolygonPanel pp){
        polygonPanel = pp;
    }
  
    public void mousePressed(MouseEvent e){
	
        Point p = e.getPoint();
	Patch patch;
	String polyName = "";
	ListIterator<Patch> it = polygonPanel.polygons.listIterator(0);
	int i = 0;
	while(it.hasNext()){
		patch = it.next();
		if(patch.contains(p)){					// if patch contains Point
			polyName = patch.name;
			i = polygonPanel.polygons.indexOf(patch);	// get the index of patch in list of polygons
			patch = polygonPanel.polygons.get(i);		// get the patch
			if(isPatchFree(nextColor, patch)){
				if(nextColor == Color.RED){
					nextColor = Color.BLUE;
				}else if(nextColor == Color.BLUE){
					nextColor = Color.RED;
				}
				patch.setColor(nextColor);			// set the color
				polygonPanel.polygons.set(i, patch);		// set the patch back into the list
			}

			polygonPanel.repaint();				// repaint the picture/panel
		}
	}
	System.out.println(polyName);
	it = polygonPanel.polygons.listIterator(0);
	while(it.hasNext()){
		patch = it.next();
		if(patch.getName().equals(polyName)){	
			if(isPatchFree(nextColor, patch)){
				i = polygonPanel.polygons.indexOf(patch);
				patch = polygonPanel.polygons.get(i);
				patch.setColor(nextColor);
				polygonPanel.polygons.set(i, patch);
			}
		}
		polygonPanel.repaint();				
	}
    }

/**
  *	is the patch already occupied by opponent
  */
    public boolean isPatchFree(Color c, Patch pa){

	boolean free = false;
	Patch patch = pa;;
	if(patch.getColor() == Color.BLACK){
		System.out.println("FreePatch");
		free = true;
	}else{
		System.out.println("Patch not free");
		free = false;
	}
	return free;
    }

    public boolean occupationPhase(){

	Patch patch;
	String polyName = "";
	ListIterator<Patch> it = polygonPanel.polygons.listIterator(0);
	boolean occupied = true;
	while(it.hasNext()){
		patch = it.next();
		if(patch.getColor() == Color.BLACK){					// if patch contains Point
			occupied = true;
		}
	}
	return occupied;
    }	


}
