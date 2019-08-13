package com.example.whichbin;

import android.app.Activity;
import android.content.Intent;
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
    private Button mainGame;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        mainGame = (Button) findViewById(R.id.tiledGameButton);
        mainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainGame();
            }
        });
    }
    /** Called if the Which Bin game button's clicked. */
    public void openBinGame(){
        Intent intent = new Intent(this, BinGame.class);
        startActivity(intent);
    }

    public void openTriviaGame(){
        Intent intent = new Intent(this, TriviaGame.class);
        startActivity(intent);
    }

    public void openMultiplayerGame(){
        Intent intent = new Intent(this, MultiPlayerGame.class);
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
}











