import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;
import java.awt.Color;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
/**
 *creates lights out game
 * 
 * @author Jaason Ginsberg
 * @version February 13, 2013
 * http://www.jaapsch.net/puzzles/lights.htm#solmini
 */
public class LightsOut extends GraphicsProgram
{
    private Color randCol;
    private GRect [][] board;
    private GRect restart;
    private GRect colorBackground;
    private GRect undo;
    private GRect solve;
    private List<Integer> storage;
    private GRect changeSize3;
    private GRect changeSize4;
    private GRect changeSize5;
    private Color rand;
    private Color cycleColor;
    private int test;
    private int end;
    private int clicks;
    private int storeSize;
    private int storeRetrieve1;
    private int storeRetrieve2;
    private int playTest;
    private GLabel over;
    private GLabel solveLabel;
    private GLabel score;
    private GLabel restartButton;
    private GLabel backgroundColorButton;
    private GLabel undoButton;
    private GLabel sizeLabel3;
    private GLabel sizeLabel4;
    private GLabel sizeLabel5;
    private int storeR;
    private int storeC;
    private int size;
    private GRect background;
    public void init ()
    {
        score = new GLabel("Clicks: "+clicks,450,100);
        background = new GRect (2000, 1000);
        initializeBoard(7,7);
        addMouseListeners();
        size = 0;
    }

    private void initializeBoard (int row, int col)
    {
        background.setLocation(0, 0);
        background.setFillColor(Color.gray);
        background.setFilled(true);

        over = new GLabel("Game Over",450,180);
        over.setFont(new Font("Serif", Font.BOLD, 36));
        storage = new ArrayList<Integer>();

        clicks = 0;
        score.setLabel("Clicks: "+clicks);
        score.setFont(new Font("Serif", Font.BOLD, 36));

        restartButton = new GLabel("Restart",180,360);
        restartButton.setFont(new Font("Serif", Font.BOLD, 36));

        restart = new GRect (200, 75);
        restart.setLocation(140, 310);
        restart.setFillColor(Color.red);
        restart.setFilled(true);

        backgroundColorButton = new GLabel("Change Background Color",150,265);
        backgroundColorButton.setFont(new Font("Serif", Font.BOLD, 16));

        colorBackground = new GRect (200, 75);
        colorBackground.setLocation(140, 210);
        colorBackground.setFillColor(Color.blue);
        colorBackground.setFilled(true);

        undoButton = new GLabel("Undo",200,160);
        undoButton.setFont(new Font("Serif", Font.BOLD, 28));

        undo = new GRect (200, 75);
        undo.setLocation(140, 110);
        undo.setFillColor(Color.green);
        undo.setFilled(true);

        solveLabel = new GLabel("Solve Hard",170,60);
        solveLabel.setFont(new Font("Serif", Font.BOLD, 28));

        solve = new GRect (200, 75);
        solve.setLocation(140, 10);
        solve.setFillColor(Color.orange);
        solve.setFilled(true);

        sizeLabel3 = new GLabel("Easy",980,60);
        sizeLabel3.setFont(new Font("Serif", Font.BOLD, 28));

        changeSize3 = new GRect (200, 75);
        changeSize3.setLocation(940, 10);
        changeSize3.setFillColor(Color.orange);
        changeSize3.setFilled(true);

        sizeLabel4 = new GLabel("Medium",980,160);
        sizeLabel4.setFont(new Font("Serif", Font.BOLD, 28));

        changeSize4 = new GRect (200, 75);
        changeSize4.setLocation(940, 110);
        changeSize4.setFillColor(Color.green);
        changeSize4.setFilled(true);

        sizeLabel5 = new GLabel("Hard",980,265);
        sizeLabel5.setFont(new Font("Serif", Font.BOLD, 28));

        changeSize5 = new GRect (200, 75);
        changeSize5.setLocation(940, 210);
        changeSize5.setFillColor(Color.blue);
        changeSize5.setFilled(true);

        playTest = 0;
        board = new GRect[row][col];
        double a = 350;
        double b= 200;

        end = 0;
        for (int r=0; r < 7+size; r++)
        {
            for (int c=0; c < 7+size; c++)
            {
                int random = (int)(Math.random()*2);
                if(c>0&&c<6+size&&r>0&&r<6+size){
                    if (random ==1){
                        randCol = (Color.yellow);
                    }
                    else{
                        randCol = (Color.black);
                    }
                }
                else{
                    randCol = (Color.gray);
                }
                board[r][c] = new GRect(a, b,75, 75);
                board[r][c].setFillColor(randCol);
                board[r][c].setFilled(true);

                add(board[r][c]);

                a= a+75;
            }
            a = 350;
            b= b+75;
        }
        add(background);
        add(restart);
        add(restartButton);
        add(colorBackground);
        add(undo);
        add(solve);
        add(backgroundColorButton);
        add(undoButton);

        add(changeSize3);
        add(changeSize4);
        add(changeSize5);
        add(solveLabel);

        add(sizeLabel3);
        add(sizeLabel4);

        add(sizeLabel5);

        add(score);
        background.sendToBack();
    }

    @Override
    public void mousePressed(MouseEvent event) 
    {
        if (playTest ==0){
            borderColor();
            for (int r=1; r < 6+size ; r++)
            {
                for (int c=1; c < 6+size; c++)
                {
                    if (board[r][c].contains(event.getX(), event.getY()))
                    {
                        storage.add(r);
                        storage.add(c);
                        for (int x = r-1;x<=r+1; x++) {
                            toggle(x, c);

                        }
                        for (int y = c-1;y<=c+1; y++) {
                            if (y!=c){
                                toggle(r, y);

                            }
                        }      
                        clicks++;
                        endTest();
                        storeR =2;
                        storeC =1;
                    }
                    score.setLabel("Clicks: "+clicks);
                }
            }
        }
        if (restart.contains(event.getX(), event.getY())){
            remove(over);
            initializeBoard(7,7);
        }
        else if (colorBackground.contains(event.getX(), event.getY())){
            changeBackgroundColor();
        }
        else if (undo.contains(event.getX(), event.getY())&&clicks != 0){
            undo();
        }
        else if (changeSize3.contains(event.getX(), event.getY())&&clicks == 0){
            size = -2;
            removeAll();
            initializeBoard(5, 5);
        }
        else if (changeSize4.contains(event.getX(), event.getY())&&clicks == 0){
            size = -1;
            removeAll();
            initializeBoard(6,6);
        }
        else if (changeSize5.contains(event.getX(), event.getY())&&clicks == 0){
            size = 0;
            removeAll();
            initializeBoard(7,7);
        }
        else if (solve.contains(event.getX(), event.getY()) && size == 0){
            /** work in progess tried to do 5x5 solution according to 
             * http://www.jaapsch.net/puzzles/lights.htm#solmini but their solution doesn't seem to 
             * always work (what if only E5 or D5 is lit?)
             */
            solution();
        }
    }

    public void borderColor(){
        for (int r=0; r < 7+size; r++)
        {
            for (int c=0; c < 7+size; c++)
            {
                if (c==0||c==6+size||r==0||r==6+size){
                    int red = (int)(Math.random()*201);
                    int green = (int)(Math.random()*50)+100;
                    int blue = 255;
                    rand = new Color(red,green,blue);
                    board[r][c].setFillColor(rand);

                }
            }
        }

    }

    public void toggle(int r, int c){
        if (board[r][c].getFillColor() == Color.black){
            board[r][c].setFillColor(Color.yellow);
        }
        else if (board[r][c].getFillColor() == Color.yellow) {
            board[r][c].setFillColor(Color.black);
        }

    }

    public void endTest(){
        for (int r=0; r < 7+size; r++)
        {
            for (int c=0; c < 7+size; c++)
            {
                if (board[r][c].getFillColor() == Color.black){
                    end++;
                    if(end == (5+size)*(5+size)){
                        playTest = 1;
                        add(over);          
                    }
                }
            }
        }
        end =0;
    }

    public void undo(){
        /**Could not work out bugs in program, row 1 works, row 2 (r,c) and (r,c-1) don't toggle
         * row 3 and up, (r,c) doesn't toggle
         */
        storeSize = storage.size();
        storeRetrieve1 = storage.get(storeSize-storeR);
        storeRetrieve2 = storage.get(storeSize-storeC);
        for (int x = storeRetrieve1-1;x<=storeRetrieve1+1; x++) {
            toggle(x, storeRetrieve2);
        }
        for (int y = storeRetrieve2-1;y<=storeRetrieve2+1; y++) {
            if (y!=storeC){
                toggle(storeRetrieve1, y);
            }
        }      
        storage.remove(storeSize-1);
        storeSize = storage.size();
        storage.remove(storeSize-1);
        clicks++;
        endTest();
        score.setLabel("Clicks: "+clicks);
    }

    private void solution() {
        /** I followed the rules on http://www.jaapsch.net/puzzles/lights.htm#solmini but the solution
         * does not seem to be working...
         * would have eventually created a system that would pause after each step of solution, 
         * to show step by step
         */
        for (int r=1; r < 5+size; r++)
        {
            for (int c=1; c < 6+size; c++)
            {
                if (board[r][c].getFillColor() == Color.yellow){
                    for (int x = r+2;x>=r; x=x-1) {
                        toggle(x, c);
                    }
                    for (int y = c-1;y<=c+1; y++) {
                        if (y!=c){
                            toggle(r+1, y);
                        }
                    }      

                    clicks++;
                    endTest();
                }
                score.setLabel("Clicks: "+clicks);
            }
        }
        if (board[5][1].getFillColor() == Color.yellow){
            toggle(1, 3);
            toggle(1, 4);
            toggle(1, 5);
            toggle(2, 4);
            clicks++;

            toggle(1, 5);
            toggle(1, 4);
            toggle(2, 5);
            clicks++;

            endTest();
            score.setLabel("Clicks: "+clicks);
        }

        if (board[5][2].getFillColor() == Color.yellow){

            toggle(1, 1);
            toggle(1, 2);
            toggle(2, 2);
            toggle(1, 3);
            clicks++;

            toggle(1, 5);
            toggle(1, 4);
            toggle(2, 5);
            clicks++;

            endTest();
            score.setLabel("Clicks: "+clicks);
        }

        if (board[5][3].getFillColor() == Color.yellow){
            toggle(1, 3);
            toggle(1, 4);
            toggle(1, 5);
            toggle(2, 4);
            clicks++;
            endTest();
            score.setLabel("Clicks: "+clicks);
        }
        for (int r=1; r < 5+size; r++)
        {
            for (int c=1; c < 6+size; c++)
            {
                if (board[r][c].getFillColor() == Color.yellow){
                    for (int x = r+2;x>=r; x=x-1) {
                        toggle(x, c);
                    }
                    for (int y = c-1;y<=c+1; y++) {
                        if (y!=c){
                            toggle(r+1, y);
                        }
                    }      
                    clicks++;
                    endTest();
                }
                score.setLabel("Clicks: "+clicks);
            }
        }
        endTest();
    }

    private void changeBackgroundColor(){
        int r = (int)(Math.random()*255);
        int g = (int)(Math.random()*255);
        int b = (int)(Math.random()*255);
        cycleColor = new Color(r,g,b);
        background.setFillColor(cycleColor);
    }
}
