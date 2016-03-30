/**
 * @author  Kanver Bains
 * @version 3.29.2016
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Item currentItem;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(){
        Room secretRoom, kitchen, diningRoom, deck, library, start, homeTheather, bathroom, bedroom, treasure;
        Room livingRoom, loft, wineCellar, foyer, lounge, gym, masterBedroom, guardRoom, finish, teleport;
        
        // create the rooms
        secretRoom = new Room("in the Secret Room. shhhhh dont tell anyone");
        kitchen = new Room("in the Kitchen");
        diningRoom = new Room("in the Dining Room");
        deck = new Room("on the Deck");
        library = new Room("in the Library");
        start = new Room("in the Starting Room");
        homeTheather = new Room("in the Home Theather");
        bathroom = new Room("in the Bathroom");
        bedroom = new Room("in the Bedroom");
        treasure = new Room("in the Treasure Room");
        livingRoom = new Room("in the Living Room");
        loft = new Room("in the Loft");
        wineCellar = new Room("in the Wine Cellar");
        foyer = new Room("in the Foyer");
        lounge = new Room("in the Lounge");
        gym = new Room("in the Gym");
        masterBedroom = new Room("in the Master Bedroom");
        guardRoom = new Room("in the Guarded Room");
        finish = new Room("in the final room! CONGRATS YOU FINISHED THE GAME!");
        teleport = new Room("in the Teleporter Room. Teleported Back to Start Room");
        
        // initialise room exits
        secretRoom.setExit("north", kitchen);
        secretRoom.setItem("Hint.. You need to find a cookie for the Guard");

        kitchen.setExit("northwest", start);
        kitchen.setExit("west", diningRoom);
        kitchen.setExit("south", secretRoom);
        kitchen.setItem("There is a moldy peice of toast. Better leave that alone");
        
        diningRoom.setExit("north", start);
        diningRoom.setExit("east", kitchen);
        diningRoom.setItem("Nothing really to see here");

        deck.setExit("northeast", start);
        deck.setExit("north", library);
        deck.setItem("The deck is made of wood.");
        
        start.setExit("north", loft);
        start.setExit("northeast", livingRoom);
        start.setExit("east", homeTheather);
        start.setExit("southeast", kitchen);
        start.setExit("south", diningRoom);
        start.setExit("southwest", deck);
        start.setExit("west", library);
        start.setExit("northwest" , bedroom);
        start.setItem("This is the starting room. No items here");
        
        homeTheather.setExit("west" , start);
        homeTheather.setExit("east", bathroom);
        homeTheather.setItem("You seea nice looing theather set up in this room");
        
        bathroom.setExit("west", homeTheather);
        bathroom.setItem("You find a peice of poop on the floor. You decide to not touch it.");
        
        bedroom.setExit("southeast", start);
        bedroom.setItem("You find a comfy looking bed");
        
        treasure.setExit("south", wineCellar);
        treasure.setItem("You find a cookie and pick it up!");
        
        livingRoom.setExit("southwest", start);
        livingRoom.setExit("north", gym);
        livingRoom.setExit("northeast", wineCellar);
        livingRoom.setItem("You find a Sports Illustrated magazine. You decide to leave it alone");
        
        loft.setExit("south", start);
        loft.setExit("northwest", foyer);
        loft.setItem("No item here");
        
        wineCellar.setExit("north", treasure);
        wineCellar.setExit("southwest", livingRoom);
        wineCellar.setItem("You see an expensive looking wine collection.");
        
        foyer.setExit("south", bedroom);
        foyer.setExit("north", masterBedroom);
        foyer.setExit("southeast", loft);
        foyer.setItem("No itmes to be found here");
        
        lounge.setExit("north", guardRoom);
        lounge.setExit("east", gym);
        lounge.setItem("You find a cigar on the floor. You decide to leave it alone.");
        
        gym.setExit("south", livingRoom);
        gym.setExit("west", lounge);
        gym.setItem("You see a well equipt home gym");
        
        masterBedroom.setExit("south", foyer);
        masterBedroom.setExit("east", guardRoom);
        masterBedroom.setItem("Nothing out of the ordinary here");
        
        guardRoom.setExit("north", teleport);
        guardRoom.setExit("east", finish);
        guardRoom.setExit("south", lounge);
        guardRoom.setExit("west", masterBedroom);
        guardRoom.setItem("The guard demands you give him a cookie or he will not move");
        
        finish.setExit("west", guardRoom);
        
        teleport.setExit("south", guardRoom);
        
        library.setExit("east", start);
        library.setExit("south", deck);
        library.setItem("You see shelves to the ceiling filled with books.");
        
        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to the Haunted House");
        System.out.println("This haunted house is a 'new', incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            
            case LOOK:
                lookCommand(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
    
    private void lookCommand(Command command) {
        String treasure, secretRoom, bathroom, wineCellar, lounge, guardRoom, start;
        treasure ="You picked up the Cookie";
        secretRoom = "Hint.. You must first visit the guard room to find out what the guard wants";
        bathroom = "You see a piece of poop on the floor. You probably should not touch it";
        wineCellar = "There is a rather expensive collection of wine here";
        lounge = "You see a cigar on the floor";
        guardRoom = "The guard wants you to get him a cookie to get past";
        start = "you are in the start. There are no items here";
        
        System.out.println(currentRoom.getItem());
        
        /* if(currentRoom == treasure){            
            System.out.println(treasure);
        }else if(secretRoom == secretRoom){
            System.out.println(currentItem.getLongItemDescription());
        }else if(bathroom == bathroom){
            System.out.println(currentItem.getLongItemDescription());
        }else if(wineCellar == wineCellar){
            System.out.println(currentItem.getLongItemDescription());
        }else if(lounge == lounge){
            System.out.println(currentItem.getLongItemDescription());
        }else if(guardRoom == guardRoom){
            System.out.println(currentItem.getLongItemDescription());
        }else if(start == start){
            System.out.println(currentItem.getLongItemDescription());
        }else{
            System.out.println("IDK");
        }
        */
        
    }
}
