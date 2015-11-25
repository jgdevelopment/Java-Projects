/**
 *  class Sequence displays the 
 *  numbers 2, 4, 8 , 16... < 100
 *  
 *  Get the sequence however you want, one per line or on the
 *  same line.  Add commas to be fancy
 * 
 * @author J. Smith
 * @version September 2012
 * */
public class Sequence
{
    public static void main (String [] args)
    {
        for (int x = 2; x < 1000; x*=2)
        {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
