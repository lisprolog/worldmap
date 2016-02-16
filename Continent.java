/**
  * Ein Kontinent ist eine Sammlung von Territorien. Die Territorien eines Kontinents 
  * müssen nicht notwendigerweise beisammen liegen oder benachbart sein.
  */
import java.util.LinkedList;

public class Continent{

	String name;
	String[] territories;
	int terrNumb;

	public Continent(String name, int t){
		this.name = name;
		this.terrNumb = t;
	}

	public void addTerritories(String[] t){
		this.territories = t;
	}
}
