import java.util.*;
import java.util.stream.Stream;

public class BruteForceEnveloper extends Enveloper {
	@Override
	public Envelop createEnvelop(Graph g) {
		if (g.getSize() == 1) { //base case | is the graph one bar?
			List<Coord> points = new ArrayList<Coord>();
			
			//retrieves coordinate values of bar corners
			int xValues[] = {g.getBars().get(0).getStartX(), 
					g.getBars().get(0).getEndX()};
			int yValues[] = {g.getBars().get(0).getHeight(), 0};
			
			//adds coords as made from values to list
			//order of BOTTOM LEFT, TOP LEFT, TOP RIGHT, BOTTOM RIGHT
			for (int x : xValues) {
				for (int y : yValues) {
					points.add(new Coord(x, y));
				}
			}
			
			//create envelop from points and return
			return new Envelop(points);
		}
		
		//inductive case | is the graph more than one bar?
		//find envelop of graph minus one bar then add same bar
		return merge(createEnvelop(g.removeBar()), g.maxBar());
	}
	
	private Envelop merge(Envelop e, Bar bar) {
		List<Coord> points = e.getCoords();
		//boundaries of bar
		int leftBound = bar.getStartX();
		int topBound = bar.getHeight();
		int rightBound = bar.getEndX();
		
		//removes coords within boundaries
		points.removeIf(n -> (n.getX() >= leftBound
				&& n.getY() < topBound
				&& n.getX() <= rightBound));
		
		for (Coord i : points) { //adds coords for all lines contacting boundaries
			//counts number of coords at that x value
			int xPair = (int)points.stream().filter(n -> (n.getX() == i.getX())).count();
			
			if (xPair != 2) { //adds coord at boundary if coord doesn't have pair going down
				points.add(new Coord(i.getX(), topBound));
				continue;
			}
			
			//makes stream of coords of same y value to left of coord
			Stream<Coord> yPairsLeft = points.stream().filter(n -> (n.getY() == i.getY()) 
					&& n.getX() < i.getX());
			//counts number of coords of same y value to left of coord
			int leftPairsNo = (int)yPairsLeft.count();
			//are there no coords of same y value between coord and bar?
			boolean adjacentLeft = yPairsLeft.anyMatch(n -> (i.getX() < n.getX()
					&& n.getX() < leftBound));
			
			//is coord adjacent to bar?
			//and are there even number of coords of same y value left of it or is it on x axis?
			if (i.getX() < leftBound && adjacentLeft && (leftPairsNo % 2 == 0 || i.getX() == 0)) {
				//adds coord to pair with coord at left boundary
				points.add(new Coord(leftBound, i.getY()));
				continue;
			}

			//makes stream of coords of same y value to right of coord
			Stream<Coord> yPairsRight = points.stream().filter(n -> (n.getY() == i.getY()) 
					&& n.getX() > i.getX());
			//counts number of coords of same y value to right of coord
			int rightPairsNo = (int)yPairsRight.count();
			//are there no coords of same y value between coord and bar?
			boolean adjacentRight = yPairsRight.anyMatch(n -> (rightBound < n.getX()
					&& n.getX() < i.getX()));
			
			//is coord adjacent to bar?
			//and are there even number of coords of same y value right of it or is it on x axis?
			if (i.getX() > rightBound && adjacentRight && (rightPairsNo % 2 == 0 || i.getX() == 0)) {
				//adds coord to pair with coord at right boundary
				points.add(new Coord(rightBound, i.getY()));
			}
		}
		
		//counts number of bar corners left of new bar taller than it
		int barCornersLeft = (int)points.stream().filter(n -> (n.getY() > topBound
				&& n.getX() < leftBound)).count();
		
		//counts number of bar corners right of new bar taller than it
		int barCornersRight = (int)points.stream().filter(n -> (n.getY() > topBound
				&& n.getX() > rightBound)).count();
		
		//do none of taller bars to left of new bar overlap it?
		if (barCornersLeft % 2 == 0) {
			//adds left corner of new bar
			points.add(new Coord(leftBound, topBound));
		}
		
		//do none of taller bars to right of new bar overlap it?
		if (barCornersRight % 2 == 0) {
			//adds right corner of new bar
			points.add(new Coord(rightBound, topBound));
		}
		
		return new Envelop(points);
	}
}