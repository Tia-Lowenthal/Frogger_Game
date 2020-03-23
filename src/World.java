/** World class written by Tia Lowenthal 910903
 * for SWEN20003 project 1 */


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class World {


	/* constants */


	// screen width, in pixels
	private static final int SCREEN_WIDTH = 1024;

	// tile width
	private static final int TILE_WIDTH = 48;

	// the y coordinates of the grass rows
	private static final int GRASS_ROW_1 = 384;
	private static final int GRASS_ROW_2 = 672;

	// initial frog position
	private static final int PLAYER_INIT_POS_X = 512;
	private static final int PLAYER_INIT_POS_Y = 720;

	// the upper and lower y boundaries of the water
	private static final float WATER_POSY_MIN = 48;
	private static final float WATER_POSY_MAX = 336;


	/* instance variables */


	// the grass tile
	private Image grass;

	// the player (frog)
	private Player player;

	// the y positions of the bus lanes
	private float[] BUSES_Y_POS = {432, 480, 528, 576, 624};

	// the bus offsets
	private float[] offsets = {48, 0, 64, 128, 250};

	// the bus separations
	private double[] separations =
			{6.5 * TILE_WIDTH, 5 * TILE_WIDTH, 12 * TILE_WIDTH, 5 * TILE_WIDTH, 6.5 * TILE_WIDTH};

	// total number of bus lanes
	private int numBusLanes = offsets.length;

	// the 2D array of buses(an array of bus lanes)
	private BusObstacle[][] buses;

	// the array of water tiles
	private WaterTile[] waterTiles;

	// number of water tile rows = (y min - y max) / tile width
	private double numWaterRows = Math.ceil((WATER_POSY_MAX - WATER_POSY_MIN) / (float)TILE_WIDTH);

	// screen width / tile width rounded up, plus 1 so there isn't a half tile black gap on the end
	private double numWaterTilesInRowD = Math.ceil((SCREEN_WIDTH / TILE_WIDTH) + 1);
	private int numWaterTilesInRowI = (int) numWaterTilesInRowD;

	// number of rows * number of tiles per row
	private double totalWaterTilesD = numWaterRows * numWaterTilesInRowD;
	private int totalWaterTilesI = (int)totalWaterTilesD;


	/* New shit for p2 */




	/* methods */


	public World() throws SlickException{

		grass = new Image("assets/grass.png");
		player = new Player("assets/frog.png", PLAYER_INIT_POS_X, PLAYER_INIT_POS_Y);
		buses = new BusObstacle[offsets.length][1];


		// generate all buses
		int numBuses;
		for(int i = 0; i < numBusLanes; i++){

			// calculate the number of buses in the lane
			numBuses = (int)((SCREEN_WIDTH - offsets[i]) / separations[i]);

			// create an array of the correct length
			buses[i] = new BusObstacle[numBuses];

			// instantiate buses
			for(int j = 0; j < numBuses; j++){

				// generate buses with x position = the offset plus the bus number * the separation
				buses[i][j] = new BusObstacle("assets/bus.png",
						(float)(offsets[i]+ j*separations[i]), BUSES_Y_POS[i]);
			}
		}


		// create the water tile array
		waterTiles = new WaterTile[totalWaterTilesI];


		// generate all water tiles
		for(int i = 0; i < numWaterRows; i++){
			for(int j = 0; j < numWaterTilesInRowI; j++){
				waterTiles[i * numWaterTilesInRowI + j] = new WaterTile("assets/water.png",
						j * TILE_WIDTH, WATER_POSY_MIN + i * TILE_WIDTH);
			}
		}
	}



	public void update(Input input, int delta) {


		// update the player (frog)
		player.update(input);


		// move the buses
		for(int i = 0; i < numBusLanes; i++){
			for(int j = 0; j < buses[i].length; j++){
				buses[i][j].update(delta);
			}
		}


		// check for bus - player intersections
		for(int i = 0; i < numBusLanes; i++){
			for(int j = 0; j < buses[i].length; j++){

				// check if player bounding box intersects with bus bounding box
				if(this.player.getBoundingBox().intersects(buses[i][j].getBoundingBox())){
					System.exit(0);
				}
			}
		}


		// check for water - player intersections
		for(int i = 0; i < totalWaterTilesI; i++){

			// check if player bounding box intersects with water tile bounding box
			if(this.player.getBoundingBox().intersects(waterTiles[i].getBoundingBox())){
				System.exit(0);
			}
		}
	}



	public void render(Graphics g) {


		// render the grass tiles
		for(int x = 0; x <= SCREEN_WIDTH; x += TILE_WIDTH){
			grass.drawCentered(x,GRASS_ROW_1);
		}
		for(int x = 0; x <= SCREEN_WIDTH; x += TILE_WIDTH){
			grass.drawCentered(x,GRASS_ROW_2);
		}


		// render the water tiles
		for(int i = 0; i < totalWaterTilesI; i++){
			waterTiles[i].render();
		}


		// render the player (frog)
		player.render();


		// render the buses
		int numBuses;
		for(int i = 0; i < offsets.length; i++){
			numBuses = (int)((SCREEN_WIDTH - offsets[i]) / separations[i]);
			for(int j = 0; j < numBuses; j++){
				buses[i][j].render();
			}
		}
	}
}