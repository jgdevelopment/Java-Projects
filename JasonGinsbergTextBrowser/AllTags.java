 

import java.util.Map;
import java.util.HashMap;

/**
 * 
 * This class holds an enumeration of all html tags known to the browser.
 * It is used to recognise commands as they are rendered.
 * 
 * borrowed and modified from Zuul project in BlueJ text.
 *
 * @author  Michael Kolling and David J. Barnes
 * @author J. Smith
 * @version 2006.03.30, November 2012
 */

public class AllTags
{
    // A mapping between a text token and a tag
    // associated with it.
    private Map<String, Tag> validTags;

    /**
     * Constructor - initialize the command word maps
     */
    public AllTags()
    {
        validTags = new HashMap<String, Tag>();
        for(Tag command : Tag.values()) 
        {
            if(command != Tag.UNKNOWN) 
            {
                validTags.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the Tag associated with token text 
     * @param tokenText The tag to look up.
     * @return The Tag correspondng to the tokenText or UNKNOWN
     *         if it is not a known tag
     */
    public Tag getTagMatch(String tokenText)
    {
        Tag command = validTags.get(tokenText);
        if(command != null) {
            return command;
        }
        else {
            return Tag.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid tag 
     * @return true if aString is a known tag, false if it isn't.
     */
    public boolean isTag(String aString)
    {
        return validTags.containsKey(aString);
    }

}