import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 23/09/2018.
 */
public class Bus extends MovingGameObject implements Murderous {

    /* constants */


    // screen width, in pixels
    private static final int SCREEN_WIDTH = 1024;

    // the speed the bus moves
    private static final double SPEED = 0.15;



    /* methods */


    public Bus(String imageSrc, float x, float y, boolean direction) throws SlickException {
        super(imageSrc, x, y, direction, SPEED);
    }

    public double getSpeed(){
        return SPEED;
    }
}
