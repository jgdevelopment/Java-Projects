
/**
 * class SortThree orders three integers
 * in ascending order
 * 
 * @author J. Smith
 * @version September 2012
 */
public class SortThree
{
    public static void main (String [] args)
    {
        int x = 10;
        int y = 60;
        int z = 50;
        int smallest = Integer.MIN_VALUE;
        if (x < y && x < z)
        {
            System.out.print(x + " ");
            if (y < z)
                System.out.println(y + " " + z);
            else
                System.out.println(z + " " + y);
        }
        else if (y < x && y < z)
        {
            System.out.print(y + " ");
            if (x < z)
                System.out.println(x + " " + z);
            else
                System.out.println(z + " " + x);
        }
        else
        {
            System.out.print(z + " ");
            if (x < y)
                System.out.println(x + " " + y);
            else
                System.out.println(y + " " + x);
        }
    }
}
