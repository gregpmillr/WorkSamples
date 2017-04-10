import java.util.ArrayList;

/**
 * Interface defining signatures for which the Quadtree will implement.
 *
 * @author greg
 *
 */
public interface TwoDimDictionary {

    /**
     * inserting a point
     * @param point Point being inserted
     */
    public void insert(Point point);

    /**
     * Print Quadtree
     */
    public void display();

    /**
     * @param bounds Where to look in for points
     * @return Total amount of pounts within bounds
     */
    public int count(Node bounds);

    /**
     * Retrieve points inside of the Node bounds and add into ArrayList set
     * @param set Points are added to this ArrayList
     * @param bounds Node where we're looking for points
     */
    public void query(ArrayList<Point> set, Node bounds);

    /**
     * @return The number of points in the Quadtree
     */
    public int size();

} //end class
