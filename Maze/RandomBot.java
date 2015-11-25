import java.util.Random;
import java.util.ArrayList;
/**
 * class for Robot that randomly navigate the maze. 
 * 
 * @author Jason Ginsberg
 * @version November 2014
 */
public class RandomBot extends Robot
{
    private  ArrayList<Location> validDirections;
    private  ArrayList<Location> previousLocation;
    public RandomBot(Location pos, Maze maze){
        super(pos,maze);
        validDirections = new ArrayList<Location>();
        previousLocation = new ArrayList<Location>();
        act();
    }

    public void act (){
        if (canMove()){
            move();
        }
        else {
            previousLocation.clear();
            previousLocation.add(getLocation());
            turn();
        }
    }

    public void move (){
        Random random = new Random();
        int randomInt = (int)random.nextInt(validDirections.size());
        int newDirection = getLocation().getDirectionToward(validDirections.get(randomInt));
        Location newLocation = getLocation().getAdjacentLocation(newDirection);
        changeLocation(newLocation); 
        previousLocation.add(newLocation);
        validDirections.clear();
    }

    public boolean canMove (){
        for (Location pos : maze.neighbors(getLocation())){
            if (maze.get(pos).equals(MazeConstants.PASSAGE) 
            &&!previousLocation.contains(pos)){
                validDirections.add(pos);
            }
        }
        return validDirections.size()>0;
    }
}
