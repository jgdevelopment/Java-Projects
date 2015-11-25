import java.util.Scanner;
import java.util.Scanner;
import java.io.File;
import java.net.URLConnection;
import java.net.URL;
/**
 * User Interface allows user to enter new URL, link on page, or quit.
 * 
 * @Jason Ginsberg 
 * @version 1
 */
public class UserInterface
{
    private String in;
    private String testHttp;
    private String testHttps;
    private Scanner parse;
    private URLConnection home;
    private BrowserRenderer begin;
    private String input;
    private String linkReturn;
    public static void main(String args[])throws Exception{
        URLConnection home = new URL("http://test.com").openConnection();
        Scanner parse = new Scanner(home.getInputStream());
        BrowserRenderer begin = new BrowserRenderer(parse);
        String in = ("");
        int test = 0;
        String linkReturn = ("");
        int input1 = 0;
        String input = ("");
        String testHttp = ("http://");
        String testHttps = ("https://");
        while(true){
            if (test == 0){
                System.out.println("select a link: 1");
                System.out.println("enter a new web url: 2");
                System.out.println("quit: 3");
                Scanner start = new Scanner(System.in);
                input = start.nextLine();
            }
            else {
                input = ("2");
            }
            if (input.equals("2")){
                if(test == 0){
                    System.out.println("Type Url: ");
                    Scanner user = new Scanner(System.in);
                    in = user.nextLine();
                    if(in.toLowerCase().contains(testHttp.toLowerCase())||in.toLowerCase().contains(testHttps.toLowerCase())){
                       
                    }
                    else{
                        in = (testHttp+in);  
                    }
                }
                else if(test ==1){
                    in = linkReturn;
                }
                home = new URL(in).openConnection();
                parse = new Scanner(home.getInputStream());
                begin = new BrowserRenderer(parse);
                begin.loadPage();
                test = 0;
            }  
            else if (input.equals("1")){
                System.out.println("Type Link Number: ");
                Scanner user2 = new Scanner(System.in);
                input1 = user2.nextInt();
                linkReturn = begin.getLink(input1);
                input = ("2");
                test =1;
            }
            else if (input.equals("3")){
                System.out.println("Thanks For Using the Browser!");
                break;
            }
            else {
                System.out.println("You made and error, type in a number");
            }
        }
    }
}
