package com.example.whichbin;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class TileBasedMap {
    private boolean readyToExit;
    private boolean blockedSquares[][];
    // Grid location represented by index of the array and string for the toast's instruction
    private String actionSquaresDescription[][];
    // Grid location as index with the number representing the order of the task
    private ArrayList<String> nextActionInstruction;
    private ArrayList<Integer> actionSquareXLocation;
    private ArrayList<Integer> actionSquareYLocation;
    private boolean activeActionSquare[][];

    private int actionSquaresOrder[][];
    // int representing what task currently needs to be completed
    private int currentTask;
    private int numberOfTasks;
    private String portalSquares[][];



    public TileBasedMap(){
        readyToExit = false;
        blockedSquares = new boolean[9][16];
        actionSquaresDescription = new String[9][16];
        actionSquaresOrder = new int[9][16];
        currentTask = 1;

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
    public boolean isCurrentTask(int xPosition, int yPosition){
        if(actionSquaresOrder[xPosition][yPosition] == currentTask){
            return true;
        }
        else{
            return false;
        }

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



    public String getActionSquareDescription(int x, int y){
        return actionSquaresDescription[x][y];
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


    // The below methods might become irrelevant in refactoring
    public String checkIfActionSquare(int x, int y){
        return actionSquaresDescription[x][y];
    }

    public void setActionSquareDescription(int x, int y, String taskDescription){
        actionSquaresDescription[x][y] = taskDescription;
    }

    public void setActionSquareOrder(int x, int y, int orderNumber){
        actionSquaresOrder[x][y] = orderNumber;
    }
}
