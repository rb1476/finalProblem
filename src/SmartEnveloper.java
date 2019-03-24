import java.util.*;

public class SmartEnveloper extends Enveloper {
    @Override
    public Envelop createEnvelop(Graph g) {
        // Retreive and sort the graph's x coords
        List<XCoord> xCoords = g.getXCoords();
        Graph.sortXCoords(xCoords);

        // Prepare lists for the algorithm
        Set<Bar> openBars = new HashSet<Bar>();
        List<Coord> resultPoints = new ArrayList<Coord>();
        resultPoints.add(new Coord(0, 0));

        // Keep track of the current maximum height
        // We _could_ calculate it each iteration from openBars, but that harms
        // time complexity
        int currentHeight = 0;

        // Go through each point
        for (XCoord xCoord : xCoords) {            
            // Is this xCoord's bar higher than the current height?
            // If so, our envelop must go upwards
            if (xCoord.getBar().height > currentHeight) {
                // Add the point before we go up, and the point after we go up
                resultPoints.add(new Coord(xCoord.getValue(), currentHeight));
                currentHeight = xCoord.getBar().height;
                resultPoints.add(new Coord(xCoord.getValue(), currentHeight));

                // Add this xCoord's bar to the set of bars which are open
                openBars.add(xCoord.getBar());
            }
            // Is this xCoord's bar lower or the same as the current height?
            else if (xCoord.getBar().height <= currentHeight) {
                // Is this the end of the current highest bar?
                if (openBars.contains(xCoord.getBar())) {
                    // Remove this bar from the set of bars which are open
                    openBars.remove(xCoord.getBar());

                    // Work out the next highest open bar, as that's the height
                    // we need to descend to
                    int highestBarHeight = 0;
                    for (Bar bar : openBars) {
                        if (bar.height > highestBarHeight) {
                            highestBarHeight = bar.height;
                        }
                    }

                    // Add the point before we go down, and then the point after
                    resultPoints.add(new Coord(xCoord.getValue(), currentHeight));
                    currentHeight = highestBarHeight;
                    resultPoints.add(new Coord(xCoord.getValue(), currentHeight));
                } else {
                    // Otherwise, just mark this bar as opened - we don't need
                    // to add any points since this xCoord's height is
                    // "overshadowed" by a higher bar
                    openBars.add(xCoord.getBar());
                }
            }
        }

        // Create an envelop from the points and return it
        return new Envelop(resultPoints);
    }
}
