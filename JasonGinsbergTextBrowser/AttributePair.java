
/**
 * class AttributePair
 * represents a attributeeter and value pair
 * 
 * @author J. Smith 
 * @version December 2012
 */
public class AttributePair
{
    private String attribute;
    private String value;

    /**
     * Constructor a new attribute pair object with the given name and value
     * @attribute name the name of the attribute
     * @attribute value the value for the attribute
     */
    public AttributePair(String name, String value)
    {
        attribute = name;
        this.value = value;
    }

    /**
     * @return the attribute name
     */
    public String getAttribute()
    {
        return attribute;
    }
    
    /**
     * @return the attribute value
     */
    public String getValue()
    {
        return value;
    }
    
    /**
     * @return a string representation of the attribute pair
     */
    public String toString ()
    {
        return attribute + "=" + value;
    }
}