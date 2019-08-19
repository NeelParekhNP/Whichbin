package com.example.whichbin;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogueActivity extends AppCompatActivity {

    private ConstraintLayout background;
    private int[] allDialogues = new int[] {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        background = (ConstraintLayout) findViewById(R.id.constraint_layout_dialogue);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogueNumber<allDialogues.length){
                    background.setBackground(getDrawable(allDialogues[dialogueNumber]));
                    dialogueNumber++;
                }
                else{
                    Intent myIntent = new Intent(getBaseContext(), OnboardingScreen.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
