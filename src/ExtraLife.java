import org.newdawn.slick.SlickException;

/**
 * Created by Tia L on 23/09/2018.
 */
public class ExtraLife extends MovingGameObject {
    private static double SPEED;
    private static final float STEP_SIZE = 48;

    private static final float TIME_VISIBLE = 11000;

    private static final int MIN_TIME = 1000;
    private static final int MAX_TIME = 3000;
    private static final int MOVE_TIME = 2000;

    private boolean visible;
    private double timeInvisible;
    private float timeSinceVisSwap;
    private float timeSinceStep;

    private float maxRight;
    private float prevMaxRight;
    private float maxLeft;
    private float prevMaxLeft;

    private boolean jumpingDirection;


    public ExtraLife(String imageSrc, float x, float y, boolean direction, float maxRight, float maxLeft) throws SlickException{
        super(imageSrc, x, y, direction, SPEED);
        visible = false;
        timeInvisible = MIN_TIME + Math.random() * (MAX_TIME - MIN_TIME);
        timeSinceVisSwap = 0;
        timeSinceStep = 0;
        this.maxRight = maxRight;
        this.maxLeft = maxLeft;
        jumpingDirection = true;
    }

    public void update(int delta){

        timeSinceStep += delta;
        // these objects are going right
        if (getDirection()) {

//            if(prevMaxRight > maxRight){
//                setX(0 - getImage().getWidth() / 2);
//            }
//
//            if(getX() > App.SCREEN_WIDTH + getImage().getWidth() / 2){
//                setX(0 - getImage().getWidth() / 2);
//            }
//
//            else if (getX() + SPEED * delta < maxRight) {
//                setX((float) (getX() + SPEED * delta));
//            }
            if(getX() > maxLeft && getX() < maxRight) {
                if (timeSinceStep < MOVE_TIME) {

                    setX((float) (getX() + SPEED * delta));

                }

                // if the object goes off the screen, set its location to start on the other side
                else {
                    timeSinceStep = 0;
                    if (jumpingDirection) {
                        if (getX() + STEP_SIZE < maxRight) {
                            setX(getX() + STEP_SIZE);
                        } else {
                            jumpingDirection = !jumpingDirection;
                            setX(getX() - STEP_SIZE);
                        }
                    } else {
                        if (getX() - STEP_SIZE > maxLeft) {
                            setX(getX() - STEP_SIZE);
                        } else {
                            jumpingDirection = !jumpingDirection;
                            setX(getX() + STEP_SIZE);
                        }
                    }

                }
                if(getX() > App.SCREEN_WIDTH + getImage().getWidth() / 2){
                    setX(0 - getImage().getWidth() / 2);
                }
            }
        }

        // these objects are going left
        else {

//            if (prevMaxLeft < maxLeft) {
//                setX(App.SCREEN_WIDTH + getImage().getWidth() / 2);
//            }
//
//            if(getX() < 0 - getImage().getWidth() / 2){
//                setX(App.SCREEN_WIDTH + getImage().getWidth() / 2);
//            }
//            // check whether the object is still on the screen
//            else if (getX() - (SPEED * delta) > maxLeft) {
//                setX((float) (getX() - SPEED * delta));
//            }
//
//            // if the object goes off the screen, set its location to start on the other side
//            else {
//                changeDirection();
//            }
            if(getX() > maxLeft && getX() < maxRight) {
                if (timeSinceStep < MOVE_TIME) {
                    setX((float) (getX() - SPEED * delta));
                } else {
                    timeSinceStep = 0;
                    if (jumpingDirection) {
                        if (getX() + STEP_SIZE < maxRight) {
                            setX(getX() + STEP_SIZE);
                        } else {
                            jumpingDirection = !jumpingDirection;
                            setX(getX() - STEP_SIZE);
                        }
                    } else {
                        if (getX() - STEP_SIZE > maxLeft) {
                            setX(getX() - STEP_SIZE);
                        } else {
                            jumpingDirection = !jumpingDirection;
                            setX(getX() + STEP_SIZE);
                        }
                    }

                }

                if(getX() < 0 - getImage().getWidth() / 2){
                    setX(App.SCREEN_WIDTH + getImage().getWidth() / 2);
                }
            }

            //if(getX() < maxLeft || getX() > maxRight){



        }

        // update the bounding box of the object
        this.getBoundingBox().setX(getX());
        this.getBoundingBox().setY(getY());

        timeSinceVisSwap += delta;
        if(visible){
            if(timeSinceVisSwap > TIME_VISIBLE){
                visible = !visible;
                timeSinceVisSwap = 0;
                timeInvisible = MIN_TIME + Math.random() * (MAX_TIME - MIN_TIME);
            }
        }
        else{
            if(timeSinceVisSwap > timeInvisible){
                visible = !visible;
                timeSinceVisSwap = 0;
            }
        }
    }

    public void setRightMax(float max){
        prevMaxRight = maxRight;
        maxRight = max;
    }


    public void setLeftMax(float max){
        prevMaxLeft = maxLeft;
        maxLeft = max;
    }


    public boolean getVisibility(){
        return visible;
    }


    public void setSpeed(double speed){
        SPEED = speed;
    }

}
