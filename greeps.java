import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)


/**
 * A Greep is an alien creature that likes to collect tomatoes.
 * 
 * @author (your name here)
 * @version 0.1
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
     * Do what a greep's gotta do.
     */
    public void act()
    {             
        super.act();   // do not delete! leave as first statement in act().
        if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
            }
            if(!atShip()) {
                turnHome();
                move();
            }
        }
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if( tomatoes ==  null) {
            move();
            checkFood();
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
        if(atWater()) {
            turn(45);
        }
        if(atWorldEdge()) {
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
