import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 23/09/2018.
 */
public class Bike extends MovingGameObject implements Murderous {


   /* constants */

    // the speed the bus moves
    private static final double SPEED = 0.2;

    // right turnaround point
    private static final float RIGHT_TURNAROUND = 1000;

    // left turnaround point
    private static final float LEFT_TURNAROUND = 24;



    /* methods */


    public Bike(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction, SPEED);
    }


    public void update(int delta){

        // these bikes are going right
        if (getDirection()) {
            if(getX() >= RIGHT_TURNAROUND){
                changeDirection();
            }
            else{
                setX((float) (getX() + SPEED * delta));
            }
        }

        // these race cars are going left
        else {
            // check whether the bus is still in the screen
            if (getX() <= LEFT_TURNAROUND){
                changeDirection();
            }
            else {
                setX((float) (getX() - SPEED * delta));
            }
        }

        // update the bounding box of the bus
        this.getBoundingBox().setX(getX());
        this.getBoundingBox().setY(getY());
    }
}
