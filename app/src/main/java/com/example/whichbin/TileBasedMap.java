package com.example.whichbin;

import java.util.ArrayList;

public class TileBasedMap {
    private boolean readyToExit;
    // Two dimesional array represents [x][y] grid values of blocked squares the character can't move into.
    private boolean blockedSquares[][];
    // ArrayLists of task Strings and X and Y locations
    private ArrayList<String> nextActionInstruction;
    private ArrayList<Integer> actionSquareXLocation;
    private ArrayList<Integer> actionSquareYLocation;
    // Grid locations of action squares with tasks-set dynamically based on the above ArrayLists
    private boolean activeActionSquare[][];
    // Holds the XY location of stair squares to other maps-only currently used once but could be extended.
    private boolean stairSquares[][];
    // Holds the XY location of the square to trigger exiting the activity.
    private String portalSquares[][];



    public TileBasedMap(){
        readyToExit = false;
        blockedSquares = new boolean[9][16];
        stairSquares = new boolean[9][16];


        portalSquares = new String [9][16];

        activeActionSquare = new boolean[9][16];
        nextActionInstruction = new ArrayList<>();
        actionSquareXLocation = new ArrayList<>();
        actionSquareYLocation = new ArrayList<>();

    }

    // Method when initialising a new map to default all squares to unblocked.
    // This can then be ovewritten by individual assignments of blocked squares.
    public void setAllSquaresUnblocked() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 16; j++) {
                blockedSquares[i][j] = false;
            }
        }
    }

    // Check if the action square holds the current task
    public boolean isAStairSquare(int xPosition, int yPosition){
        return stairSquares[xPosition][yPosition];
    }

    public void deactivateAllStairSquares() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 16; j++) {
                activeActionSquare[i][j] = false;
            }
        }
    }

    public void setStairSquareLocation(int x, int y){
        stairSquares[x][y] = true;
    }

    public boolean areTasksComplete(){
        return readyToExit;
    }

    public void allTasksComplete(){
            readyToExit = true;
    }

    public void setBlockedSquare(int x, int y){
        blockedSquares[x][y] = true;
    }

    public boolean checkSquareBlocked(int x, int y){
        return blockedSquares[x][y];
    }

    public void setNextActionInstruction(String instruction){
        nextActionInstruction.add(instruction);
    }

    public String getNextActionInstruction(int index){
        return nextActionInstruction.get(index);
    }

    public int getNumberOfActionInstructions(){
        return nextActionInstruction.size();
    }

    public void addActionSquareLocation(int x, int y){
        actionSquareXLocation.add(x);
        actionSquareYLocation.add(y);
    }

    public void activateNextActionSquare(int actionInstructionNumber){
        deactivateAllActionSquares();
        activeActionSquare[actionSquareXLocation.get(actionInstructionNumber)][actionSquareYLocation.get(actionInstructionNumber)] = true;
    }

    public void deactivateAllActionSquares() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 16; j++) {
                activeActionSquare[i][j] = false;
            }
        }
    }

    public boolean checkIfActiveActionSquare(int x, int y){
        return activeActionSquare[x][y];
    }


    public void setPortalSquare(int x, int y, String direction){
        portalSquares[x][y] = direction;
    }

    public String checkIfPortalSquare(int x, int y){
        return portalSquares[x][y];
    }

}
