import java.util.*;

public class Envelop {
	List<Coord> coords;
	
	public Envelop(List<Coord> coords) {
		this.coords = coords;
	}
	
	private int maxXCoord() {
		int largest = 0;
		
		for (Coord i : coords) {
			if (i.x > largest) {
				largest = i.x;
			}
		}
		
		return largest;
	}
	
	private int maxYCoord() {
		int largest = 0;
		
		for (Coord i : coords) {
			if (i.y > largest) {
				largest = i.y;
			}
		}
		
		return largest;
	}
	
	public String toString() {
		final int HEIGHT = maxYCoord();
		final int WIDTH = maxXCoord();
		char drawing[][] = new char[HEIGHT][WIDTH];
		
		Coord currentPosition = new Coord(0, 0);
		
		for (Coord newPosition : coords) {
			// Determine the way the line moves
			if (currentPosition.y != newPosition.y) {
				// We've changed the y
				
				
			} else if (currentPosition.x != newPosition.x) {
				// We've changed the x
				
				
			}
		}
	}
}
