import java.util.List;
/**
 * interface MazeInterface defines methods for a 2D data structure
 * 
 * @author J. Smith 
 * @version October 2014
 */
public interface MazeInterface<E>
{
    /**
     * @ return number of rows in the maze
     */
    public int numRows ();
    
    /**
     * @return number of columns in the maze
     */
    public int numCols ();
    
    /**
     * @param pos is a valid position in the maze
     * @param value is data to place in position
     * @return the old value at pos or null if no item was there
     * Throw IndexOutOfBounds error if pos is not valid
     */
    public E put (Location pos, E value);
    

    /**
     * @return the item at pos
     * throw IndexOutOfBounds error if pos is not valid
     */
    public E get (Location pos);
    
    /**
     * @param pos a possible position in the maze
     * @return true if pos is valid, false otherwise
     */
    public boolean isValid (Location pos);
  

    /**
     * Note: the Location class defines direction constants
     * @return a List of 4 neighboring valid cell Locations (N, S, E, W)
     * 
     */
    public List<Location> neighbors (Location pos);
    
    /**
     * @return a string in row-col major form that shows the maze in
     * a useful 2D manner
     */
    public String toString ();
}
