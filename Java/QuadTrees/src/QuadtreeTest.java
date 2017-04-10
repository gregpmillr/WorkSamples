import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Class for testing the Quadtree
 * @author greg
 *
 */
public class QuadtreeTest {

		private static final int MAX_NODES = 10;
		private static final int MIN_COORDINATES = 0;


    @Test
    public void testInserts() {

        Quadtree tree = new Quadtree(new Node(MIN_COORDINATES,MIN_COORDINATES,MAX_NODES,MAX_NODES));
        tree.insert(new Point(7,9));
        assertEquals(1, tree.size());
        tree.insert(new Point(4,2));
        assertEquals(2, tree.size());

    } //end constructor

    @Test
    public void testQueries() {

        Quadtree tree = new Quadtree(new Node(0,0,MAX_NODES,MAX_NODES));

        for(int i = 0;i<10;i++){
        	for(int j = 0;j<10;j++){
                tree.insert(new Point(i, j));
        	}
        }

        ArrayList<Point> list = new ArrayList<Point>();
        tree.query(list, new Node(MIN_COORDINATES,MIN_COORDINATES,MAX_NODES,MAX_NODES));

    } //end method

    @Test
    public void testSizes() {

        Quadtree tree = new Quadtree(new Node(MIN_COORDINATES,MIN_COORDINATES,MAX_NODES,MAX_NODES));
        tree.insert(new Point(3,1));
        assertEquals(1, tree.size());
        tree.insert(new Point(6,9));
        assertEquals(2, tree.size());

    } //end method

    @Test
    public void testCounts() {

        Quadtree tree = new Quadtree(new Node(0,0,MAX_NODES,MAX_NODES));

        //total count for points to confirm amount
        int count = 0;

        for(int i = 0;i<MAX_NODES;i++){
        	for(int j = 0;j<MAX_NODES;j++){
                tree.insert(new Point(i, j));
                count++;
        	}
        }

        assertEquals(count, tree.count(new Node(MIN_COORDINATES,MIN_COORDINATES,MAX_NODES,MAX_NODES)));

    } //end method

} //end class
