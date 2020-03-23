import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 24/09/2018.
 */
public class MovingGameObject extends Sprite {

    // screen width, in pixels
    public static final int SCREEN_WIDTH = 1024;

    private boolean direction;
    private double speed;

    public MovingGameObject(String imageSrc, float x, float y, boolean direction, double speed) throws SlickException{
        super(imageSrc, x, y);
        this.direction = direction;
        this.speed = speed;
    }


    public void update(int delta){

        // these objects are going right
        if (getDirection()) {

            // check whether the object is still on the screen
            if (getX() + speed * delta < SCREEN_WIDTH + getImage().getWidth() / 2) {
                setX((float) (getX() + speed * delta));
            }

            // if the object goes off the screen, set its location to start on the other side
            else {
                setX(0 - getImage().getWidth() / 2);
            }
        }

        // these objects are going left
        else {
            // check whether the object is still on the screen
            if (getX() - (speed * delta) > 0 - getImage().getWidth() / 2) {
                setX((float) (getX() - speed * delta));
            }

            // if the object goes off the screen, set its location to start on the other side
            else {
                setX((float) (SCREEN_WIDTH + getImage().getWidth() / 2));
            }
        }

        // update the bounding box of the object
        this.getBoundingBox().setX(getX());
        this.getBoundingBox().setY(getY());
    }


    public boolean getDirection(){
        return direction;
    }


    public void changeDirection(){
        direction = !direction;
    }


    public int getScreenWidth(){
        return SCREEN_WIDTH;
    }


    public void setDirection(boolean direction){
        this.direction = direction;
    }
}
