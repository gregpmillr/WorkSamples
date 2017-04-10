import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Class for testing the SimpleTwoDimDictionary class
 *
 * @author greg
 *
 */
public class SimpleTwoDimDictionaryTest {

	private static final int MAX_NODES = 10;
	private static final int MIN_COORDINATES = 0;


    @Test
    public void testInserts() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(new Node(0,0,MAX_NODES,MAX_NODES));
        simple.insert(new Point(7,9));
        assertEquals(1, simple.size());
        simple.insert(new Point(4,2));
        assertEquals(2, simple.size());
    } //end method

    @Test
    public void testQueries() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(new Node(0,0,MAX_NODES,MAX_NODES));

        for(int i = 0;i<MAX_NODES;i++){
        	for(int j = 0;j<MAX_NODES;j++){
                simple.insert(new Point(i, j));
        	}
        }

        ArrayList<Point> list = new ArrayList<Point>();
        simple.query(list, new Node(0,0,MAX_NODES,MAX_NODES));
    } //end method

    @Test
    public void testCounts() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(new Node(0,0,MAX_NODES,MAX_NODES));

        //total count for points to confirm amount
        int count = 0;

        for(int i = 0;i<MAX_NODES;i++){
        	for(int j = 0;j<MAX_NODES;j++){
        		simple.insert(new Point(i, j));
                count++;
        	}
        }

        assertEquals(count, simple.count(new Node(0,0,MAX_NODES,MAX_NODES)));
    } //end method

    @Test
    public void testSizes() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(new Node(0,0,MAX_NODES,MAX_NODES));
        simple.insert(new Point(7,9));
        assertEquals(1, simple.size());
        simple.insert(new Point(6,2));
        assertEquals(2, simple.size());
    } //end method



} //end class
