package com.example.whichbin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainMenu extends AppCompatActivity {
    String msg = "Android : ";
    private Button playButton;
    private Button multiplayerGame;
    private Button cinematicButton;
    private Button instructionsButton;
    private Button resetButton;
    private ConstraintLayout parentLayout;
    private SharedPreferences sharedPreferences;

    private boolean dialogueStatus, instructionsStatus, levelTwoWorldOnePassed, levelTwoWorldTwoPassed, levelTwoWorldThreePassed;

    public static final String DIALOGUE_BUTTON_CLICKED = "dialogueButtonClicked";
    public static final String INSTRUCTION_BUTTON_CLICKED = "instructionButtonClicked";

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        loadData();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.main_menu);
        Log.d(msg, "The onCreate() event");

        playButton = (Button) findViewById(R.id.levelSelectorButtonX);
        multiplayerGame = (Button) findViewById(R.id.multiplayerGameX);
        cinematicButton = (Button) findViewById(R.id.cinematicX);
        instructionsButton = (Button) findViewById(R.id.instructionsX);
        resetButton = (Button) findViewById(R.id.resetX);
        parentLayout = (ConstraintLayout) findViewById(R.id.menu_layout);

        View currentButton;

        for(int i=0; i<parentLayout.getChildCount(); i++) {
            currentButton = parentLayout.getChildAt(i);
            currentButton.setOnClickListener(clickListener);
        }

        if(levelTwoWorldOnePassed || levelTwoWorldTwoPassed || levelTwoWorldThreePassed){
            multiplayerGame.setEnabled(true);
        }

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.levelSelectorButtonX:
                    /** Delete first 2 lines if want to keep progress saved even after app reset*/
                    /*SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sharedPreferences.edit().clear().commit();*/
                    if (!dialogueStatus){
                        openDialogueScreen();
                    }
                    else if(!instructionsStatus){
                        openOnboardingScreen();
                    }
                    else {
                        openLevelSelectionScreen();
                    }
                    break;

                case R.id.multiplayerGameX:
                    openMultiplayerGame();
                    break;

                case R.id.cinematicX:
                    openDialogueScreen();
                    saveData(view);
                    break;

                case R.id.instructionsX:
                    openOnboardingScreen();
                    saveData(view);
                    break;

                case R.id.resetX:
                    new AlertDialog.Builder(MainMenu.this)
                            .setTitle("Reset Progress?")
                            .setMessage("Are you sure you want to reset your progress?")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    sharedPreferences.edit().clear().commit();
                                    multiplayerGame.setEnabled(false);
                                    loadData();
                                }
                            })

                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    break;
            }
        }
    };

    /** Called if the Multiplayer game button's clicked. */
    public void openMultiplayerGame(){
        Intent intent = new Intent(this, MultiPlayerGame.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked, depending on whether the dialogues have already been seen. */
    public void openDialogueScreen() {
        Intent intent = new Intent(this, DialogueActivity.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked, depending on whether the instructions have already been seen. */
    public void openOnboardingScreen(){
        Intent intent = new Intent(this, OnboardingScreen.class);
        startActivity(intent);
    }

    /** Called if the play button's clicked, depending on whether the dialogues and instructions have already been seen. */
    public void openLevelSelectionScreen(){
        Intent intent = new Intent(this, LevelSelectionWorldOne.class);
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
        dialogueStatus = sharedPreferences.getBoolean("dialogueSeen", false);
        instructionsStatus = sharedPreferences.getBoolean("instructionsSeen", false);
        levelTwoWorldOnePassed = sharedPreferences.getBoolean("levelTwoWorldOneStatus", false);
        levelTwoWorldTwoPassed = sharedPreferences.getBoolean("levelTwoWorldTwoStatus", false);
        levelTwoWorldThreePassed = sharedPreferences.getBoolean("levelTwoWorldThreeStatus", false);
    }

    private void saveData(View clicked) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (clicked.getId()) {
            case R.id.cinematicX :
                editor.putBoolean(DIALOGUE_BUTTON_CLICKED, true);
                break;
            case R.id.instructionsX :
                editor.putBoolean(INSTRUCTION_BUTTON_CLICKED, true);
                break;
        }
        editor.commit();
    }
}











