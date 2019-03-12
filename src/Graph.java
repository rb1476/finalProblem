import java.util.*;

public class Graph {
	private List<Bar> bars;
	
	public Graph(List<Bar> bars) {
		this.bars = bars;
	}
	
	public List<XCoord> getXCoords(List<Bar> values) {
		List<XCoord> newValues = new ArrayList<XCoord>();
		
		for (Bar i : values) {
			XCoord start = new XCoord(i, i.startX);
			XCoord end = new XCoord(i, i.endX);
			newValues.add(start);
			newValues.add(end);
		}
		
		return newValues;
	}
	
	public void sortXCoords(List<XCoord> values) {
		values.sort(Comparator.comparing(XCoord::getValue));
	}
	
	
}
