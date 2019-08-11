package com.example.whichbin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


public class RendererLayout extends SurfaceView implements Runnable {

    Thread thread = null;
    boolean canDraw = false;

    Bitmap backGroundCheck;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    Bitmap character;
    int characterPositionX;
    int characterPositionY;

    // Get screen height and width
    int width;
    int height;

    // Grid values as pixels
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

    int xUnit;
    int yUnit;

    public RendererLayout(int width, int height, Context context){
        super(context);
        surfaceHolder = getHolder();

        this.width = width;
        this.height = height;

        assignGridValues();

        backGroundCheck = BitmapFactory.decodeResource(getResources(), R.drawable.check);
        backGroundCheck = Bitmap.createScaledBitmap(backGroundCheck, width, height, true);

        character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        character = Bitmap.createScaledBitmap(character, (width/9), (height/16), true);

        characterPositionX = 0;
        characterPositionY = 0;

        xUnit = Math.round(x1);
        yUnit = Math.round(y1);

        // Below is code for testing which starts the character in the bottom right.
        /**
         characterPositionX = Math.round(convertIntToGridX(8));
         characterPositionY = Math.round(convertIntToGridY(15));;
         */

    }

    @Override
    public void run() {

        while(canDraw){


            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }

            canvas = surfaceHolder.lockCanvas();
            // motionCharacter(10);

            //moveUp(2);

            // Slightly concerned by the trail the character is leaving behind and whether this will take up memory
            // This is only visible with the background hidden so comment out the below line to test.
            canvas.drawBitmap(backGroundCheck, null, new Rect(0,0, width, height), null);
            // drawSquare(130,130,650,650);
            canvas.drawBitmap(character, characterPositionX, characterPositionY, null);
            surfaceHolder.unlockCanvasAndPost(canvas);




            // Unsure whether this is necessary
            // invalidate();
        }
    }

    public void pause(){
        canDraw = false;

        while(true){
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }

    public void resume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }


    public void moveUp(){
        characterPositionY = characterPositionY - yUnit;

    }

    public void moveDown(){
        characterPositionY = characterPositionY + yUnit;

    }

    public void moveRight(){

        characterPositionX = characterPositionX + xUnit;

    }

    public void moveLeft(){

        characterPositionX = characterPositionX - xUnit;

    }

    // Previous attempt below will try a simplified version
    /**
     public void moveUp(int destinationY){
     if(characterPositionY > convertIntToGridY(destinationY)){
     characterPositionY = characterPositionY - 5;
     }
     }

     public void moveDown(int destinationY){
     if(characterPositionY < convertIntToGridY(destinationY)){
     characterPositionY = characterPositionY + 5;
     }
     }

     public void moveRight(int destinationX){
     if(characterPositionX < convertIntToGridX(destinationX)){
     characterPositionX = characterPositionX + 5;
     }
     }

     public void moveLeft(int destinationX){
     if(characterPositionX > convertIntToGridX(destinationX)){
     characterPositionX = characterPositionX - 5;
     }
     }
     */

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
