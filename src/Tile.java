import org.newdawn.slick.SlickException;
import utilities.BoundingBox;
import org.newdawn.slick.Image;

/**
 * Created by Tia L on 23/09/2018.
 */
public class Tile {

    private Position position;
    private Image image;
    private BoundingBox box;


    public Tile (String imageSrc, float x, float y) throws SlickException{
        position = new Position(x, y);
        image = new Image(imageSrc);
        box = new BoundingBox(image, x, y);
    }


    public void render(){
        image.drawCentered(position.getX(), position.getY());
    }


    public float getX(){
        return position.getX();
    }


    public boolean setX(float newX){
        return position.setX(newX);
    }


    public float getY(){
        return position.getY();
    }


    public boolean setY(float newY){
        return position.setY(newY);
    }
}
