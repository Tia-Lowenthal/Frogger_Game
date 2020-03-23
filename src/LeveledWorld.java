import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Tia L on 24/09/2018.
 */
public class LeveledWorld {

    private static final String FIRST_LEVEL = "0";
    private static final String SECOND_LEVEL = "1";

    private static final int PLAYER_INIT_POS_X = 512;
    private static final int PLAYER_INIT_POS_Y = 720;

    private static final int TILE_WIDTH = 48;


    private static final int NUM_TARGETS = 5;
    private static final int TARGET1 = 120;
    private static final int TARGET2 = 312;
    private static final int TARGET3 = 504;
    private static final int TARGET4 = 696;
    private static final int TARGET5 = 888;



    private static int randomLogIndex;

    private ArrayList<Murderous> murderous;
    private ArrayList<Rideable> rideables;
    private ArrayList<Solid> solids;
    private ArrayList<Grass> grass;
    private Player player;
    private ExtraLife extraLife;
    private boolean extraLifevisible;
    private ArrayList<Player> filledTargets;


    public LeveledWorld() throws SlickException{
        murderous = new ArrayList<Murderous>();
        rideables = new ArrayList<Rideable>();
        solids = new ArrayList<Solid>();
        grass = new ArrayList<Grass>();
        player = new Player("assets/frog.png", PLAYER_INIT_POS_X, PLAYER_INIT_POS_Y);
        filledTargets = new ArrayList<Player>();
        createLevel(FIRST_LEVEL);
        generateExtraLife();
    }


    public void update(Input input, int delta) throws SlickException{

        float playerCurrX = player.getX();
        float playerCurrY = player.getY();

        player.update(input);

        boolean blocked = false;
        for(Solid object: solids){
            if(object instanceof Bulldozer){

                ((MovingGameObject) object).update(delta);

                if(player.getBoundingBox().intersects(((Bulldozer) object).getBoundingBox())) {
                    if(player.getX() > ((Bulldozer) object).getX() + ((Bulldozer) object).getImage().getWidth() / 2) {
                        player.setX((float) (player.getX() + ((Bulldozer) object).getSpeed() * delta));
                        if(player.getX() + player.getImage().getWidth()/2 > App.SCREEN_WIDTH
                                || player.getX() - player.getImage().getWidth() / 2 < 0){
                            killPlayer();
                        }
                    }
                    else{
                        blocked = true;
                    }
                }

            }

            else if(object instanceof Tree){
                if(player.getBoundingBox().intersects(((Tree) object).getBoundingBox())){
                    blocked = true;
                }
            }
        }

        boolean riding = false;
        for(Rideable object: rideables){

            if(object instanceof Log){
                ((MovingGameObject) object).update(delta);
                if(player.getBoundingBox().intersects(((Log) object).getBoundingBox())){
                    if(((Log) object).getDirection()) {
                        player.setX((float) (player.getX() + ((Log) object).getSpeed() * delta));
                    }
                    else{
                        player.setX((float) (player.getX() - ((Log) object).getSpeed() * delta));
                    }
                    if(player.getX() + player.getImage().getWidth()/2 > App.SCREEN_WIDTH
                            || player.getX() - player.getImage().getWidth() / 2 < 0){
                        killPlayer();
                    }
                    riding = true;
                }
                if(rideables.indexOf(object) == randomLogIndex){

                }
            }
/*
            else if(object instanceof LongLog){
                ((MovingGameObject) object).update(delta);
                if(player.getBoundingBox().intersects(((LongLog) object).getBoundingBox())){
                    if(((LongLog) object).getDirection()) {
                        player.setX((float) (player.getX() + ((LongLog) object).getSpeed() * delta));
                    }
                    else{
                        player.setX((float) (player.getX() - ((LongLog) object).getSpeed() * delta));
                    }
                    if(player.getX() + player.getImage().getWidth()/2 > App.SCREEN_WIDTH
                            || player.getX() - player.getImage().getWidth() / 2 < 0){
                        killPlayer();
                    }
                    riding = true;
                }
            }
*/
            else if(object instanceof Turtles){
                ((MovingGameObject) object).update(delta);
                if(((Turtles) object).getVisibility())
                    if(player.getBoundingBox().intersects(((Turtles) object).getBoundingBox())){
                        if(((Turtles) object).getDirection()) {
                            player.setX((float) (player.getX() + ((Turtles) object).getSpeed() * delta));
                        }
                        else{
                            player.setX((float) (player.getX() - ((Turtles) object).getSpeed() * delta));
                        }
                        if(player.getX() + player.getImage().getWidth()/2 > App.SCREEN_WIDTH
                                || player.getX() - player.getImage().getWidth() / 2 < 0){
                            killPlayer();
                        }
                        riding = true;
                    }
            }
        }


        for(Murderous object: murderous){

            if(object instanceof Bus){
                ((MovingGameObject) object).update(delta);

                if(player.getBoundingBox().intersects(((Bus) object).getBoundingBox())){
                    killPlayer();
                }
            }

            else if(object instanceof RaceCar){
                ((MovingGameObject) object).update(delta);

                if(player.getBoundingBox().intersects(((RaceCar) object).getBoundingBox())){
                    killPlayer();
                }
            }

            else if(object instanceof Bike){
                ((Bike) object).update(delta);

                if(player.getBoundingBox().intersects(((Bike) object).getBoundingBox())){
                    killPlayer();
                }
            }

            else if(object instanceof Water){
                if(player.getBoundingBox().intersects(((Water) object).getBoundingBox()) && !riding){
                    killPlayer();
                    player.update(input);
                }
            }
        }

        for(Player frog: filledTargets){
            if(player.getBoundingBox().intersects(frog.getBoundingBox())){
                killPlayer();
            }
        }




        if(blocked){
            player.setX(playerCurrX);
            player.setY(playerCurrY);
        }

        if(player.getY() == TILE_WIDTH){
            if(player.getX() > TARGET1 - TILE_WIDTH && player.getX() < TARGET1 + TILE_WIDTH) {
                filledTargets.add(new Player("assets/frog.png", TARGET1, TILE_WIDTH));
                resetPlayerPos();
            }
            else if(player.getX() > TARGET2 - TILE_WIDTH && player.getX() < TARGET2 + TILE_WIDTH){
                filledTargets.add(new Player("assets/frog.png", TARGET2, TILE_WIDTH));
                resetPlayerPos();
            }
            else if(player.getX() > TARGET3 - TILE_WIDTH && player.getX() < TARGET3 + TILE_WIDTH){
                filledTargets.add(new Player("assets/frog.png", TARGET3, TILE_WIDTH));
                resetPlayerPos();
            }
            else if(player.getX() > TARGET4 - TILE_WIDTH && player.getX() < TARGET4 + TILE_WIDTH){
                filledTargets.add(new Player("assets/frog.png", TARGET4, TILE_WIDTH));
                resetPlayerPos();
            }
            else if(player.getX() > TARGET5 - TILE_WIDTH && player.getX() < TARGET5 + TILE_WIDTH){
                filledTargets.add(new Player("assets/frog.png", TARGET5, TILE_WIDTH));
                resetPlayerPos();
            }
        }




        if(extraLifevisible != extraLife.getVisibility()){
            if(extraLifevisible){
                resetExtraLife();
            }
            else{
                extraLifevisible = !extraLifevisible;
            }

        }
        float currLogX = ((Log)rideables.get(randomLogIndex)).getX();
        float rightMax = currLogX + ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;
        float leftMax = currLogX - ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;


        extraLife.setRightMax(rightMax);
        extraLife.setLeftMax(leftMax);
        extraLife.update(delta);

        if(extraLife.getBoundingBox().intersects(player.getBoundingBox())){
            player.addLife();
            generateExtraLife();
        }

        if(filledTargets.size() == NUM_TARGETS){
            murderous.clear();
            solids.clear();
            rideables.clear();
            grass.clear();
            filledTargets.clear();
            createLevel(SECOND_LEVEL);
        }
    }


    public void render(Graphics g){

        for(Murderous object: murderous){
            ((Sprite) object).render();
        }

        for(Rideable object: rideables){
            if(object instanceof Turtles){
                if(((Turtles) object).getVisibility()){
                    ((Sprite) object).render();
                }
            }
            else {
                ((Sprite) object).render();
            }
        }

        for(Solid object: solids){
            ((Sprite) object).render();
        }

        for(Grass object: grass){
            object.render();
        }

        for(Player object: filledTargets){
            object.render();
        }

        if(extraLife.getVisibility()){
            extraLife.render();
        }

        player.render();
        player.renderLives();
    }


    private void createLevel(String level){
        String fileName = "assets/levels/" + level + ".lvl";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){

            String line;
            String[] object;

            while((line = br.readLine()) != null){
                object = line.split(",");

                if(object[0].equals("water")){
                    murderous.add(new Water("assets/water.png", Float.valueOf(object[1]), Float.valueOf(object[2])));
                }

                else if(object[0].equals("grass")){
                    grass.add(new Grass("assets/grass.png", Float.valueOf(object[1]), Float.valueOf(object[2])));
                }

                else if(object[0].equals("tree")){
                    solids.add(new Tree("assets/tree.png", Float.valueOf(object[1]), Float.valueOf(object[2])));
                }

                else if(object[0].equals("bike")){
                    murderous.add(new Bike("assets/bike.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("bulldozer")){
                    solids.add(new Bulldozer("assets/bulldozer.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("racecar")){
                    murderous.add(new RaceCar("assets/racecar.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("bus")){
                    murderous.add(new Bus("assets/bus.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("turtle")){
                    rideables.add(new Turtles("assets/turtles.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("longLog")){
                    rideables.add(new Log("assets/longlog.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }

                else if(object[0].equals("log")){
                    rideables.add(new Log("assets/log.png", Float.valueOf(object[1]), Float.valueOf(object[2]), Boolean.parseBoolean(object[3])));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    private void killPlayer(){
        player.removeLife();
        resetPlayerPos();
    }

    private void resetPlayerPos(){
        player.setX(PLAYER_INIT_POS_X);
        player.setY(PLAYER_INIT_POS_Y);
    }

    private void generateRandomLog(){
        randomLogIndex = (int) (Math.random() * rideables.size());
        while(!(rideables.get(randomLogIndex) instanceof Log)){
            randomLogIndex = (int) (Math.random() * rideables.size());
        }
    }


    private void generateExtraLife() throws SlickException{
        generateRandomLog();

        float extraLifeX = ((Log)rideables.get(randomLogIndex)).getX();
        float extraLifeY = ((Log)rideables.get(randomLogIndex)).getY();
        float extraLifeRightMax = extraLifeX + ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;
        float extraLifeLeftMax = extraLifeX - ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;
        double logSpeed = ((Log)rideables.get(randomLogIndex)).getSpeed();
        boolean logDirection = ((Log)rideables.get(randomLogIndex)).getDirection();
        extraLife = new ExtraLife("assets/extralife.png", extraLifeX, extraLifeY, logDirection, extraLifeRightMax, extraLifeLeftMax);
        extraLifevisible = extraLife.getVisibility();
        extraLife.setSpeed(logSpeed);
    }

    private void resetExtraLife(){
        generateRandomLog();

        float extraLifeX = ((Log)rideables.get(randomLogIndex)).getX();
        float extraLifeY = ((Log)rideables.get(randomLogIndex)).getY();
        float extraLifeRightMax = extraLifeX + ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;
        float extraLifeLeftMax = extraLifeX - ((Log)rideables.get(randomLogIndex)).getImage().getWidth() / 2;
        double logSpeed = ((Log)rideables.get(randomLogIndex)).getSpeed();
        boolean logDirection = ((Log)rideables.get(randomLogIndex)).getDirection();

        extraLife.setX(extraLifeX);
        extraLife.setY(extraLifeY);
        extraLife.setRightMax(extraLifeRightMax);
        extraLife.setLeftMax(extraLifeLeftMax);
        extraLife.setDirection(logDirection);
        extraLifevisible = extraLife.getVisibility();
        extraLife.setSpeed(logSpeed);
    }
}
