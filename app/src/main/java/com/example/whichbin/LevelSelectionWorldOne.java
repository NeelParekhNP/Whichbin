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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LevelSelectionWorldOne extends AppCompatActivity {

    private ImageView character;
    private ImageButton world1Level1, world1Level2, world1Level3;
    private Button launchWorldTwo;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 3000;

    private boolean levelOneWorldOnePassed;
    private boolean levelTwoWorldOnePassed;

    public static final String DRAG_DROP_GAME_THEME = "dragDropGameTheme";
    public static final String MULTIPLE_CHOICE_GAME_THEME = "multipleChoiceGameTheme";
    public static final String TRIVIA_GAME_THEME = "triviaGameTheme";

    private ImageButton buttons [] = new ImageButton[3];
    private int[] allButtonIds = {
            R.id.imageButton_w1_l1,
            R.id.imageButton_w1_l2,
            R.id.imageButton_w1_l3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection_world_one);

        character = (ImageView) findViewById(R.id.imageView_character_world_one);
        launchWorldTwo = (Button) findViewById(R.id.button_world_one_right);
        world1Level1 = (ImageButton) findViewById(R.id.imageButton_w1_l1);
        world1Level2 = (ImageButton) findViewById(R.id.imageButton_w1_l2);
        world1Level3 = (ImageButton) findViewById(R.id.imageButton_w1_l3);

        launchWorldTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), LevelSelectionWorldTwo.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        for (int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(allButtonIds[i]);
            buttons[i].setOnClickListener(clickListener);
            if ((allButtonIds[i] == R.id.imageButton_w1_l1) || (allButtonIds[i] == R.id.imageButton_w3_l1)){
                //Do nothing
            }
            else{
                buttons[i].setEnabled(false);
                buttons[i].setImageAlpha(0x3F);
            }
        }

        loadData();
        if (levelOneWorldOnePassed){
            world1Level2.setEnabled(true);
            world1Level2.setImageAlpha(0xFF);
        }
        if (levelTwoWorldOnePassed){
            world1Level3.setEnabled(true);
            world1Level3.setImageAlpha(0xFF);
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
                    myIntent = new Intent(getBaseContext(), MultipleChoiceGame.class);
                    break;
                case R.id.imageButton_w1_l3:
                    myIntent = new Intent(getBaseContext(), TriviaGame.class);
                    break;
                default:
                    myIntent = new Intent(getBaseContext(), LevelSelectionWorldOne.class);
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
        levelTwoWorldOnePassed = sharedPreferences.getBoolean("levelTwoWorldOneStatus", false);
    }

    private void changeData(View clicked) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (clicked.getId()) {
            case R.id.imageButton_w1_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 1);
                break;
            case R.id.imageButton_w1_l2 :
                editor.putInt(MULTIPLE_CHOICE_GAME_THEME, 1);
                break;
            case R.id.imageButton_w1_l3 :
                editor.putInt(TRIVIA_GAME_THEME, 1);
                break;
        }
        editor.commit();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
