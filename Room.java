import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author  Kanver Bains
 * @version 3.29.2016
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private String iName, iDescription;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    /**
     * Define an name from this itemDescripton.
     * @param iName The itemName.
     * @param iDescription the itemDescription.
     */
    public void setItem(String itemDescription, String itemName) {
        iName = itemName;
        iDescription = itemDescription;
    }
    /**
     * @return The long description of the item
     * (the one that was defined in the setItem).
     */    
    public String getItemLong()
    {
        return iDescription;
    }
    /**
     * @return the short description/name of the item
     */
    
    public String getItemShort(){
        return iName;
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription() {
        return "You are " + description + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * Also hides the hidden room so it wont print out the direction.
     * @return Details of the room's exits.
     */

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys) {
            if(exit == "secret"){
                returnString += "";
            }else{
                returnString += " " + exit;
            }
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
}

