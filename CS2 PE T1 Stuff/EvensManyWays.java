
/**
 * class EvensManyWays 
 * display even numbers from 2 to 50 in many ways
 * 
 * @author J. Smith
 * @version September 2012
 */
public class EvensManyWays
{
    public static void main (String [] args)
    {
        for (int x=2; x<=50; x+=2)
        {
            System.out.println(x + " ");
        }
        // as a while
        System.out.println();
        int x=2;
        while (x <= 50)
        {
            System.out.println(x + " ");
            x++;
        }
    }
}
