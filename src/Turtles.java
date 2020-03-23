import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 23/09/2018.
 */
public class Turtles extends MovingGameObject implements Rideable {

    // the speed the turtle moves
    private static final double SPEED = 0.085;

    private static final float TIME_VIS = 7000;
    private static final float TIME_INVIS = 2000;

    private boolean visibile;
    private float timeSinceVisSwap;

    public Turtles(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction, SPEED);
        visibile = true;
        timeSinceVisSwap = 0;
    }

    public void update(int delta){

        // these objects are going right
        if (getDirection()) {

            // check whether the object is still on the screen
            if (getX() + SPEED * delta < SCREEN_WIDTH + getImage().getWidth() / 2) {
                setX((float) (getX() + SPEED * delta));
            }

            // if the object goes off the screen, set its location to start on the other side
            else {
                setX(0 - getImage().getWidth() / 2);
            }
        }

        // these objects are going left
        else {
            // check whether the object is still on the screen
            if (getX() - (SPEED * delta) > 0 - getImage().getWidth() / 2) {
                setX((float) (getX() - SPEED * delta));
            }

            // if the object goes off the screen, set its location to start on the other side
            else {
                setX((float) (SCREEN_WIDTH + getImage().getWidth() / 2));
            }
        }

        // update the bounding box of the object
        this.getBoundingBox().setX(getX());
        this.getBoundingBox().setY(getY());

        timeSinceVisSwap += delta;
        if(visibile){
            if(timeSinceVisSwap > TIME_VIS){
                visibile = !visibile;
                timeSinceVisSwap = 0;
            }
        }
        else{
            if(timeSinceVisSwap > TIME_INVIS){
                visibile = !visibile;
                timeSinceVisSwap = 0;
            }
        }
    }


    public double getSpeed(){
        return SPEED;
    }


    public boolean getVisibility(){
        return visibile;
    }
}
