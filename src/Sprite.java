/** Sprite class written by Tia Lowenthal 910903
 * for SWEN20003 project 1 */


import org.newdawn.slick.*;
import utilities.BoundingBox;


public class Sprite {

	/* instance variables */

	private float x;
	private float y;
	private Image image;
	private BoundingBox boundingBox;


	/* methods */


	public Sprite(String imageSrc, float x, float y) throws SlickException{
		// Why would the constructor need a path to an image, and a coordinate?
		this.x = x;
		this.y = y;
		image = new Image(imageSrc);
		boundingBox = new BoundingBox(image, x, y);
	}


	public void render() {
		image.drawCentered(x, y);
	}


	public void contactSprite(Sprite other) {
		// Should be called when one sprite makes contact with another.
	}


	public float getX(){
		return x;
	}


	public void setX(float newX){
		x = newX;
	}


	public float getY(){
		return y;
	}


	public void setY(float newY){
		y = newY;
	}


	public Image getImage(){
		return image;
	}


	public BoundingBox getBoundingBox(){
		return boundingBox;
	}


	public void setBoundingBox(BoundingBox newBox){
		boundingBox = newBox;
	}
}