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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LevelSelectionWorldThree extends AppCompatActivity {

    private Button launchWorldTwo, mainMenu;
    private ImageView character;
    private ImageButton world3Level1, world3Level2, world3Level3, world3Level4;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private static final long COUNTDOWN_IN_MILLIS = 3000;

    private boolean levelOneWorldThreePassed;
    private boolean levelTwoWorldThreePassed;
    private boolean levelThreeWorldThreePassed;

    public static final String DRAG_DROP_GAME_THEME = "dragDropGameTheme";
    public static final String MULTIPLE_CHOICE_GAME_THEME = "multipleChoiceGameTheme";
    public static final String TRIVIA_GAME_THEME = "triviaGameTheme";

    private ImageButton buttons [] = new ImageButton[4];
    private int[] allButtonIds = {
            R.id.imageButton_w3_l1,
            R.id.imageButton_w3_l2,
            R.id.imageButton_w3_l3,
            R.id.imageButton_w3_l4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_selection_world_three);

        launchWorldTwo = (Button) findViewById(R.id.button_world_three_left);
        mainMenu = (Button) findViewById(R.id.button_world_three_menu);

        /** Sets the clicklistener for the next button to open the next level section screen*/
        launchWorldTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), LevelSelectionWorldTwo.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        /** Sets the clicklistener for the button to open the main menu*/
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), MainMenu.class);
                startActivity(myIntent);
            }
        });

        character = (ImageView) findViewById(R.id.imageView_character_world_three);
        world3Level1 = (ImageButton) findViewById(R.id.imageButton_w3_l1);
        world3Level2 = (ImageButton) findViewById(R.id.imageButton_w3_l2);
        world3Level3 = (ImageButton) findViewById(R.id.imageButton_w3_l3);
        world3Level4 = (ImageButton) findViewById(R.id.imageButton_w3_l4);

        /** Dynamically sets the click listeners to the buttons and visibility */
        for (int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(allButtonIds[i]);
            buttons[i].setOnClickListener(clickListener);
            if ((allButtonIds[i] == R.id.imageButton_w3_l1)){
                //Do nothing
            }
            else{
                buttons[i].setEnabled(false);
                buttons[i].setImageAlpha(0x3F);
            }
        }

        /** Unlocks the next level if enough points were scored in previous levels */
        loadData();
        if (levelOneWorldThreePassed){
            world3Level2.setEnabled(true);
            world3Level2.setImageAlpha(0xFF);
        }
        if (levelTwoWorldThreePassed){
            world3Level3.setEnabled(true);
            world3Level3.setImageAlpha(0xFF);
        }
        if (levelThreeWorldThreePassed){
            world3Level4.setEnabled(true);
            world3Level4.setImageAlpha(0xFF);
        }
    }

    /** Opens the level that is clicked on */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            float x = view.getX();
            float y = view.getY();
            Intent myIntent;
            switch(view.getId()) {
                case R.id.imageButton_w3_l1:
                    myIntent = new Intent(getBaseContext(), BinGame.class);
                    break;
                case R.id.imageButton_w3_l2:
                    myIntent = new Intent(getBaseContext(), MultipleChoiceGame.class);
                    break;
                case R.id.imageButton_w3_l3:
                    myIntent = new Intent(getBaseContext(), TriviaGame.class);
                    break;
                case R.id.imageButton_w3_l4:
                    myIntent = new Intent(getBaseContext(), TileBasedGameActivity.class);
                    break;
                default:
                    myIntent = new Intent(getBaseContext(), LevelSelectionWorldThree.class);
            }
            changeData(view);
            startCountDown(x, y, myIntent);
        }
    };

    /** Moves the character to clicked image and adds a delay before level is started */
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

    /** Checks if enough points were scored in previous level to unlock next levels */
    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        levelOneWorldThreePassed = sharedPreferences.getBoolean("levelOneWorldThreeStatus", false);
        levelTwoWorldThreePassed = sharedPreferences.getBoolean("levelTwoWorldThreeStatus", false);
        levelThreeWorldThreePassed = sharedPreferences.getBoolean("levelThreeWorldThreeStatus", false);
    }

    /** Feeds information to other games about which level was picked */
    private void changeData(View clicked) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (clicked.getId()) {
            case R.id.imageButton_w3_l1 :
                editor.putInt(DRAG_DROP_GAME_THEME, 3);
                break;
            case R.id.imageButton_w3_l2 :
                editor.putInt(MULTIPLE_CHOICE_GAME_THEME, 3);
                break;
            case R.id.imageButton_w3_l3 :
                editor.putInt(TRIVIA_GAME_THEME, 3);
                break;
        }
        editor.commit();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
