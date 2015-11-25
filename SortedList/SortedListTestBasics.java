import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * class SortedListTest.
 * 
 * Some basic tests for the SortedList implementation 
 * from AP 2014-15 
 * Fall lab
 * 
 * To Students:  This does not test everything - but should help you 
 * get control of the add method.
 * 
 * @author  J. Smith
 * @version October 2014
 */
public class SortedListTestBasics
{
    private Object [] expected;
    private Object [] result;
    /**
     * Default constructor for test class SortedListTest
     */
    public SortedListTestBasics()
    {
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
        int i=0;
        for (Object value: expected)
        {
            assertEquals(value, result[i]);
            i++;
        }
        assertEquals(expected.length, result.length);
    }

    @Test
    public void toStringMT()
    {
        expected = new Comparable [0]; 
        SortedList list = new SortedList();
        assertEquals("[]", list.toString());
        assertEquals(0, list.size());
        result = list.toArray();
    }
    
    @Test
    public void toStringMany()
    {
        Object []  temp = {new String ("A"), new String ("B"), new String ("BA")};
        expected = temp;
        SortedList list = new SortedList();
        list.add(new String("A"));
        list.add(new String("BA"));
        list.add(new String("B"));
        result = list.toArray();
        assertEquals("[A, B, BA]", list.toString());  
        assertEquals(3, list.size());
    }
    

    @Test
    public void addOne()
    {
        Object []  temp = {"A"};
        expected = temp;
        SortedList list = new SortedList();
        assertEquals(true, list.add(new String("A")));
        result = list.toArray();
        assertEquals(1, list.size());
    }
    
    @Test
    public void addABC()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C")};
        expected = temp;
        SortedList list = new SortedList();
        assertEquals(true, list.add(new String("A")));
        assertEquals(true, list.add(new String("B")));
        assertEquals(true, list.add(new String("C")));
        result = list.toArray();
        assertEquals(3, list.size());
    }
    
    @Test
    public void addCBA()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C")};
        expected = temp;
        SortedList list = new SortedList();
        assertEquals(true, list.add(new String("C")));
        assertEquals(true, list.add(new String("B")));
        assertEquals(true, list.add(new String("A")));
        result = list.toArray();
        assertEquals(3, list.size());
    }
    
    @Test
    public void addACB()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C")};
        expected = temp;
        SortedList list = new SortedList();
        assertEquals(true, list.add(new String("A")));
        assertEquals(true, list.add(new String("C")));
        assertEquals(true, list.add(new String("B")));
        result = list.toArray();
        assertEquals(3, list.size());
    }
    
    @Test
    public void addCAB()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C")};
        expected = temp;
        SortedList list = new SortedList();
        assertEquals(true, list.add(new String("C")));
        assertEquals(true, list.add(new String("A")));
        assertEquals(true, list.add(new String("B")));
        result = list.toArray();
        assertEquals(3, list.size());
    }
    
    @Test
    public void addResize()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C"), new String ("D")};
        expected = temp;
        SortedList list = new SortedList(3); // use alternate constructor for capacity
        assertEquals(true, list.add(new String("C")));
        assertEquals(true, list.add(new String("A")));
        assertEquals(true, list.add(new String("B")));
        assertEquals(3, list.size());
        assertEquals(true, list.add(new String("D"))); // must resize to 6 to add this
        assertEquals(4, list.size());
        result = list.toArray();
    }
    
    @Test
    public void addResize2()
    {
        Object []  temp = {new String ("A"), new String("B"), new String ("C"), new String ("D")};
        expected = temp;
        SortedList list = new SortedList(3); // use alternate constructor for capacity
        assertEquals(true, list.add(new String("C")));
        assertEquals(true, list.add(new String("D")));
        assertEquals(true, list.add(new String("B")));
        assertEquals(3, list.size());
        assertEquals(true, list.add(new String("A"))); // must resize to 6 to add this
        assertEquals(4, list.size());
        result = list.toArray();
    }
}


