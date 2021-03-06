import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)


/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @ author Felix Patryjas (@felix-development), Leo Mühlenhoff (@lm-development)
 * @ version 0.2 release
 * @ Current State: Working in progress
 *
 */


public class Greep extends Creature
{  
    
    
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!
 
    
    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }
 
    
    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }
 
    
     /**
     * Do what a greep's gotta do.Our edited source code is "WIP", so don't expect it to work properly. 
     * The total score seems pretty random but it should be always something between 30 and 50 tomatoes.
     * Map 1 & Map 2 are working fine while there are still problems on Map 3 with passing the Water and World Edges.
     * You have to run the scenario a few times if you want to debug the source code. 
     * 
     * Current High Score: 15:39:  [ 30 24  9]  63  -- Felix
     */
    public void act() 
    {
        super.act();   // Do not delete! Leave as first statement in act().
        
        if(onFood()) {
            checkFood();
            boolean carryingTomato = true; // Set flag carryingTomato true when a tomato was found.
        }
        
        if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
                move();
                checkFood();
                onFood();
            }
            if(!atShip()) {
                turnHome();
                if(atWater()) {
                    turn(145);   // Turn 145° in order to catch some tomatoes on Map 3.
                }
                if(isAtEdge()) {
                    turn(145);
                }                    
                move();
            }
        }
        
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        
        if( tomatoes ==  null) {
            move();
            checkFood();
            onFood();
        }
        else {
            return;
        }
        
        if(randomChance(50)) {
            this.setRotation(getRotation()+10);
        }
        else {
            this.setRotation(getRotation()-10);
        }
        
        if(atWater()) { // This needs to be set for general actions.
            turn(45);
        }
        
        if(isAtEdge()) {
            turn(45);
        }
    }

    
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            loadTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
    }


    /**
      * Is there any food here where we are? 
      */
    public boolean onFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        return(tomatoes != null);
    }
    
    
    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Felix";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need 
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    } 
    
    
}
