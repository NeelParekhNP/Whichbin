package com.example.whichbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectionWorldTwo extends AppCompatActivity {

    private Button launchWorldOne, launchWorldThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection_world_two);

        launchWorldOne = (Button) findViewById(R.id.button_world_two_left);
        launchWorldThree = (Button) findViewById(R.id.button_world_two_right);

        launchWorldOne.setOnClickListener(clickListener);
        launchWorldThree.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent;
            switch (view.getId()){
                case R.id.button_world_two_left:
                    myIntent = new Intent(getBaseContext(), LevelSelectionWorldOne.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;
                case R.id.button_world_two_right :
                    myIntent = new Intent(getBaseContext(), LevelSelectionWorldThree.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                    default:
                        myIntent = new Intent(getBaseContext(), LevelSelectionWorldTwo.class);
                        break;
            }
        }
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
