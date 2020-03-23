/** WaterTile class written by Tia Lowenthal 910903
 * for SWEN20003 project 1 */


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


public class WaterTile {


    /* constants */


    /* screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;

    /* screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;


    /* instance variables */

    private float posX;
    private float posY;
    private Image image;
    private BoundingBox boundingBox;

    /* methods */


    public WaterTile(String imageSrc, float x, float y) throws SlickException{
        setX(x);
        setY(y);
        image = new Image(imageSrc);
        boundingBox = new BoundingBox(image, x, y);
    }


    public void render(){
        image.drawCentered(getX(), getY());
    }


    public float getX(){
        return posX;
    }


    public void setX(float x){
        if(x >= 0 && x <= SCREEN_WIDTH) {
            posX = x;
        }
    }


    public float getY(){
        return posY;
    }


    public void setY(float y){
        if(y >= 0 && y <= SCREEN_HEIGHT) {
            posY = y;
        }
    }


    public Image getImage(){
        return image;
    }


    public BoundingBox getBoundingBox(){
        return boundingBox;
    }
}
