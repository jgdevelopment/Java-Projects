 

/**
 * Definition of constants for all the valid Tags for the browser
 * associated with a string
 * 
 * based on BlueJ text project
 * 
 * @author Michael Kolling and David J. Barnes
 * @author modified by J. Smith
 * @version 2006.03.30, November 2012
 */
public enum Tag
{
    // A value for each command word along with its
    // corresponding user interface string.
    HTML("HTML"), ANCHOR("A"), CLOSE_ANCHOR("/A"),
    P("P"), CLOSE_P("/P"), DOCTYPE("!DOCTYPE"), 
    TITLE("TITLE"), CLOSE_TITLE("/TITLE"), 
    BODY("BODY"), CLOSE_BODY("/BODY"), 
    HEAD("HEAD"), CLOSE_HEAD("/HEAD"),
    LI("LI"), CLOSE_LI("/LI"),
    H1("H1"), CLOSE_H1("/H1"),
    H2("H2"), CLOSE_H2("/H2"),
    H3("H3"), CLOSE_H3("/H3"),
    H4("H4"), CLOSE_H4("/H4"),
    H5("H5"), CLOSE_H5("/H5"),
    H6("H6"), CLOSE_H6("/H6"),
    DIV("DIV"), CLOSE_DIV("/DIV"),
    HR("HR"), CLOSE_HR("/HR"),
    OL("OL"), CLOSE_OL("/OL"),
    SPACER("SPACER"), CLOSE_SPACER("/SPACER"),
    UL("UL"), CLOSE_UL("/UL"),
    SCRIPT("SCRIPT"), CLOSE_SCRIPT("/SCRIPT"),
    BR("BR"), TEXT("TEXT"),BLOCKQUOTE("BLOCKQUOTE"),CLOSE_BLOCKQUOTE("/BLOCKQUOTE"), UNKNOWN("?");
    
    
    // The command string.
    private String tagString;
    
    /**
     * Initialize with the corresponding command word.
     * @param tagString The tag string
     */
    Tag(String tagString)
    {
        this.tagString = tagString;
    }
    
    /**
     * @return The tag given as a string.
     */
    public String toString()
    {
        return tagString;
    }
}