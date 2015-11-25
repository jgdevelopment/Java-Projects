import java.util.ArrayList;
/**
 * Write a description of class Composite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Composite
{
    private ArrayList<Long> primes;
    /**
     * Constructor for objects of class Composite
     */
    public long count(long n)
    {
        primes = createPrimes(n);
        return makeComposite(1,n, 0,1);
    }

    public static void main(){
        Composite c = new Composite();
        System.out.println(c.count(500));
    }

    public long makeComposite(long composite, long limit, int index, int distinct){
        int total = 0;
        if (composite>=limit){
            return 0;
        }
        if (distinct>=4){
            total++;
        }
        for (int x = index; x<primes.size();x++){
            boolean isDistinct = x!=index;
            total+=makeComposite(primes.get(x)*composite,limit,x,distinct + (isDistinct?1:0));
        }
        System.out.println("i "+index);
        System.out.println("c "+composite);
        System.out.println("d "+distinct);
        System.out.println("t "+total);
        System.out.println();	
        return total;
    }

    public static ArrayList<Long> createPrimes(long limit){     
        int lim = (int)(limit);
        boolean[] sieve = new boolean[lim];
        for (int i = 2; i<lim;i++){
            if (sieve[i] == false){
                for (int j = 2;j<lim/i;j++){
                    sieve[i*j] = true;
                }
            }
        }
        ArrayList<Long> primes = new ArrayList<Long>();
        for (long i = 2; i<lim;i++){
            if (sieve[(int)i]==false){
                primes.add(i);
            }
        }
        return primes;
    }
}
