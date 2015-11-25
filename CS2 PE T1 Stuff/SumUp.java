
/**
 * SumUp
 * 
 * sums the even numbers from 50 to 100 with a while loop
 * 
 * @author J. Smith
 * @version September 2012
 */
public class SumUp
{
    public static void main (String [] args)
    {
        int randnum = (int) (Math.random() * 100);
        
        int sum = 0;
        int x = 50;
        while (x <= 100)
        {
            sum = sum + x;
            x+=2;
        }
        System.out.println("The sum of the numbers from 50..100 is " + sum);
    }
}
