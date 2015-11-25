import java.util.List;
import java.util.ArrayList;
/**
 * Maze class which implements the Maze Interface
 * 
 * @author Jason Ginsberg
 * @version November 2014
 */
public class Maze<E> implements MazeInterface<E>
{
    private Object[][] data;
    private int row;
    private int column;
    /**
     * Constructor for objects of class Maze
     */
    public Maze(int rows, int cols)
    {
        // initialise instance variables
        this.row = rows;
        this.column = cols;
        data = new Object[row][column];
    }

    /**
     * @ return number of rows in the maze
     */
    public int numRows ()
    {
        return row;   
    }

    /**
     * @return number of columns in the maze
     */
    public int numCols (){
        return column;   
    }

    /**
     * @param pos is a valid position in the maze
     * @param item is data to place in position
     * @return the old value at pos or null if no item was there
     * Throw IndexOutOfBounds error if pos is not valid
     */
    public E put (Location pos, E item){
        if (isValid(pos)==false){
            throw new IndexOutOfBoundsException(pos + " is not a valid position");
        }
        int posRow = pos.getRow();
        int posColumn = pos.getCol();
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (i == posRow && j ==posColumn){
                    E old = (E)data[i][j];
                    data[i][j] = item;
                    return old;
                }
            }
        }
        return null;
    }

    /**
     * @return the item at pos
     * throw IndexOutOfBounds error if pos is not valid
     */
    public E get (Location pos){
        if (isValid(pos)==false){
            throw new IndexOutOfBoundsException(pos + " is not a valid position");
        }
        int posRow = pos.getRow();
        int posColumn = pos.getCol();
        for (int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                if (i == posRow && j ==posColumn)
                    return (E)data[i][j];
            }
        }
        return null;
    }

    /**
     * @param pos a possible position in the maze
     * @return true if pos is valid, false otherwise
     */
    public boolean isValid (Location pos){
        int posRow = pos.getRow();
        int posColumn = pos.getCol();
        return (posRow<row&&posColumn<column);
    }

    /**
     * Note: the Location class defines direction constants
     * @return a List of 4 neighboring valid cell Locations (N, S, E, W)
     * 
     */
    public List<Location> neighbors (Location pos){
        List<Location> neighbors = new ArrayList<Location>();
        int posRow = pos.getRow();
        int posColumn = pos.getCol();
        for(int nRow = posRow - 1; nRow <= posRow + 1; nRow++)
        {
            for(int nCol =  posColumn-1;  nCol <= posColumn + 1; nCol++)
            {
                if( !(posRow == nRow &&  posColumn == nCol) 
                &&
                (nRow >= 0 && nCol >= 0 && nRow < row && nCol < column) 
                && (!(nRow!=posRow && nCol!=posColumn)))
                {
                    neighbors.add(new Location(nRow,nCol));
                }
            }
        }
        return neighbors;
    }

    /**
     * @return a string in row-col major form that shows the maze in
     * a useful 2D manner
     */
    public String toString (){
        String mazeString = "";
        for (int i=0;i<column;i++){
            mazeString+=" ";          
            for (int j=0;j<row;j++){
                mazeString+=data[i][j];
                mazeString+="\t";
            }
            mazeString+="\n";
        }
        //System.out.println(mazeString);
        return mazeString;
    }
}
