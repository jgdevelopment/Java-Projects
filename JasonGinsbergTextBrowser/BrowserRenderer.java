
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.net.URLConnection;
import java.net.URL;

/**
 * Parses web page sent by user in User Interface, conforming to the rules of different tags
 * 
 * @Jason Ginsberg
 * @version 1
 */
public class BrowserRenderer
{
    private static int MAX_CHARS = 80;
    private List<String> links;
    private TokenParser parser;
    private String output;
    private String choseLink;
    private int breakTest;
    private int bodyTest;
    private int linkTest;
    private int linkBrackTest;
    private int order;
    private int orderList;
    private int blockTest;
    private int orderTest;
    private int listTest;
    public BrowserRenderer(Scanner input)throws Exception{
        parser = new TokenParser(input);
        links = new ArrayList<String>();
        choseLink = ("");
        String output = ("");
        breakTest = 0;
        bodyTest = 0;
        blockTest = 0;
        orderTest = 0;
        linkTest = 0;
        listTest = 0;
        linkBrackTest = 0;
        order = 1;
        orderList = 1;
    }

    public void loadPage()throws Exception{

        while (parser.hasNextToken()){
            Token result = parser.nextToken();

            if (result.getTag()==Tag.ANCHOR){
                AttributePair in = result.getFirstAttribute();
                output = in.getValue();
                links.add(output);
                linkTest = 1;
                bodyTest = 12;
                order++;
                linkBrackTest = 0;
            }
            else if(result.getTag()==Tag.CLOSE_ANCHOR){
                linkBrackTest = 3;
                System.out.print("]"+" ");
            }
            else if (result.getTag()==Tag.TEXT){
                AttributePair in = result.getFirstAttribute();
                output = in.getValue();
                breakTest ++;
                if (linkTest ==1){
                    if (linkBrackTest == 0){
                        System.out.print("[");
                        linkBrackTest = 2;
                        System.out.print(output);
                    }
                    else if (linkBrackTest == 2){
                        System.out.print(" "+output);
                    }
                    else if (linkBrackTest == 3){
                        System.out.print(output+" ");
                        linkTest = 0;
                        bodyTest = 1;
                    }
                }
                else{
                    if (breakTest == MAX_CHARS){
                        System.out.println(output+" ");
                        breakTest = 0;
                        if(blockTest == 1){
                            System.out.print("  ");
                        }
                    }
                    else{
                        System.out.print(output+" ");
                    }
                }
            }
            else if (result.getTag()==Tag.TITLE){
                System.out.print("Title: ");
                bodyTest = 1;
            }
            else if(result.getTag()==Tag.CLOSE_TITLE){
                bodyTest = 1;
                System.out.println();
            }
            else if (result.getTag()==Tag.BODY){
                bodyTest = 1;
            }
            else if(result.getTag()==Tag.CLOSE_BODY){
                bodyTest = 1;
            }
            else if(result.getTag()==Tag.BR){
                System.out.println();
            }
            else if(result.getTag()==Tag.P){
                System.out.println();
                bodyTest =1;
            }
            else if(result.getTag()==Tag.CLOSE_P){
                bodyTest = 1;
                System.out.println();
                System.out.println();
            }
            else if(result.getTag()==Tag.BLOCKQUOTE){
                System.out.print("  ");
                blockTest = 1;
            }
            else if(result.getTag()==Tag.CLOSE_BLOCKQUOTE){
                blockTest = 0;
            }
            else if(result.getTag()==Tag.OL){
                orderTest = 1;
            }
            else if(result.getTag()==Tag.CLOSE_OL){
                orderList = 1;
                orderTest = 0;
            }
            else if(result.getTag()==Tag.LI){
                listTest = 1;
                System.out.println();
                if (orderTest ==1&&listTest ==1){
                    System.out.print(orderList+". ");
                    orderList++;
                }
            }
            else if(result.getTag()==Tag.CLOSE_LI){
                listTest = 0;
            }
            else{
            }
        }
        System.out.println();
        System.out.println();
        int listLength = links.size();
        int currentIndex = 0;
        System.out.println("Links on this page:");
        while (currentIndex<listLength){
            System.out.println(currentIndex+". "+links.get(currentIndex));
            currentIndex++;
        }
        System.out.println();
    }

    public String getLink(int index)throws Exception{
        choseLink = links.get(index);
        return choseLink;
    }
}

