import java.util.ArrayList;
/**
 * Solves the NQueens Problem for any size using a 2D array
 * 
 * @author Jason Ginsberg
 * @version December 8, 2014
 */
public class KingdomOfQueens
{
    private boolean[][] board;
    private boolean shouldPrint;   //I am working on eliminating this
    private static int numSol = 0; //I am working on eliminating this

    public KingdomOfQueens(int n){
        board = new boolean[n][n];
        System.out.println(solveAndShow()); //prints solutions and total
    }

    public int solveN(){
        numSol = 0;
        shouldPrint = false;
        return positionQueens(0,0,0);
    }

    public int solveAndShow (){
        numSol =0;
        shouldPrint = true;
        return positionQueens(0,0,0);
    }

    private boolean canPlace(int r, int c){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board.length; y++){
                if(board[x][y]){
                    if(!(x==r && y ==c)){
                        if(x == r){ //row
                            return false;
                        }
                        if(y == c){ //column
                            return false;
                        }
                        if(Math.abs(x-r) == Math.abs(y-c)){ //diagonals
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int positionQueens(int row, int col, int count) {
        if(count == board.length){
            if (shouldPrint){
                print();
            }
            numSol++;
            return numSol;
        }
        if(col>=board.length){
            return 1;
        }
        if(canPlace(row, col)) {
            board[row][col] = true;
            //the position is safe, move to start of next row
            positionQueens(row+1,0,count+1);
            //unplace
            board[row][col] = false;
            //retry the next column position in the same row
            positionQueens(row,col+1,count);
        }
        else{
            // if the position is unsafe, try the next col in the same row
            positionQueens(row,col+1,count);
        }
        return numSol;
    }

    private void print(){
        for(int y = 0; y < board.length; y++){
            for(int x = 0; x < board.length; x++){
                System.out.print(board[x][y] ? "|Q" : "|_");
            }
            System.out.println("|");
        }
        System.out.println();
    }
}