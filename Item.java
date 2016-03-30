import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * @author Kanver Bains 
 * @version 3.29.2016
 */

public class Item {
    private String itemDescription;
    //private HashMap<String, Item> itemList;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemDescription) {
        this.itemDescription = itemDescription;
    //    itemList = new HashMap<String, Item>();
    }
    
    public String getLongItemDescription(){
        return itemDescription;
    }
}
