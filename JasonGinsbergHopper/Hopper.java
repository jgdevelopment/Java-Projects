import acm.graphics.*;
import acm.program.*;
import acm.gui.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import acm.util.RandomGenerator;

/**
 * Hopper is a very shortened version of Frogger
 * used as a Lab Practical for CS2
 * and revised for Feb. 2013
 * 
 * -- simpler idea yet
 * -- uses GImages -- frog.png, old_truck.png, images from www.clker.com/cliparts
 * -- ctrls wasd
 * -- defined constants
 *  
 * @author Jason Ginsberg
 * @version February 2012, February 2013
 */
public class Hopper extends GraphicsProgram
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int SCORE_AREA_HEIGHT = 50;
    private static final double FROG_HOP = 50;
    private static final int FROG_SIZE = 50;
    private static final int FROG_WIN_COUNT = 10;
    private static final int GAME_SPEED = 33;
    private static final int INDENT = 10;
    private int intScore;
    private int truckSpeed;
    private int intFrog;
    private int truckHeight;
    private int randomX;
    private boolean breakTest;
    private GRect scoreArea;
    private GRect safeZone;
    private GImage truck;
    private GImage frog;
    private GLabel score;
    private GLabel frogCount;
    private GLabel splat;
    private RandomGenerator randgen;

    @Override
    public void init ()
    {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT-2*FROG_SIZE);
        randgen = new RandomGenerator();

        intScore = 0;
        intFrog = 3;
        truckSpeed = 3;
        truckHeight = 300;

        breakTest = true;
        scoreArea = new GRect(0, WINDOW_HEIGHT-25, WINDOW_WIDTH,SCORE_AREA_HEIGHT );
        scoreArea.setFillColor(Color.darkGray);
        scoreArea.setFilled(true);

        safeZone = new GRect(350, 0, FROG_SIZE,FROG_SIZE );
        safeZone.setFillColor(Color.green);
        safeZone.setFilled(true);

        splat = new GLabel ("SPLAT", getWidth()/2, getHeight()/2);
        splat.setFont(new Font("Cambria", Font.BOLD, 18));
        splat.setColor(Color.white);

        truck = new GImage("old-truck.png", 0,300);
        frog = new GImage("frog.png", 350,500);

        score = new GLabel ("Score: "+intScore,0, WINDOW_HEIGHT);
        score.setFont(new Font("Cambria", Font.BOLD, 18));
        score.setColor(Color.white);

        frogCount = new GLabel ("Frogs Left: "+intFrog,WINDOW_WIDTH-110, WINDOW_HEIGHT);
        frogCount.setFont(new Font("Cambria", Font.BOLD, 18));
        frogCount.setColor(Color.white);

        setBackground(Color.lightGray);
        add(scoreArea);
        add(safeZone);
        add(truck);
        add(frog);
        add(score);
        add(frogCount);
        addActionListeners();
        addKeyListeners();
    }    

    @Override
    public void keyPressed (KeyEvent event)
    {
        if (breakTest){
            char key = event.getKeyChar();
            if (key == 'w')
            {
                frog.move(0, -FROG_HOP);
            }
            else if (key == 'a')
            {
                frog.move(-FROG_HOP, 0);
            }
            else if (key == 's')
            {
                frog.move(0,FROG_HOP);
            }
            else if (key == 'd')
            {            
                frog.move(FROG_HOP, 0);
            }
        }
    }

    @Override
    public void run ()
    {
        while (true)
        {
            if (truck.getX() > WINDOW_WIDTH){
                truckHeight = truckHeight -50;
                truck.setLocation(0, truckHeight);
                truckSpeed = truckSpeed +1;
            }
            if (truck.getBounds().intersects(frog.getBounds())){
                add (splat);
                pause (500);
                frog.setLocation(350, 500);
                intFrog = intFrog -1;
                if (intFrog == -1){
                    breakTest = false;
                    break;
                }
                remove (splat);
                frogCount.setLabel("Frogs Left: "+intFrog);
            }
            if (frog.getBounds().intersects(safeZone.getBounds())){
                randomX = randgen.nextInt(0, WINDOW_WIDTH);
                intScore++;
                score.setLabel("Score: "+intScore);
                frog.setLocation(350, 500);
                safeZone.setLocation(randomX, 0);
            }
            if (intScore==10){
                splat.setLabel("YOU WIN!!!");
                add (splat);
                breakTest = false;
                break;
            }
            if (truck.getBounds().intersects(safeZone.getBounds())){
                intScore = intScore -1;
                truck.setLocation(0, 300);
                truckHeight = 300;
                truckSpeed = 3;
            }
            truck.move(truckSpeed,0);
            pause(GAME_SPEED);
        }
    }
}
