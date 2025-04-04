package com.example.whichbin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * CITATION: this class was based on the helpful tutorials of youtube user "clientuser.net" found here:
 * https://www.youtube.com/playlist?list=PLjAaEVR74i-AMRQnNQ3fsRPzZ98J2iaut
 * I followed some of the videos to learn about animation and game loops-in particular tutorials 42-46 and 49-52.
 * Notably I used the logic that lets the thread sleep only if delta_t is greater than zero and learnt about the canvas.
 * This is what let me implement some basic animation of the character moving-but this was changed
 * and combined with other logic for the tile based movement I set up and my own images. Any parts relating to dividing
 * the screen into a grid, to create movement in discrete steps, I wrote myself.
 */

public class RendererLayout extends SurfaceView implements Runnable {

    Thread thread = null;

    double frames_per_second, frame_time_seconds, frame_time_ms, frame_time_ns;
    double tLF, tEOR, delta_t;

    boolean canDraw = false;
    SurfaceHolder surfaceHolder;
    Bitmap downstairs;
    Bitmap upstairs;
    public int currentBackgroundNumber;
    Canvas canvas;


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

        this.width = width;
        this.height = height;

        assignGridValues();

        downstairs = BitmapFactory.decodeResource(getResources(), R.drawable.downstairs);
        downstairs = Bitmap.createScaledBitmap(downstairs, width, height, true);

        upstairs = BitmapFactory.decodeResource(getResources(), R.drawable.upstairs);
        upstairs = Bitmap.createScaledBitmap(upstairs, width, height, true);
        currentBackgroundNumber = 1;

        character = BitmapFactory.decodeResource(getResources(), R.drawable.character);
        character = Bitmap.createScaledBitmap(character, (width/9), (height/16), true);

        characterPositionX = Math.round(convertIntToGridX(4));
        characterPositionY = Math.round(convertIntToGridY(14));

        xUnit = Math.round(x1);
        yUnit = Math.round(y1);

        canDraw = false;
        surfaceHolder = getHolder();

        frames_per_second = 15;
        frame_time_seconds=1/frames_per_second;
        frame_time_ms = frame_time_seconds*1000;
        frame_time_ns = frame_time_ms * 1000000;



    }

    @Override
    public void run() {

        tLF = System.nanoTime();
        delta_t = 0;

        while(canDraw){

            update(delta_t);

            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }

            draw();

            tEOR = System.nanoTime();

            delta_t = frame_time_ns - (tEOR - tLF);

            //stats();

            try {
                if(delta_t > 0) {
                    thread.sleep((long) (delta_t / 1000000));
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }



        }
    }

    private void update(double delta_t){

    }

    private void draw(){
        canvas = surfaceHolder.lockCanvas();
        //canvas.drawBitmap(downstairs, 0, 0, null);
        drawBackground(currentBackgroundNumber);
        // Check this is the correct variable
        canvas.drawBitmap(character, characterPositionX, characterPositionY, null);

        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    public void drawBackground(int backGroundNumber){
        if(backGroundNumber == 1){
            canvas.drawBitmap(downstairs, 0, 0, null);
        }
        if(backGroundNumber == 2){
            canvas.drawBitmap(upstairs, 0, 0, null);
        }
        else{
            canvas.drawBitmap(downstairs, 0, 0, null);
        }
    }

    public void changeBackgroundNumber(int chosenBackground){
        currentBackgroundNumber = chosenBackground;
    }

    public int getCurrentBackgroundNumber(){
        return currentBackgroundNumber;
    }

    public void moveUp(){
        characterPositionY = characterPositionY - yUnit;


        try {
            thread.sleep((long) 100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void moveDown(){
        characterPositionY = characterPositionY + yUnit;


        try {
            thread.sleep((long) 100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void moveRight(){

        characterPositionX = characterPositionX + xUnit;


        try {
            thread.sleep((long) 100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void moveLeft(){

        characterPositionX = characterPositionX - xUnit;


        try {
            thread.sleep((long) 100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
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

    public void pause(){
        canDraw = false;
        Log.d("Thread", "Pausing thread..." + Thread.currentThread().getId());

        while(true){
            try{
                Log.d("Thread", "Joining");
                thread.join();
                break;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        Log.d("Thread", "THREAD IS PAUSED " + Thread.currentThread().getId());
        thread = null;
        Log.d("Thread", "NULLED ******");
    }

    public void resume(){
        canDraw = true;
        Log.d("CanDraw", "true");

        if(thread == null){
            Log.d("Thread", "Making New");
            thread = new Thread(this);
            Log.d("Thread", "STARTING NEW");
            thread.start();
            Log.d("Thread", "STARTED");
        }
    }
}
