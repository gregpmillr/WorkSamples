/**
 * Represents a point with x,y coordinates on a point.
 * This class may be instantiated by providing x,y int parameters.
 * 
 * @author greg
 *
 */
public class Point {
	/**
	 * Declare instance variables
	 */
    private int X;
    private int Y;
    
    /**
     * Constructor accepting x,y coordinates for the created point. 
     * @param x value for X coordinate.
     * @param y value for Y coordinate.
     */
    public Point(int x, int y){X = x; Y = y;}
    public int getX(){return this.X;}
    public int getY(){return this.Y;}
    public void setX(int x){this.X = x;}
    public void setY(int y){this.Y = y;}
    
    @Override
    public String toString(){return X + "," + Y;}
    
} //end class
