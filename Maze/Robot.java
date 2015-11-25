
/**
 * Abstract class Robot
 * 
 * @author Jason Ginsbegr
 * @version November 2014
 */
public abstract class Robot
{
    public int direction;
    public Location position;
    public Maze<MazeConstants> maze;

    public Robot(Location pos, Maze maze){
        this.position = pos;
        this.maze = maze;
    }

    public abstract void act ();

    public abstract void move ();

    public abstract boolean canMove ();

    public void turn(){
        setDirection(direction+position.EAST);
    }

    public int getDirection(){
        return this.direction;
    }

    public void setDirection (int dir){
        this.direction = dir%360;
    }

    public Location getLocation(){
        return this.position;
    }

    public void changeLocation (Location toPos){	
        if (!this.maze.isValid(toPos)|| maze.get(toPos) != MazeConstants.PASSAGE){
            throw new IllegalArgumentException(toPos + " is invalid");
        }
        if (this.position.compareTo(toPos)!=0){
            setDirection(this.position.getDirectionToward(toPos));
            maze.put(this.position,MazeConstants.PASSAGE);
            this.position = toPos;
            maze.put(toPos,MazeConstants.ROBOT);
        }
    }

    public Maze<MazeConstants> getMaze(){
        return this.maze;
    }

    public String toString(){
        return "current direction: "+this.direction
        +", current position: "+this.position;
    }

}
