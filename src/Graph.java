import java.util.*;

public class Graph {
	private List<Bar> bars;
	
	public Graph(List<Bar> bars) {
		this.bars = bars;
	}
	
	public List<XCoord> getXCoords() {
		List<XCoord> newValues = new ArrayList<XCoord>();
		
		for (Bar i : bars) {
			XCoord start = new XCoord(i, i.startX);
			XCoord end = new XCoord(i, i.endX);
			newValues.add(start);
			newValues.add(end);
		}
		
		return newValues;
	}
	
	public static void sortXCoords(List<XCoord> values) {
		values.sort(Comparator.comparing(XCoord::getValue));
	}
	
	
}
