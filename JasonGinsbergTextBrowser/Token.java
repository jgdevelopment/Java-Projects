import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * class Token represents a tag and argument list 
 * 
 * Dec. 2012 Update: <br>
 * <li> Added param/value pairs should add a list of attributes/value pairs</li>
 * 
 * December 2012<br>
 * <li>     store types as uppercase.</li>
 * <p>
 * Discussion of parts of the tag
 * </p>
 * <p>
 * <li>tag:  name of the tag</li>
 * <li>attributes:  a list of attributes and values for the tag if they exist</li>
 * </p>
 * 
 * ex.  the tag <html> would have a zero length parameter list, with tag=HTML
 * 
 * @author J. Smith 
 * @version November 2012
 */
public class Token
{
    private static AllTags tagMap;
    private Tag tag;
    private List<AttributePair> attributes;

    /**
     *  Create a new token with an empty attribute list and given type
     *  @param type The token tag type
     */
    public Token (String type)
    {
        tagMap = new AllTags();
        tag = tagMap.getTagMatch(type.toUpperCase());
        attributes = new ArrayList<AttributePair> ();
    }

    /**
     * This method is available primarily to assist in the non-list
     * stage of the CS2 assignment.
     * 
     * @return the first AttributePair or null if there are no attributes
     */
    public AttributePair getFirstAttribute ()
    {
        if (attributes.size() > 0)
            return attributes.get(0);
        else
            return null;
    }

    /**
     * @return an iterator for the parameter list
     */
    public Iterator<AttributePair> getattributes ()
    {
        return attributes.iterator();
    }

    /**
     * @return the name of the tag
     */
    public Tag getTag ()
    {
        return tag;
    }

    /**
     * Add a new attribute to the end of the parameter list
     * @param name The name of the parameter
     * @param value The value of the parameter
     */
    public void addAttribute (String name, String value)
    {
        attributes.add(new AttributePair(name, value));
    }

    /**
     * @return a string representing the tag name and it's associated list of attributes
     */
    public String toString ()
    {
        // will always be at least one parameter with the value for the tag
        String result = "tag=" + tag;
        if (attributes.size() > 0)
        {
            result += " params (";
            for (AttributePair pair : attributes)
            {
                result += pair + " ";
            }
            result += ")";
        }
        return result;        
    }
}