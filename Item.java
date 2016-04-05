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
     * Create a Item described "itemDescription". Initially, it has
     * no itemDescription. "itemDescription" is something like 
     * "you found a cookie in the room"
     * @param itemDescription The items's description.
     */
    public Item(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    /**
     * @return the itemDescription of the item
     */
    public String getLongItemDescription(){
        return itemDescription;
    }
    /**
     * @return the itemName of the item
     */
    public String getShortItemDescription(){
        return itemName;
    }
}
