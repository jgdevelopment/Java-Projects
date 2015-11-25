
/**
 * class Average 
 * computes the average of 100 random numbers in the range 0..99
 * 
 * @author J. Smith 
 * @version September 2012
 */
public class Average
{
    public static void main (String [] args)
    {
        
        
        int sum = 0;
        int count = 0;
        while (count < 100)
        {
            int randnum = (int) (Math.random() * 100);
            sum = sum + randnum;
            count++;
        }
        System.out.println("The average is " + sum/100.0);
    }
}
