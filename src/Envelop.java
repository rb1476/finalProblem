import java.util.*;
import java.lang.StringBuilder;

public class Envelop {
	private List<Coord> coords;
	
	public Envelop(List<Coord> coords) {
		this.coords = coords;
	}
	
	private int maxXCoord() {
		int largest = 0;
		
		for (Coord i : coords) {
			if (i.getX() > largest) {
				largest = i.getX();
			}
		}
		
		return largest;
	}
	
	private int maxYCoord() {
		int largest = 0;
		
		for (Coord i : coords) {
			if (i.getY() > largest) {
				largest = i.getY();
			}
		}
		
		return largest;
	}
	
	public List<Coord> getCoords() {
		return coords;
	}
	
	public void setCoords(List<Coord> coords) {
		this.coords = coords;
	}
	
	public String toString() {
		final int HEIGHT = maxYCoord();
		final int WIDTH = maxXCoord();
		String drawing[][] = new String[HEIGHT][WIDTH];
		
		Coord currentPosition = new Coord(0, 0);
		
		for (Coord newPosition : coords) {
			//determine the way the line moves
			if (currentPosition.getY() < newPosition.getY()) {
				//going up in graph
				drawing[currentPosition.getY()][currentPosition.getX()] = "+";
				
				for (int i = currentPosition.getY() + 1; i < newPosition.getY() - 1; i++) {
					drawing[i][currentPosition.getX()] = "|";
				}
				
				drawing[currentPosition.getY()][currentPosition.getX()] = "+";
				
				currentPosition = newPosition;
			} else if (currentPosition.getY() > newPosition.getY()) {
				//going down in graph
				drawing[currentPosition.getY()][currentPosition.getX()] = "+";
				
				for (int i = currentPosition.getY() - 1; i > newPosition.getY() - 1; i--) {
					drawing[i][currentPosition.getX()] = "|";
				}
				
				drawing[currentPosition.getY()][currentPosition.getX()] = "+";
				
				currentPosition = newPosition;	
			} else if (currentPosition.getX() < newPosition.getX()) {
				//going right in graph
				drawing[currentPosition.getY()][currentPosition.getX()] = "+";
				
				for (int i = currentPosition.getX() + 1; i < newPosition.getX() - 1; i++) {
					drawing[currentPosition.getY()][i] = "--";
				}
				
				drawing[currentPosition.getY()][currentPosition.getX()] = "-+";
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
				//aligns digits in labels
				lines.append(" ".repeat(heightDigits - String.valueOf(i).length()));
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
