import java.util.*;

public class Graph {
	private List<Bar> bars;
	private static List<XCoord> values;
	
	public Graph(List<Bar> bars) {
		this.bars = bars;
		values = makeXCoords();
		sortXCoords();
	}
	
	public List<XCoord> makeXCoords() {
		List<XCoord> newValues = new ArrayList<XCoord>();
		
		for (Bar i : bars) {
			XCoord start = new XCoord(i, i.getStartX());
			XCoord end = new XCoord(i, i.getEndX());
			newValues.add(start);
			newValues.add(end);
		}
		
		return newValues;
	}
	
	public static void sortXCoords() {
		values.sort(Comparator.comparing(XCoord::getValue));
	}
	
	public List<XCoord> getXCoords() {
		return values;
	}
	
	public void setXCoords(List<XCoord> values) {
		Graph.values = values;
	}
	
	public int getSize() {
		return values.size();
	}
}
