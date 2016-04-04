import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * @author Kanver Bains 
 * @version 3.29.2016
 */

public class Item {
    private String itemDescription;
    private String itemName;
    /**
     * Constructor for objects of class Item
     */
    public Item(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public String getLongItemDescription(){
        return itemDescription;
    }
    
    public String getShortItemDescription(){
        return itemName;
    }
}
