import java.util.ArrayList;

/**
 * Class representing the implementation for TwoDimDictionary interface.
 * This QuadTree includes functionality for CRUD operations.
 * QuadTrees may be a leaf, pointer, or null, each depending on if
 * the countPts int is 0
 *
 * Restrictions: No display tree method
 * .
 * @author greg
 *
 */
public class Quadtree implements TwoDimDictionary {

	/**
	 * Declare & instantiate instance variables
	 */
    private static int MAX_NODES = 4;
    private Node bounds = null; // bounds for tree
    private int countPts = 0; // # of points in Node
    private Point point = null; // Point if this is a leaf
    private Quadtree[] subTrees = null; // Children of this QuadTree if a pointer

    /**
     * QuadTree constructor accepting arguments for Node bounds.
     * @param bounds Bounds to be set for this Node.
     */
    public Quadtree(Node bounds){

        this.bounds = bounds;

    } //end constructor

    @Override
    public void insert(Point newPoint) {

        //ensure that we're in bounds
        if(!bounds.contains(newPoint)){return;}

        //this is a leaf as there are no points
        else if(countPts == 0){this.point = newPoint;}
        //leaf is filled, need to split up and make this a pointer
        else if(countPts == 1){
            //create quadrants (instantiate subTree QuadTrees)
            split();
            subTrees[bounds.quadrant(this.point).getVal()].insert(this.point);
            this.point = null;
            subTrees[bounds.quadrant(newPoint).getVal()].insert(newPoint);
        }
        //Node already has children, so iterate through and find an empty spot
        else{subTrees[bounds.quadrant(newPoint).getVal()].insert(newPoint);}

        countPts++;

    } //end method

    /**
     * Instantiate the QuadTrees for the subTree's declaration variable
     */
    private void split(){

        subTrees = new Quadtree[4];
        subTrees[Node.QuadrantPosition.NE.getVal()] = new Quadtree(bounds.quadrantNE());
        subTrees[Node.QuadrantPosition.NW.getVal()] = new Quadtree(bounds.quadrantNW());
        subTrees[Node.QuadrantPosition.SW.getVal()] = new Quadtree(bounds.quadrantSW());
        subTrees[Node.QuadrantPosition.SE.getVal()] = new Quadtree(bounds.quadrantSE());

    } //end method

    @Override
    public void query(ArrayList<Point> set, Node testBounds) {

    	//look for intersections of this Node to query
        if(bounds.intersects(testBounds)){

            //this is a leaf
            if(countPts == 1 && testBounds.contains(point)){
                set.add(point);
            }
            else if(countPts > 1){
                for(int i = 0; i < MAX_NODES; i++){
                    subTrees[i].query(set, testBounds);
                }
            }
        } //end if

    } //end method

    @Override
    public int count(Node testBounds) {

        int amount = 0;

        if(countPts == 0){return 0;}

        //this Node's bounds within Node testBounds
        else if(bounds.coveredBy(testBounds)){amount = countPts;}
        //this Node is a point inside testBounds
        else if(isPoint() && testBounds.contains(point)){amount = 1;}
        //not a point so look at children
        else if(!isPoint()){
            //look into the bounds which intersect and increase points.
            for(int i = 0; i < MAX_NODES; i++){
                if(subTrees[i].bounds.intersects(testBounds)){
                    amount += subTrees[i].count(testBounds);
                }
            } //end for
        }

        return amount;

    } //end method

    @Override
    public int size() {return countPts;}

    @Override
    public void display() {}

    /**
     * Check if this Node is a point. If the Point variable has a value,
     * it's a point.
     * @return true or false representing this Point variable
     */
    private boolean isPoint(){return point != null;}

}//end class
