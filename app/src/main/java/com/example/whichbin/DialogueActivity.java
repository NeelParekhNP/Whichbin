package com.example.whichbin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class DialogueActivity extends AppCompatActivity {

    private ConstraintLayout background;
    private SharedPreferences sharedPreferences;

    private int[] allDialogues = new int[] {
            R.drawable.bad_art_puffin,
            R.drawable.dialogue_1,
            R.drawable.dialogue_2,
            R.drawable.dialogue_3,
            R.drawable.dialogue_4,
            R.drawable.dialogue_5,
            R.drawable.dialogue_6,
            R.drawable.dialogue_7,
            R.drawable.dialogue_8,
            R.drawable.dialogue_9,
            R.drawable.dialogue_10,
            R.drawable.dialogue_11
    };

    private int dialogueNumber;
    private float screenWidth;

    private boolean dialogueButtonClicked;

    public static final String DIALOGUE_SEEN = "dialogueSeen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        loadData();

        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        background = (ConstraintLayout) findViewById(R.id.constraint_layout_dialogue);

        /** Get's the physical size of phone screen */

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;

        background.setBackground(getDrawable(allDialogues[dialogueNumber]));
        background.setOnTouchListener(touchListener);

    }
    /** Moves to the next dialogue if clicked on right of screen and moves to the previous dialogue if clicked on left of screen */
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            float x = motionEvent.getX();

            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(x < screenWidth/2 && dialogueNumber > 0 && dialogueNumber <= allDialogues.length){
                        background.setBackground(getDrawable(allDialogues[dialogueNumber-1]));
                        dialogueNumber--;
                    }
                    else if(x < screenWidth/2 && dialogueNumber == 0){
                        //Do nothing
                    }
                    else if (dialogueNumber == (allDialogues.length -1)){
                        saveData();
                        if(dialogueButtonClicked){
                            Intent myIntent = new Intent(getBaseContext(), MainMenu.class);
                            startActivity(myIntent);
                        }
                        else {
                            Intent myIntent = new Intent(getBaseContext(), OnboardingScreen.class);
                            startActivity(myIntent);
                        }

                    }
                    else if (x > screenWidth/2 && (dialogueNumber-1) < allDialogues.length){
                        dialogueNumber++;
                        background.setBackground(getDrawable(allDialogues[dialogueNumber]));
                    }
                    break;
            }
            return true;
        }
    };

    private void saveData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(DIALOGUE_SEEN, true);
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        dialogueButtonClicked = sharedPreferences.getBoolean("dialogueButtonClicked", false);
    }
}
