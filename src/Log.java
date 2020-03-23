import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 23/09/2018.
 */
public class Log extends MovingGameObject implements Rideable {


    // the speed the bus moves
    private static final double SPEED = 0.1;


    public Log(String imageSrc, float x, float y, boolean direction) throws SlickException{
        super(imageSrc, x, y, direction, SPEED);
    }

    public double getSpeed(){
        return SPEED;
    }
}
