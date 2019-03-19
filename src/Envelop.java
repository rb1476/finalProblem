import java.util.*;
import java.lang.StringBuilder;

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
			if (currentPosition.y < newPosition.y) {
				// going up in graph
				drawing[currentPosition.y][currentPosition.x] = "+";
				
				for (int i = currentPosition.y + 1; i < newPosition.y - 1; i++) {
					drawing[i][currentPosition.x] = "|";
				}
				
				drawing[currentPosition.y][currentPosition.x] = "+";
				
				currentPosition = newPosition;
			} else if (currentPosition.y > newPosition.y) {
				// going down in graph
				drawing[currentPosition.y][currentPosition.x] = "+";
				
				for (int i = currentPosition.y - 1; i > newPosition.y - 1; i--) {
					drawing[i][currentPosition.x] = "|";
				}
				
				drawing[currentPosition.y][currentPosition.x] = "+";
				
				currentPosition = newPosition;	
			} else if (currentPosition.x < newPosition.x) {
				// going right in graph
				drawing[currentPosition.y][currentPosition.x] = "+";
				
				for (int i = currentPosition.x + 1; i < newPosition.x - 1, i++) {
					drawing[currentPosition.y][i] = "-";
				}
				
				drawing[currentPosition.y][currentPosition.x] = "+";
			}
		}
		
		StringBuilder lines = new StringBuilder();
		
		for (int i = HEIGHT; i >= 0; i--) {
			for (int j = 0; j <= WIDTH; i++) {
				if (drawing[i][j] != null) {
					lines.append(drawing[i][j]);
				} else {
					lines.append(" ")
				}
			}
		}
		
	}
}
