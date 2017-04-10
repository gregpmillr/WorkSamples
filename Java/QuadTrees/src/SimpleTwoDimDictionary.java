import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents an ArrayList implementation of the TwoDimDictionary interface.
 * 
 * Restrictions: Doesn't display.
 * 
 * @author greg
 *
 */
public class SimpleTwoDimDictionary implements TwoDimDictionary {

	/**
	 * Declare instance variables
	 */
    private ArrayList<Point> points;
    private Node bounds;
    
    public SimpleTwoDimDictionary(Node bounds){
    	
        this.bounds = bounds;
        points = new ArrayList<Point>();
        
    } //end constructor
    
    @Override
    public void insert(Point point) {
    	
        // only insert if in bounds
        if(bounds.contains(point)){points.add(point);}
        
    } //end method

    @Override
    public int count(Node bounds) {
    	
        Iterator<Point> iterator = points.iterator();
        int count = 0; // the amount of bounded points
        
        while(iterator.hasNext()){
            if(bounds.contains(iterator.next())){count++;}
        }
        
        return count;
        
    } //end method

    @Override
    public void query(ArrayList<Point> set, Node bounds) {
    	
        Iterator<Point> iterator = points.iterator();
        while(iterator.hasNext()){
            Point point = iterator.next();
            if(bounds.contains(point)){set.add(point);}
        }
        
    } //end method

    @Override
    public int size() {return points.size();}

    @Override
    public void display() {} //end method

} //end class
