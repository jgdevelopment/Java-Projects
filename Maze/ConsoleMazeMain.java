
/**
 * class ConsoleMaze runs the robot maze escape
 * lab from a console window.
 * 
 * constructor -- reads the file and initializes the right type of robot
 *          or terminates with an error
 * run -- run the simulation pausing between steps for the user to press RETURN
 * 
 * November 2014
 * Updated for interface/abstract class version
 * 
 * @author J. Smith 
 * @version September 2012, November 2014
 */
import java.util.Scanner;
import java.io.File;

public class ConsoleMazeMain
{
    private Maze<MazeConstants> maze;
    private Robot robot;
    private Location start;
    private Location goal;

    /**
     * Constructor for objects of class ConsoleMaze
     * You can make your own maze files too - just follow the format
     * of the provided maze1 file.
     * 
     * Uncomment robot types when you have written the classes
     * 
     * @param mazeFilename enter "maze1" without the extension .txt
     * @param robotType the kind of robot to put in the maze, random or rhbot, add others as needed
     */
    public ConsoleMazeMain(String mazeFilename, String robotType)
    {
        createMaze(mazeFilename);
        if (robotType.equals("random"))
        {
            robot = new RandomBot(start, maze);
            maze.put(robot.getLocation(), MazeConstants.ROBOT); // R for random robot
        }  // add additional if statements as you add robot types
        else if (robotType.equals("rhbot"))
        {
            robot = new RHBot(start, maze);
            maze.put(robot.getLocation(), MazeConstants.ROBOT);
        }
        else
            throw new IllegalArgumentException ("unrecognized robot type " + robotType);
    }

    /**
     * Read the file and create a maze.  Files must be in the user directory where
     * the java source files exist.
     * @param filename the name of the maze file, no ".txt" extension
     */
    private void createMaze (String filename)
    {
        try
        {
            Scanner in = new Scanner (new File(System.getProperty("user.dir") + "/" + filename + ".txt"));
            int rows = in.nextInt();
            int cols = in.nextInt();
            start = new Location (in.nextInt(), in.nextInt());
            goal = new Location (in.nextInt(), in.nextInt());
            in.nextLine(); // skip white space
            System.out.println(rows + " " + cols + " start=" + start + " goal = " + goal);
            maze = new Maze(rows, cols);
            for (int r=0; r < rows; r++)
            {
                String line = in.nextLine();
                for (int c=0; c < cols; c++)
                {
                    String val = line.substring(0,1);
                    if (val.equals("*"))
                        maze.put(new Location(r,c), MazeConstants.WALL); 
                    else
                        maze.put(new Location(r,c), MazeConstants.PASSAGE); 
                    line = line.substring(1);
                }
            }
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(System.getProperty("user.dir") + "/" + filename + ".txt" + e);
        }
    }

    /**
     * primary maze loop, press enter to go one more step
     * works best if the maze class toString displays the CONTENTS of
     * the maze followed by a "\t" tab character.  This will display WALL, PASSAGE, ROBOT
     * in a grid like form with columns and rows aligned.
     */
    public void run ()
    {
        Scanner ctrl = new Scanner(System.in);
        System.out.println(maze);
        ctrl.nextLine();
        while (!robot.getLocation().equals(goal))
        {
            System.out.println("location:"+robot.getLocation());
            System.out.println("------------------");
            robot.act();
            System.out.println(maze);
            ctrl.nextLine();
        }
        System.out.println("Escaped!!");
    }
}
