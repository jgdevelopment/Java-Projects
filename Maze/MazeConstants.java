
/**
 * enum MazeType defines constants to store what
 * type of thing is in the robot maze
 * 
 * Use anywhere you would put a regular type.
 * eg.
 *      List<MazeConstants> list 
 *      
 * Refer to the constants in another class by using
 * the format classname.constat
 * eg.
 *      if (x == MazeConstants.WALL)
 *          doSomething();
 * 
 * @author J. Smith 
 * @version November 2014
 */
public  enum MazeConstants
{
    WALL, PASSAGE, ROBOT
}
