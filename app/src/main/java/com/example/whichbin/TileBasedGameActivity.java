package com.example.whichbin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static java.lang.StrictMath.abs;

import com.example.whichbin.RendererLayout;

public class TileBasedGameActivity extends Activity {
    // Screen dimensions
    int width;
    int height;

    RendererLayout renderer;

    String msg = "Android : TileBasedGameActivity: ";

    private float newX;
    private float newY;

    // Player's current grid values
    private int playerPositionX;
    private int playerPositionY;
    // Grid square set on click for the character to move to
    private int playerDestinationX;
    private int playerDestinationY;
    // Difference between position and destination
    private int differenceIntX;
    private int differenceIntY;

    // next position variables
    public int nextRight;
    private int nextLeft;
    private int nextUp;
    private int nextDown;

    // Grid locations in pixels depending on screen size
    private float x0;
    // This value x1 also represents one grid square's width
    private float x1;
    private float x2;
    private float x3;
    private float x4;
    private float x5;
    private float x6;
    private float x7;
    private float x8;

    private float y0;
    // This value y1 also represents one grid square's height
    private float y1;
    private float y2;
    private float y3;
    private float y4;
    private float y5;
    private float y6;
    private float y7;
    private float y8;
    private float y9;
    private float y10;
    private float y11;
    private float y12;
    private float y13;
    private float y14;
    private float y15;

    // Selected map variable and map
    private TileMapManager tileMapManager;
    private TileBasedMap currentMap;
    private int currentMapNumber;
    private int actionInstructionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        width = getWidth();
        height = getHeight();

        renderer = new RendererLayout(width, height, this);
        setContentView(renderer);

        assignGridValues();

        playerPositionX = 4;
        playerPositionY = 14;

        setNextPositions();

        tileMapManager = new TileMapManager();
        currentMapNumber = 1;
        currentMap = tileMapManager.getMap(1);

        actionInstructionNumber = 0;

        updateInstruction();

        // currentMap.allTasksComplete();

        //ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        renderer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float destinationX = event.getX();
                float destinationY = event.getY();

                // Set the x and y values separately
                newX = findNearestGridX(event.getX());
                newY = findNearestGridY(event.getY());

                playerDestinationX = convertGridToIntX(newX);
                playerDestinationY = convertGridToIntY(newY);




                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {


                        differenceIntX = playerDestinationX - playerPositionX;
                        differenceIntY = playerDestinationY - playerPositionY;

                        while (!(differenceIntX == 0 && differenceIntY == 0)) {
                            // If further away in X value than Y from destination move in X dimension
                            // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                            if (abs(differenceIntX) >= abs(differenceIntY)) {
                                // Move left condition
                                // LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
                                if (playerDestinationX < playerPositionX) {
                                    if(currentMap.checkIfPortalSquare(nextLeft, playerPositionY) != null && !currentMap.checkIfPortalSquare(nextLeft, playerPositionY).isEmpty()){
                                        if(currentMap.checkIfPortalSquare(nextLeft, playerPositionY).equals("exit")){
                                            openLevelSelectionScreen();
                                        }
                                    }
                                    if(currentMap.checkSquareBlocked((nextLeft), playerPositionY) == true) {
                                        stopMovement();
                                    }
                                    if(currentMap.checkSquareBlocked((nextLeft), playerPositionY) == false) {
                                        renderer.moveLeft();
                                        playerPositionX--;
                                        setNextPositions();
                                        updateJourneyDifference();
                                    }
                                }
                                // LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
                                // Move right condition
                                // RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
                                if (playerDestinationX > playerPositionX) {
                                    // Check if next square is blocked
                                    if(currentMap.checkSquareBlocked((nextRight), playerPositionY) == true) {
                                        stopMovement();
                                    }
                                    if(currentMap.checkSquareBlocked((nextRight), playerPositionY) == false) {
                                        renderer.moveRight();
                                        playerPositionX++;
                                        setNextPositions();
                                        updateJourneyDifference();
                                    }
                                }
                                // RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
                            }
                            // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
                            // Below is concerned with movement in Y
                            // YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
                            if (abs(differenceIntX) < abs(differenceIntY)) {
                                // Move up condition
                                // UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
                                if (playerDestinationY < playerPositionY) {
                                    if(currentMap.checkSquareBlocked(playerPositionX, nextUp) == true) {
                                        stopMovement();
                                    }
                                    if(currentMap.checkSquareBlocked(playerPositionX, nextUp) == false) {
                                        renderer.moveUp();
                                        playerPositionY--;
                                        setNextPositions();
                                        updateJourneyDifference();
                                    }
                                }
                                // UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
                                // Move down condition
                                // DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
                                if (playerDestinationY > playerPositionY) {
                                    if(currentMap.checkSquareBlocked(playerPositionX, nextDown) == true) {
                                        stopMovement();
                                    }
                                    if(currentMap.checkSquareBlocked(playerPositionX, nextDown) == false) {
                                        renderer.moveDown();
                                        playerPositionY++;
                                        setNextPositions();
                                        updateJourneyDifference();
                                    }
                                }
                                // DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
                            }
                            // YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
                        }
                        checkIfExitSquare();
                        checkIfActionSquare();


                        break;


                    }
                    case MotionEvent.ACTION_MOVE: {
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        break;
                    }

                }
                return false;
            }


        });
    }

    @Override
    protected void onPause(){
        Log.d("Super/Game", "Resume");
        super.onPause();
        renderer.pause();
    }

    @Override
    protected void onResume(){
        Log.d("Super/Game", "Pause");
        super.onResume();
        renderer.resume();
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void openLevelSelectionScreen() {
        Intent intent = new Intent(this, LevelSelectionWorldOne.class);
        startActivity(intent);
    }

    private void checkIfExitSquare(){
        if(currentMap.checkIfPortalSquare(playerPositionX, playerPositionY) != null && !currentMap.checkIfPortalSquare(playerPositionX, playerPositionY).isEmpty()){
            if(currentMap.checkIfPortalSquare(playerPositionX, playerPositionY).equals("exit") && (currentMap.areTasksComplete() == true)){
            // if(currentMap.checkIfPortalSquare(playerPositionX, playerPositionY).equals("exit")){
                openLevelSelectionScreen();
            }
        }
    }

    private boolean trueIfBlockedSquare(int x, int y){
        return currentMap.checkSquareBlocked(x,y);
    }

    // Method to reset variables when the character meets a blocked tile
    private void stopMovement(){
        playerDestinationX = playerPositionX;
        playerDestinationY = playerPositionY;
        differenceIntX = 0;
        differenceIntY = 0;
    }

    private void updateJourneyDifference() {
        differenceIntX = playerDestinationX - playerPositionX;
        differenceIntY = playerDestinationY - playerPositionY;
    }

    private void setNextPositions() {
        nextRight = playerPositionX + 1;
        nextLeft = playerPositionX - 1;
        nextUp = playerPositionY - 1;
        nextDown = playerPositionY + 1;
    }

    private void checkIfActionSquare(){
        if(currentMap.checkIfActiveActionSquare(playerPositionX, playerPositionY) == true){
            updateInstruction();
        }
    }

    private void updateInstruction(){
        if(actionInstructionNumber < (currentMap.getNumberOfActionInstructions()-1)) {
            currentMap.activateNextActionSquare(actionInstructionNumber);
            Toast.makeText(getApplicationContext(), currentMap.getNextActionInstruction(actionInstructionNumber), Toast.LENGTH_LONG).show();
            actionInstructionNumber++;
        }

        else{
            //Activate portal square
            currentMap.setPortalSquare(4, 14, "exit");
            currentMap.allTasksComplete();
            Toast.makeText(getApplicationContext(), "You completed the tasks! Go to the front door to leave.", Toast.LENGTH_LONG).show();
        }

    }

    private void showInstruction(int x, int y){
        Toast.makeText(getApplicationContext(), currentMap.getActionSquareDescription(x, y), Toast.LENGTH_LONG).show();
    }

    public int getWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public int getHeight(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

    public void assignGridValues(){
        //Create variables for the 10 possible positions
        x0 = 0;
        x1 = (width / 9);
        x2 = (width / 9) * 2;
        x3 = (width / 9) * 3;
        x4 = (width / 9) * 4;
        x5 = (width / 9) * 5;
        x6 = (width / 9) * 6;
        x7 = (width / 9) * 7;
        x8 = (width / 9) * 8;

        //Create variables for the 10 possible positions
        y0 = 0;
        y1 = (height / 16);
        y2 = (height / 16) * 2;
        y3 = (height / 16) * 3;
        y4 = (height / 16) * 4;
        y5 = (height / 16) * 5;
        y6 = (height / 16) * 6;
        y7 = (height / 16) * 7;
        y8 = (height / 16) * 8;
        y9 = (height / 16) * 9;
        y10 = (height / 16) * 10;
        y11 = (height / 16) * 11;
        y12 = (height / 16) * 12;
        y13 = (height / 16) * 13;
        y14 = (height / 16) * 14;
        y15 = (height / 16) * 15;
    }


    public float findNearestGridX(float touchX) {


        // horizontal position 0
        if (touchX < x1) {
            return x0;
        }
        // horizontal position 1
        if (touchX >= x1 && touchX < x2) {
            return x1;
        }
        // horizontal position 2
        if (touchX >= x2 && touchX < x3) {
            return x2;
        }
        // horizontal position 3
        if (touchX >= x3 && touchX < x4) {
            return x3;
        }
        // horizontal position 4
        if (touchX >= x4 && touchX < x5) {
            return x4;
        }
        // horizontal position 5
        if (touchX >= x5 && touchX < x6) {
            return x5;
        }
        // horizontal position 6
        if (touchX >= x6 && touchX < x7) {
            return x6;
        }
        // horizontal position 7
        if (touchX >= x7 && touchX < x8) {
            return x7;
        }
        // horizontal position 8
        else {
            return x8;
        }
    }

    public float findNearestGridY(float touchY){

        // vertical position 0
        if (touchY < y1) {
            return y0;
        }
        // vertical position 1
        if (touchY >= y1 && touchY < y2) {
            return y1;
        }
        // vertical position 2
        if (touchY >= y2 && touchY < y3) {
            return y2;
        }
        // vertical position 3
        if (touchY >= y3 && touchY < y4) {
            return y3;
        }
        // vertical position 4
        if (touchY >= y4 && touchY < y5) {
            return y4;
        }
        // vertical position 5
        if (touchY >= y5 && touchY < y6) {
            return y5;
        }
        // vertical position 6
        if (touchY >= y6 && touchY < y7) {
            return y6;
        }
        // vertical position 7
        if (touchY >= y7 && touchY < y8) {
            return y7;
        }
        // vertical position 8
        if (touchY >= y8 && touchY < y9) {
            return y8;
        }
        // vertical position 9
        if (touchY >= y9 && touchY < y10) {
            return y9;
        }
        // vertical position 10
        if (touchY >= y10 && touchY < y11) {
            return y10;
        }
        // vertical position 11
        if (touchY >= y11 && touchY < y12) {
            return y11;
        }
        // vertical position 12
        if (touchY >= y12 && touchY < y13) {
            return y12;
        }
        // vertical position 13
        if (touchY >= y13 && touchY < y14) {
            return y13;
        }
        // vertical position 14
        if (touchY >= y14 && touchY < y15) {
            return y14;
        }
        // vertical position 15
        else {
            return y15;
        }
    }

    private int convertGridToIntX(float gridPosition){
        if(gridPosition == x0){
            return 0;
        }
        if(gridPosition == x1){
            return 1;
        }
        if(gridPosition == x2){
            return 2;
        }
        if(gridPosition == x3){
            return 3;
        }
        if(gridPosition == x4){
            return 4;
        }
        if(gridPosition == x5){
            return 5;
        }
        if(gridPosition == x6){
            return 6;
        }
        if(gridPosition == x7){
            return 7;
        }
        if(gridPosition == x8){
            return 8;
        }
        else{
            return 0;
        }
    }

    private int convertGridToIntY(float gridPosition){
        if(gridPosition == y0){
            return 0;
        }
        if(gridPosition == y1){
            return 1;
        }
        if(gridPosition == y2){
            return 2;
        }
        if(gridPosition == y3){
            return 3;
        }
        if(gridPosition == y4){
            return 4;
        }
        if(gridPosition == y5){
            return 5;
        }
        if(gridPosition == y6){
            return 6;
        }
        if(gridPosition == y7){
            return 7;
        }
        if(gridPosition == y8){
            return 8;
        }
        if(gridPosition == y9){
            return 9;
        }
        if(gridPosition == y10){
            return 10;
        }
        if(gridPosition == y11){
            return 11;
        }
        if(gridPosition == y12){
            return 12;
        }
        if(gridPosition == y13){
            return 13;
        }
        if(gridPosition == y14){
            return 14;
        }
        if(gridPosition == y15){
            return 15;
        }
        else{
            return 0;
        }
    }

    public float convertIntToGridX(int gridPosition){
        if(gridPosition == 0){
            return x0;
        }
        if(gridPosition == 1){
            return x1;
        }
        if(gridPosition == 2){
            return x2;
        }
        if(gridPosition == 3){
            return x3;
        }
        if(gridPosition == 4){
            return x4;
        }
        if(gridPosition == 5){
            return x5;
        }
        if(gridPosition == 6){
            return x6;
        }
        if(gridPosition == 7){
            return x7;
        }
        if(gridPosition == 8){
            return x8;
        }
        else{
            return x0;
        }
    }

    public float convertIntToGridY(int gridPosition){
        if(gridPosition == 0){
            return y0;
        }
        if(gridPosition == 1){
            return y1;
        }
        if(gridPosition == 2){
            return y2;
        }
        if(gridPosition == 3){
            return y3;
        }
        if(gridPosition == 4){
            return y4;
        }
        if(gridPosition == 5){
            return y5;
        }
        if(gridPosition == 6){
            return y6;
        }
        if(gridPosition == 7){
            return y7;
        }
        if(gridPosition == 8){
            return y8;
        }
        if(gridPosition == 9){
            return y9;
        }
        if(gridPosition == 10){
            return y10;
        }
        if(gridPosition == 11){
            return y11;
        }
        if(gridPosition == 12){
            return y12;
        }
        if(gridPosition == 13){
            return y13;
        }
        if(gridPosition == 14){
            return y14;
        }
        if(gridPosition == 15){
            return y15;
        }
        else{
            return y0;
        }
    }
}
