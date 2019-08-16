package com.example.whichbin;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class TileBasedMap {
    private Bitmap img;
    private boolean blockedSquares[][];
    private String quizSquares[][];
    private String portalSquares[][];

    public TileBasedMap(){
        blockedSquares = new boolean[9][16];
        quizSquares = new String[9][16];
        portalSquares = new String [9][16];
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

    public void setBlockedSquare(int x, int y){
        blockedSquares[x][y] = true;
    }

    public boolean checkSquareBlocked(int x, int y){
        return blockedSquares[x][y];
    }

    public void setQuizSquare(int x, int y, String gameName){
        quizSquares[x][y] = gameName;
    }

    public String checkIfQuizSquare(int x, int y){
        return quizSquares[x][y];
    }

    public void setPortalSquare(int x, int y, String direction){
        portalSquares[x][y] = direction;
    }

    public String checkIfPortalSquare(int x, int y){
        return portalSquares[x][y];
    }
}
