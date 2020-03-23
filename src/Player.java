/** Player class written by Tia Lowenthal 910903
 * for SWEN20003 project 1 */


import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;


public class Player extends Sprite {


    /* constants */

    // step size
    private static final int STEP_SIZE = 48;

    // lives constants
    private static final int INIT_NUM_LIVES = 3;
    private static final float LIFE_X = 24;
    private static final float LIFE_Y = 744;
    private static final float LIFE_SEPARATION = 32;
    private static final String LIFE_IMAGE = "assets/lives.png";


    /* instance variables */
    private ArrayList<Life> lives;


    /* methods */


    public Player(String imageSrc, float x, float y) throws SlickException {

        super(imageSrc, x, y);

        lives = new ArrayList<Life>();

        float xPos;
        for(int i = 0; i < INIT_NUM_LIVES; i++){
            xPos = LIFE_X + i * LIFE_SEPARATION;
            lives.add(new Life(LIFE_IMAGE, xPos, LIFE_Y));
        }
    }


    public void update(Input input) {

        // move player a distance of one tile in the direction of the key that is pressed

        // up key
        if (input.isKeyDown(Input.KEY_UP) & input.isKeyPressed(Input.KEY_UP)) {
            setY(getY() - STEP_SIZE);
        }

        // right key
        if (input.isKeyDown(Input.KEY_RIGHT) & input.isKeyPressed(Input.KEY_RIGHT)) {
            setX(getX() + STEP_SIZE);
        }

        // down key
        if (input.isKeyDown(Input.KEY_DOWN) & input.isKeyPressed(Input.KEY_DOWN)) {
            setY(getY() + STEP_SIZE);
        }

        // left key
        if (input.isKeyDown(Input.KEY_LEFT) & input.isKeyPressed(Input.KEY_LEFT)) {
            setX(getX() - STEP_SIZE);
        }

        // update the bounding box to the player's current position
        getBoundingBox().setX(getX());
        getBoundingBox().setY(getY());
    }


    public int numLives(){
        return lives.size();
    }


    public void addLife() throws SlickException{
        float xPos = LIFE_X + numLives() * LIFE_SEPARATION;
        lives.add(new Life(LIFE_IMAGE, xPos, LIFE_Y));
    }


    public void removeLife(){
        lives.remove(numLives() - 1);
        if(lives.size() == 0){
            System.exit(0);
        }
    }


    public void renderLives(){
        for(Life life: lives){
            life.render();
        }
    }
}