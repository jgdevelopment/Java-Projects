import java.util.ArrayList;
/**
 * class for a Robot that follows the right hand rule for navigating the maze.  
 * 
 * @author Jason Ginsberg
 * @version November 2014
 */
public class RHBot extends Robot
{
    private  ArrayList<Location> previousLocation;
    public RHBot(Location pos, Maze maze){
        super(pos,maze);
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
        if (canGoRight()){
            Location pos = getLocation().getAdjacentLocation(getDirection()+getLocation().EAST);
            previousLocation.add(pos);
            changeLocation(pos);
        }

        else if (canGoForward()){
            Location pos = getLocation().getAdjacentLocation(getDirection()+getLocation().NORTH);
            previousLocation.add(pos);
            changeLocation(pos); 
        }
        else if (canGoLeft()){
            Location pos = getLocation().getAdjacentLocation(getDirection()+getLocation().WEST);
            previousLocation.add(pos);
            changeLocation(pos); 
        }
    }

    public boolean canGoForward(){
        for (Location pos : maze.neighbors(position)){
            int dir = (getLocation().getDirectionToward(pos))%360;
            if (maze.get(pos).equals(MazeConstants.PASSAGE) &&
            dir  == getDirection()
            && !previousLocation.contains(pos)){
                return true;
            }
        }
        return false;
    }

    public boolean canGoRight(){
        for (Location pos : maze.neighbors(position)){
            int dir = (getLocation().getDirectionToward(pos))%360;
            if (maze.get(pos).equals(MazeConstants.PASSAGE) &&
            dir== getDirection()+getLocation().EAST
            && !previousLocation.contains(pos)){
                return true;
            }
        }
        return false;
    }

    public boolean canGoLeft(){
        for (Location pos : maze.neighbors(position)){
            int dir = (getLocation().getDirectionToward(pos))%360;
            if (maze.get(pos).equals(MazeConstants.PASSAGE) &&
            dir == ((getDirection()+getLocation().WEST)%360)
            && !previousLocation.contains(pos)){
                return true;
            }
        }
        return false;
    }

    public boolean canMove (){
        return (canGoRight() ||canGoForward()||canGoLeft());
    }
}
