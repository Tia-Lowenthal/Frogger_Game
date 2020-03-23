/**
 * Created by Tia L on 23/09/2018.
 */
public class Position {

    // screen width, in pixels
    public static final int SCREEN_WIDTH = 1024;

    // screen height, in pixels
    public static final int SCREEN_HEIGHT = 768;

    private float x, y;

    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public boolean setX(float x){
        if(x <= SCREEN_WIDTH && x >= 0){
            this.x = x;
            return true;
        }
        return false;
    }

    public float getY(){
        return y;
    }

    public boolean setY(float y){
        if(y <= SCREEN_HEIGHT && y >= 0){
            this.y = y;
            return true;
        }
        return false;
    }
}
