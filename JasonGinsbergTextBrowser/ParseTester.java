
import java.util.Scanner;
import java.io.File;
import java.net.URLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
/**
 * Prints out information gathered from Token Parser
 * 
 * @author Jason Ginsberg
 *
 * @version December 12 2012
 */
public class ParseTester
{

    public static void main(String args[])throws Exception{
        URLConnection home = new URL("https://gist.github.com/raw/50f14dfc302aa5c9b49b/b2fc77be039a2bc8d2df028a395135edf196f75c/gistfile1.txt").openConnection();
        Scanner web = new Scanner(home.getInputStream());
        TokenParser reader = new TokenParser(web);
        while (reader.hasNextToken()){
            Token result = reader.nextToken();
            System.out.println(result);
        }

    }
}