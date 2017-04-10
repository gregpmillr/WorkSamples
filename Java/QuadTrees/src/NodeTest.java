import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class for testing the Node class
 *
 * @author greg
 *
 */
public class NodeTest {

	private static final int MAX_NODES = 10;
	private static final int MIN_COORDINATES = 0;

    @Test
    public void testQuadrants() {

        Node rect = new Node(MIN_COORDINATES, MIN_COORDINATES, MAX_NODES, MAX_NODES);
        Point[] points = new Point[4];

        points[0] = new Point(7,2);
        points[1] = new Point(2,2);
        points[2] = new Point(2,7);
        points[3] = new Point(7,7);
        assertEquals(Node.QuadrantPosition.NE, rect.quadrant(points[0]));
        assertEquals(Node.QuadrantPosition.NW, rect.quadrant(points[1]));
        assertEquals(Node.QuadrantPosition.SW, rect.quadrant(points[2]));
        assertEquals(Node.QuadrantPosition.SE, rect.quadrant(points[3]));

    } //end method

    @Test
    public void testContainers() {

        Node containers = new Node(MIN_COORDINATES,MIN_COORDINATES,MAX_NODES,MAX_NODES);
        Point[] points = new Point[11];

        points[0] = new Point(5, 5);
        points[1]  = new Point(12, 5);
        points[2]  = new Point(5, 12);
        points[3]  = new Point(0, 0);
        points[4]  = new Point(0, 10);
        points[5]  = new Point(10, 0);
        assertTrue(containers.contains(points[0]));
        assertFalse(containers.contains(points[1]));
        assertFalse(containers.contains(points[2]));
        assertTrue(containers.contains(points[3]));
        assertFalse(containers.contains(points[4]));
        assertFalse(containers.contains(points[5]));

    } //end method

} //end class
