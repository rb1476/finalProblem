import java.util.*;

public class Graph {
	private List<Bar> bars;
	private static List<XCoord> values;
	
	public Graph(List<Bar> bars) {
		this.bars = bars;
		values = makeXCoords();
		sortXCoords();
	}
	
	private List<XCoord> makeXCoords() {
		List<XCoord> newValues = new ArrayList<XCoord>();
		
		for (Bar i : bars) {
			XCoord start = new XCoord(i, i.getStartX());
			XCoord end = new XCoord(i, i.getEndX());
			newValues.add(start);
			newValues.add(end);
		}
		
		return newValues;
	}
	
	private static void sortXCoords() {
		values.sort(Comparator.comparing(XCoord::getValue));
	}
	
	public List<Bar> getBars() {
		return bars;
	}
	
	public void setBars(List<Bar> bars) {
		this.bars = bars;
	}
	
	public List<XCoord> getXCoords() {
		return values;
	}
	
	@SuppressWarnings("static-access")
	public void setXCoords(List<XCoord> values) {
		this.values = values;
	}
	
	public int getSize() {
		return bars.size();
	}
	
	public Bar maxBar() {
		Bar largest = new Bar(0, 0, 0);
		
		for (Bar bar : bars) {
			if (bar.getHeight() < largest.getHeight()) {
				largest = bar;
			}
		}
		
		return largest;
	}
	
	public Graph removeBar() {
		bars.remove(maxBar());
		
		return new Graph(bars);
	}
}
