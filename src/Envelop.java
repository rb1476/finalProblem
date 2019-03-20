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
		String drawing[][] = new String[HEIGHT][WIDTH];
		
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
				
				for (int i = currentPosition.x + 1; i < newPosition.x - 1; i++) {
					drawing[currentPosition.y][i] = "--";
				}
				
				drawing[currentPosition.y][currentPosition.x] = "-+";
			}
		}
		
		StringBuilder lines = new StringBuilder();
		int heightDigits = String.valueOf(HEIGHT).length();
		
		lines.append(" ".repeat(heightDigits));
		lines.append(" .");
		lines.append(" ".repeat(2 * WIDTH));
		lines.append("\n");
		//first line of graph
		
		for (int i = HEIGHT; i >= 0; i--) {
			if (i == HEIGHT || i % 5 == 0) { //adds y axis labels (multiples of 5 and max)
				lines.append(" ".repeat(heightDigits - String.valueOf(i).length())); //aligns digits in labels
				lines.append(String.valueOf(i));
				lines.append(" .");
			}
			
			for (int j = 0; j <= WIDTH + 2; j++) { //adds whole line of graph
				if (drawing[i][j] != null) {
					lines.append(drawing[i][j]);
				} else if (i == 0) { // draws x axis
					if (j % 5 == 0) {
						lines.append(" :");
					} else {
						lines.append(" .");
					}
				} else {
					lines.append("  ");
				}
			}
			
			lines.append("\n");
		}
		
		for (int i = 0; i <= WIDTH; i++) { //adds x axis labels
			if (i == WIDTH || i % 5 == 0) {
				lines.append(String.valueOf(i));
				if (i % 5 == 0) {
					lines.append(" ".repeat(10 - String.valueOf(i).length()));
				}
			}
		}
		
		String str = lines.toString();
		
		return str;
	}
}
