package com.example.whichbin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LevelSelection extends AppCompatActivity {

    private ImageView character;
    private ImageButton world1Level1, world1Level2, world2Level1, world2Level2;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 3000;

    private boolean levelOneWorldOnePassed;
    private boolean levelOneWorldTwoPassed;

    public static final String DRAG_DROP_GAME_THEME = "dragDropGameTheme";

    private ImageButton buttons [] = new ImageButton[4];
    private int[] allButtonIds = {
            R.id.imageButton_w1_l1,
            R.id.imageButton_w1_l2,
            R.id.imageButton_w2_l1,
            R.id.imageButton_w2_l2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        character = (ImageView) findViewById(R.id.imageView_character);
        world1Level1 = (ImageButton) findViewById(R.id.imageButton_w1_l1);
        world1Level2 = (ImageButton) findViewById(R.id.imageButton_w1_l2);
        world2Level1 = (ImageButton) findViewById(R.id.imageButton_w2_l1);
        world2Level2 = (ImageButton) findViewById(R.id.imageButton_w2_l2);

        for (int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(allButtonIds[i]);
            buttons[i].setOnClickListener(clickListener);
            if ((allButtonIds[i] == R.id.imageButton_w1_l1) || (allButtonIds[i] == R.id.imageButton_w2_l1) || (allButtonIds[i] == R.id.imageButton_w2_l1) || (allButtonIds[i] == R.id.imageButton_w2_l1)){
                //Do nothing
            }
            else{
                buttons[i].setEnabled(false);
                buttons[i].setImageAlpha(0x3F);
            }
        }

        loadData();
        if (levelOneWorldOnePassed == true){
            world1Level2.setEnabled(true);
            world1Level2.setImageAlpha(0xFF);
        }
        if (levelOneWorldTwoPassed == true){
            world2Level2.setEnabled(true);
            world2Level2.setImageAlpha(0xFF);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            float x = view.getX();
            float y = view.getY();
            Intent myIntent;
            switch(view.getId()) {
                case R.id.imageButton_w1_l1:
                    myIntent = new Intent(getBaseContext(), BinGame.class);
                    break;
                case R.id.imageButton_w1_l2:
                    myIntent = new Intent(getBaseContext(), TriviaGame.class);
                    break;
                case R.id.imageButton_w2_l1:
                    myIntent = new Intent(getBaseContext(), BinGame.class);
                    break;
                case R.id.imageButton_w2_l2:
                    myIntent = new Intent(getBaseContext(), TriviaGame.class);
                    break;
                default:
                    myIntent = new Intent(getBaseContext(), LevelSelection.class);
            }
            changeData(view);
            startCountDown(x, y, myIntent);
        }
    };

    private void startCountDown(final float x, final float y, final Intent myIntent){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                ObjectAnimator animX = ObjectAnimator.ofFloat(character, "x", x-150);
                ObjectAnimator animY = ObjectAnimator.ofFloat(character, "y", y+100);
                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                startActivity(myIntent);
            }
        }.start();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        levelOneWorldOnePassed = sharedPreferences.getBoolean("levelOneWorldOneStatus", false);
        levelOneWorldTwoPassed = sharedPreferences.getBoolean("levelOneWorldTwoStatus", false);
    }

    private void changeData(View clicked) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (clicked.getId()) {
            case R.id.imageButton_w1_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 1);
                break;
            case R.id.imageButton_w2_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 2);
                break;
/*            case R.id.imageButton_w3_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 3);
                break;
            case R.id.imageButton_w4_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 4);
                break;*/
        }
        editor.commit();
    }
}
