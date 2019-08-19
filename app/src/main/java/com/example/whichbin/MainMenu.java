package com.example.whichbin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainMenu extends AppCompatActivity {
    String msg = "Android : ";
    private Button whichBinButton;
    private Button triviaButton;
    private Button multiplayerGame;
    private Button multipleChoiceGame;
    private Button playButton;
    private Button mainGame;

    private boolean dialogueStatus, instructionsStatus;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.main_menu);
        Log.d(msg, "The onCreate() event");

        whichBinButton = (Button) findViewById(R.id.whichBinButtonX);
        whichBinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openBinGame();
            }
        });

        triviaButton = (Button) findViewById(R.id.triviaGameX);
        triviaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTriviaGame();
            }
        });

        multiplayerGame = (Button) findViewById(R.id.multiplayerGameX);
        multiplayerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiplayerGame();
            }
        });

        /** Checks if the dialogues and instructions have already been seen and opens an activity accordingly */

        playButton = (Button) findViewById(R.id.levelSelectorButtonX);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                //sharedPreferences.edit().clear().commit();
                if (dialogueStatus == false){
                    openDialogueScreen();
                }
                else if(instructionsStatus == false){
                    openOnboardingScreen();
                }
                else {
                    openLevelSelectionScreen();
                }
            }
        });

        mainGame = (Button) findViewById(R.id.tiledGameButton);
        mainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainGame();
            }
        });

        multipleChoiceGame = (Button) findViewById(R.id.multipleChoiceGameX);
        multipleChoiceGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultipleChoiceGame();
            }
        });
    }
    /** Called if the Which Bin game button's clicked. */
    public void openBinGame(){
        Intent intent = new Intent(this, BinGame.class);
        startActivity(intent);
    }

    /** Called if the Trivia game button's clicked. */

    public void openTriviaGame(){
        Intent intent = new Intent(this, TriviaGame.class);
        startActivity(intent);
    }

    /** Called if the Multiplayer game button's clicked. */

    public void openMultiplayerGame(){
        Intent intent = new Intent(this, MultiPlayerGame.class);
        startActivity(intent);
    }

    /** Called if the Multiple choice game button's clicked. */

    public void openMultipleChoiceGame(){
        Intent intent = new Intent(this, MultipleChoiceGame.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked depending on whether the dialogues have already been seen. */

    public void openDialogueScreen() {
        /** Delete first 2 lines if want to keep progress saved even after app reset*/
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, DialogueActivity.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked depending on whether the instructions have already been seen. */

    public void openOnboardingScreen(){
        Intent intent = new Intent(this, OnboardingScreen.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked depending on whether the dialogues and instructions have already been seen. */

    public void openLevelSelectionScreen(){
        Intent intent = new Intent(this, LevelSelectionWorldOne.class);
        startActivity(intent);
    }

    public void openMainGame(){
        Intent intent = new Intent(this, TileBasedGameActivity.class);
        startActivity(intent);
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    /** Loads data on whether the instructions and dialogues have already been seen */

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dialogueStatus = sharedPreferences.getBoolean("dialogueSeen", false);
        instructionsStatus = sharedPreferences.getBoolean("instructionsSeen", false);
    }
}











