import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * The test class Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Test1
{
    /**
     * Default constructor for test class Test
     */
    public boolean Test()
    {
        return true;
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void isValid()
    {
        Maze maze2 = new Maze(4, 5);
        assertEquals(true, maze2.isValid(new Location(2,3)));
        assertEquals(true, maze2.isValid(new Location(3,4)));
        assertEquals(true, maze2.isValid(new Location(0,4)));
        Maze maze3 = new Maze(1, 1);
        assertEquals(true, maze3.isValid(new Location(0,0)));
    }

    @Test
    public void isPut()
    {
        Maze maze1 = new Maze(10, 10);
        assertEquals(null, maze1.put(new Location(5,5), "book"));
        assertEquals(null, maze1.put(new Location(1,1), "firetruck"));
        assertEquals("firetruck", maze1.put(new Location(1,1), "book"));
        assertEquals(null, maze1.put(new Location(9,9), "x"));
        assertEquals("x", maze1.put(new Location(9,9), "jump"));
    }

    @Test
    public void isGet()
    {
        Maze maze1 = new Maze(3, 3);
        assertEquals(null, maze1.get(new Location(1,1)));
        assertEquals(null, maze1.put(new Location(1,1), 123));
        assertEquals(123, maze1.get(new Location(1,1)));
        assertEquals(null, maze1.get(new Location(0,0)));
    }

    @Test
    public void isNeighbors()
    {
        Maze maze1 = new Maze(15, 15);
        ArrayList list1= new ArrayList();
        list1.add(new Location(0,1));
        list1.add(new Location(1,0));
        ArrayList list2= new ArrayList();
        list2.add(new Location(13,14));
        list2.add(new Location(14,13));
        assertEquals(list1, maze1.neighbors(new Location(0,0)));
        assertEquals(15, maze1.numRows());
        assertEquals(list2, maze1.neighbors(new Location (14,14)));
    }
}

