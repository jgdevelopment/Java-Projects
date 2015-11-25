
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
/**
 * class TokenParser3 parses html
 * text into tokens
 * 
 * Update December 2012
 *     This version uses regular expression patterns for identifying token types.
 *     Delimiter remains the default
 * 
 * @author J. Smith 
 * @version November 2012, December 2012
 */
public class TokenParser
{
    private Scanner tokenizer;

    /**
     * Constructor for objects of class TokenParser
     */
    public TokenParser(Scanner page)
    {
        tokenizer = page;

    }

    public boolean hasNextToken ()
    {
        return tokenizer.hasNext();
    }

    /**
     * @return the next token in the stream
     */
    public Token nextToken ()
    {
        String command = null;
        String param = null;
        String value = "";

        String textpattern = "[^<][^\\s||^<]*"; 
        String textIndicator = "[^<].*";
        String tagpattern = "<\\s*[^>]*\\s*>";
        String tagIndicator = "<.*";

        Token token = null;
        tokenizer.skip("\\s*");
        if (tokenizer.hasNext(textIndicator))
        {
            Token result = makeTextToken( tokenizer.findInLine(textpattern));
            return result;
        }   
        else
        {
            if (tokenizer.hasNext(tagIndicator))
            {
                String tokentext = tokenizer.findInLine(tagpattern);
                if (tokentext != null)
                {
                    return makeTagToken(tokentext);
                }
                else
                {
                    // weirdly formatted stuff so process token as text
                    return makeTextToken( tokenizer.next());
                }
            }
            else
            {
                return makeTextToken(tokenizer.next()); // use default delimiter
            }   
        } 

    }	

    private Token makeTextToken(String tokentext)
    {
        Token result = new Token ("TEXT");
        result.addAttribute("text", tokentext);
        return result;
    }

    /**
     * tokentext is in the form
     * 
     * tagpattern = <\\s*[^>]*\\s*>
     * 
     * type part must be terminated by a space
     */
    private Token makeTagToken(String tokentext)
    {
        Scanner tagProcessor = new Scanner (tokentext);
        tagProcessor.skip("<\\s*"); // skip < and whitespace
        String typepattern = "[^\\s||^>]*";
        String tagtype = tagProcessor.findInLine(typepattern);
        // at the first parameter OR at the end
        Token result = new Token(tagtype);
        
        String parampattern = "\\s*[^\\s]*=\"[^\\s]*\"";
        while (tagProcessor.hasNext())
        {    
            String nextparam = tagProcessor.findInLine(parampattern);
            if (nextparam != null)//tagProcessor.hasNext(parampattern))
            {
                //String allparams = tagProcessor.findInLine(parampattern);
                String [] paramValuePair = nextparam.split("=");
                if (paramValuePair.length == 2) // just in case
                    result.addAttribute(paramValuePair[0], paramValuePair[1].replace("\"", ""));
                else
                { 
                    // otherwise must be an error -- this should not happen
                    //System.out.println("not valid param " + nextparam);
                }
               
            }
            else // nothing relevant left but the closing > and some space
            {
                String leftover = tagProcessor.next(); // munch it and ignore
                //System.out.println("left over: " + leftover);
            }	
        }
        return result;
    }
        

}