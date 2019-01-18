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
    * Determines if the greep has previously bounced back
    * @author Russell Fair
    * @since 0.3
    */
    public boolean hasBounced(){
        return getFlag(1);
    }

    /**
    * Determines if the greep has previously bounced back
    * @author Russell Fair
    * @since 0.3
    */
    public boolean isBackwards(){
        return getFlag(2);
    }

    /**
    * sets the "has bounced" flag
    * @author Russell Fair
    * @since 0.3
    */
    public void setBounced(){
        if(!hasBounced())
            setFlag(1, true);

        else
            setFlag(1, false);
    }
    /**
    *sets the "is backwards" flag
    * @author Russell Fair
    * @since 0.3
    */
    public void setBackwards(){
        if(!isBackwards())
            setFlag(2, true);
        else 
            setFlag(2, false);
    }
    /**
    *unsets the "has bounced" flag
    * @author Russell Fair
    * @since 0.3
    */
    public void unsetBounced(){
        setFlag(1, false);
    }
    /**
    *unsets the "is backwards" flag
    * @author Russell Fair
    * @since 0.3
    */
    public void unsetBackwards(){
        setFlag(2, false);
    }

    /**
     * Do what a greep's gotta do.
    * @author Russell Fair
    * @since 0.1
    */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().
        if(atShip()){
            //reset
            setBrain();
        }
        if (carryingTomato()) {
            if(atShip()) {
                dropTomato();
                turn(180);
                resetBrain();
            }
            else {
                //need to do Deliver
                doDelivery();
            }
            move();//done once while having tomatoes
        }
        else {//not found tomatoes yet
        
            if(!atFoodPile()){
                //need to do Search
                doSearch();
                move();
            }
            else //at food pile...
            {
                //wait? 
                doWaiter();
                
                   
            }
           
        }
    }
    /**
    * the new method for waiting greeps (at food pile)
    * @author Russell Fair
    * @since 0.5
    */
    public void doWaiter(){

        
       
        int step = getMemory();
        if(step>=99){
            step=15;
        }
        else if(step>7){
            // spit("red");
            move();
        }
        else if(step<=7){
            //go to other side of food pile?
            turnAwayShip();
        }else{
            turnAwayShip();
        }

        if(pileEmpty()){
            doSearch();
            move();
        }
        if(step<=1)
            step=7;

        checkFood();
        loopCounter(step);

    }
    /**
    * the new method for searching
    * @author Russell Fair
    * @since 0.5
    */
    public void doSearch(){
        int step = getMemory();


        if(step==254){
            //first time...
            startOut();
            step = 250;
        }
           
        else if(atWorldEdge()){
            setBounced();
            redirect();
            setMemory(250);
        }
        else if(atWater()){
            redirect();
            setMemory(250);
        }
        else if(step > 220){
            //don't do anything for the first few moves or after redirecting...
        }
        

        else if(checkBreadcrumb()){
            //if sees breadcrumb
            followBreadcrumb();
        }
        else if(step%100==0){
           
            redirect();
            //fanOut();

        }
        else if(step%10==0){
            //spit("orange");
            //turnAwayShip();
            
        }else if(step < 205){
            if(atShip()){

            //prevent greep from getting stuck at ship.
                redirect();
            // redirect();
            // step=250;
            }
        
        }

        //prevent searching greeps from going below 200...
        if(step <= 200){
            step=250;
            turnAwayShip();
        }
            

        loopCounter(step);
    }

    /**
    * the new method for delivering
    * @author Russell Fair
    * @since 0.5
    */
    public void doDelivery(){
        int step = getMemory();



        if(step>=200||step<=16){
            //spit("red");
            turnHome();
            step =195;
        }
        else if(atWorldEdge()){
            setBounced();
            //setBackwards();
            //redirect();
            turnHome();
            //breadcrumb();
            step =195;
        }
        else if(atWater()){
            // setBounced();
            //breadcrumb();
            //breadcrumb();
            redirect();
            step =195;
        }
        // else if(checkBreadcrumb()&&!seePaint("purple")){
        //     bounceOffBreadcrumb();
        //     step =195;
        // }
        else if(step>=191){
            //do nothing for a few turns...
            //prevents breadcrumbs too close to the pile
            //turnHome();
        }
        else if(step%10==0){
            //do something special every 10 turns
            turnHome();
           
            
        }
        else if (step<175){
            //don't turn
            //turnHome();
            breadcrumb();
        }else{
            //breadcrumb();
        }

     if(step>199||step<=1)
            step=195;
        
        loopCounter(step);
    }

    /**
    * leaves the paint trail
    */
    public void breadcrumb(){
        if(carryingTomato()){
            if(atWater()){
                if(isBackwards()){
                    spit("red");
                }else{
                    spit("orange");
                }
            }else{
                spit("purple");
            }
            
        }
            
    }

    /**
    * checks the paint trail
    */
    public boolean checkBreadcrumb(){
        if(seePaint("purple")||seePaint("red")||seePaint("orange"))
           return true;
        else
            return false;
    }

    /**
    * follows the paint trail
    */
    public boolean followBreadcrumb(){
        if(seePaint("purple"))
            turnAwayShip();
        else if(seePaint("red"))
            goRight();
        else if (seePaint("orange"))
            goLeft();

        return false;
    }
    /**
    * stay off other greeps' paint trail
    */
    public void bounceOffBreadcrumb(){
        if(seePaint("red"))
            redirect();
        else if (seePaint("orange"))
            redirect();

    }


    /**
    * sets the memory to one less than previous
    */
    public void loopCounter(int step){
        if(step==1)
            step=255;

        setMemory(step-1);
    }

    /**
    * goes right or left
    */
    public void redirect(){

        // if(isBackwardFacing())
        //     spit("red");

        changeDirection(isBackwards());

    }

    /**
    * turn around
    */
    public void turnaround(){
        setRotation(getRotation() - 180);
    }

    public boolean smellsFood(){

        Object tomatoes = sniffXandY();

        if(null != tomatoes)
            return true;

        else 
            return false;
    }

    public Object sniffXandY(){

        int x =10;
        int y =10;
        
        int xcount = 0;
        int ycount = 0;

        Object tomatoes = null;
        
        while(xcount <= x){

            while(ycount<=y){
                tomatoes = sniffLocation(xcount, ycount);
                ycount++;
            }
            
            xcount++;
        }

        return tomatoes;
           
    }

    public Object sniffLocation(int x, int y){

        Object found = getOneObjectAtOffset(x, y, TomatoPile.class);

        if(null!=found)
            return found;

        return null;
    }

    /**
    * turn around
    */
    public void turnAwayShip(){
        turnHome();
        turn(180);
    }
    /**
    * change the direction based on if the greep is at water
    * 2 in 10 chance the greep will change direction more than 10 (30) degrees
    */
    public void changeDirection(boolean neg){

        int chance = Greenfoot.getRandomNumber(7);

        int angle = (atWorldEdge() || atShip()) ? 20+chance : 33;
        

       // angle = (chance%5==0)? angle*3: angle*1;
       //  if (chance ==5 || chance  ==10)
       //      spit("orange");

        if(neg){
            setRotation(getRotation() - angle);
            //spit("orange");  
        }
        else {
            //spit("red");
            setRotation(getRotation() + angle);
        }
    }
    /*
    * makes the greeps stay away from home while searching
    */
    public void fanOut(){
        int rando = Greenfoot.getRandomNumber(180);
        turnHome();
        turn(rando);

    }
    

    /**
    * sets the greeps memory back to "nothing"
    * @author Russell Fair
    * @since 0.1
    */
    public void resetBrain()
    {
        setMemory(253);
        setFlag(1, false);
        setFlag(2, true);
    }

    /**
    * sets the greeps initial memory to "nothing"
    * @author Russell Fair
    * @since 0.1
    */
    public void setBrain()
    {
        if(getMemory()==0)
            setMemory(254);

        setFlag(1, false);
        int rand = Greenfoot.getRandomNumber(2);

        if(rand%2==0)
            setFlag(2, true);
        else
            setFlag(2, false);
    }
    /**
    * go around something, to the right
    * @author Russell Fair
    * @since 0.2
    */
    public void goRight()
    {
       changeDirection(true);
    }

    /**
    * go around something, to the left
    * @author Russell Fair
    * @since 0.2
    */
    public void goLeft()
    {
       changeDirection(false);
    }


    /** checks if greep is loitering at a food pile
    * @author Russell Fair
    * @since 0.1
    */
    public boolean atFoodPile()
    {
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
            if(tomatoes != null) 
                return true;

            return false;
    }

    /**
    * the new method for delivering
    * @author Russell Fair
    * @since 0.5
    */
    public void startOut(){
        World world = getWorld();

        int x = getX();
        int y = getY();

        int wx = world.getWidth();
        int wy = world.getHeight();

        int rotate = 0;

        if(x < wx/2)
            rotate = rotate+66;

        else 
            rotate = rotate+233;

        // if(y < wy/2)
        //     rotate = rotate - 0;

        // else 
        //     rotate = rotate - 180;

        setRotation(rotate);
    }
    
    /** checks if the pile is finished
    * @author Russell Fair
    * @since 0.1
    */
    public boolean pileEmpty()
    {
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
            if(tomatoes != null) 
                return false;

            return true;
    }
    /**
     * Is there any food here where we are? If so, try to load some!
     */
    public void checkFood()
    {
        // check whether there's a tomato pile here
        TomatoPile tomatoes = (TomatoPile) getOneIntersectingObject(TomatoPile.class);
        if(tomatoes != null) {
            giveTomato();
            // Note: this attempts to load a tomato onto *another* Greep. It won't
            // do anything if we are alone here.
        }
        else{
            //spit("blue");
        }
    }


    /**
     * Load a tomato onto *another* creature. This works only if there is another creature
     * and a tomato pile present, otherwise this method does nothing.
     */
    public void giveTomato()
    {
        loadTomato();
        //if(!seePaint("red"))
        //spit("red");
    }

    /**
    * turn around silly greep
    * @author Russell Fair
    * @since 0.2
    */
    public void bounceBack()
    {
        turn(180-Greenfoot.getRandomNumber(90));
        //a near total turn around, but not entirely.
    }

    /**
    * go away silly greep
    * @author Russell Fair
    * @since 0.2
    */
    public void bounce()
    {
       int rando = Greenfoot.getRandomNumber(180)-Greenfoot.getRandomNumber(180);
       turn(rando);
    }


    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Russell's Greeps";  // write your name here!
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
