/** BusObstacle class written by Tia Lowenthal 910903
 * for SWEN20003 project 1 */


import org.newdawn.slick.SlickException;


public class BusObstacle extends Sprite {


    /* constants */


    // screen width, in pixels
    private static final int SCREEN_WIDTH = 1024;

    // the speed the bus moves
    private static final double SPEED = 0.15;

    // tile width
    private static final int TILE_WIDTH = 48;


    /* all instance variables are inherited from class Sprite */


    /* methods */


    public BusObstacle(String imageSrc, float posX, float posY) throws SlickException{
        super(imageSrc, posX, posY);
    }

    public void update(int delta) {

        // these buses are going left
        if ((int) getY() / TILE_WIDTH % 2 == 1) {

            // check whether the bus is still in the screen
            if (getX() - (SPEED * delta) > 0) {
                setX((float) (getX() - SPEED * delta));
            }

            // if the bus goes off the screen, set its location to start on the other side
            else {
                setX((float) SCREEN_WIDTH);
            }
        }

        // these buses are going right
        else {

            // check whether the bus is still in the screen
            if (getX() + SPEED * delta < SCREEN_WIDTH) {
                setX((float) (getX() + SPEED * delta));
            }

            // if the bus goes off the screen, set its location to start on the other side
            else {
                setX(0);
            }
        }

        // update the bounding box of the bus
        this.getBoundingBox().setX(getX());
        this.getBoundingBox().setY(getY());
    }
}
