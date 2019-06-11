package com.example.whichbin;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BinGame extends AppCompatActivity {

    private TextView questionTextView;
    private Button trashButton;
    private Button recycleButton;
    private Button organicWasteButton;
    private ImageView questionImageView;
    private LinearLayout nameTags;
    private LinearLayout allButtons;
    private int currentIndex = 0;
    private int totalCorrect =0;

    private Questions [] mQuestions = new Questions[]{
            new Questions(R.string.question_zero, 1),
            new Questions(R.string.question_one, 2),
            new Questions(R.string.question_two, 3),
            new Questions(R.string.question_three, 2),
            new Questions(R.string.question_four, 3),
            new Questions(R.string.question_five, 3),
            new Questions(R.string.question_six, 1),
            new Questions(R.string.question_seven, 1),
            new Questions(R.string.question_eight, 1),
            new Questions(R.string.question_nine, 1),
            new Questions(R.string.question_ten, 1)
    };

    private int[] textureArrayWin = {
        R.drawable.image_0,
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4,
        R.drawable.image_5,
        R.drawable.image_6,
        R.drawable.image_7,
        R.drawable.image_8,
        R.drawable.image_9,
        R.drawable.image_10,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_game);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        final int Question = mQuestions[currentIndex].getQuestion();
        questionTextView.setText(Question);

        questionImageView = (ImageView) findViewById(R.id.questionImageView);
        Drawable d = getResources().getDrawable(textureArrayWin[currentIndex]);
        questionImageView.setImageDrawable(d);

        nameTags = (LinearLayout) findViewById(R.id.buttonTagsLayout);
        allButtons = (LinearLayout) findViewById((R.id.buttonsLayout));

        trashButton = (Button) findViewById(R.id.trashButton);
        trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex==(mQuestions.length-1)){
                    ((ViewGroup) questionImageView.getParent()).removeView(questionImageView);
                    allButtons.removeAllViews();
                    nameTags.removeAllViews();
                    questionTextView.setText("Your total score was: " + totalCorrect);

                    //Intent myIntent = new Intent(getBaseContext(),   Results.class);
                    //startActivity(myIntent);
                }
                else {
                    checkAnswer(1);
                    currentIndex = (currentIndex + 1);
                    int question = mQuestions[currentIndex].getQuestion();
                    Drawable imageQuestion = getResources().getDrawable(textureArrayWin[currentIndex]);
                    questionTextView.setText(question);
                    questionImageView.setImageDrawable(imageQuestion);
                }
            }
        });

        recycleButton = (Button) findViewById(R.id.recycleButton);
        recycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex==(mQuestions.length-1)){
                    ((ViewGroup) questionImageView.getParent()).removeView(questionImageView);
                    allButtons.removeAllViews();
                    nameTags.removeAllViews();
                    questionTextView.setText("Your total score was: " + totalCorrect);
                    //Intent myIntent = new Intent(getBaseContext(),   Results.class);
                    //startActivity(myIntent);
                }
                else {
                    checkAnswer(2);
                    currentIndex = (currentIndex + 1);
                    int question = mQuestions[currentIndex].getQuestion();
                    Drawable imageQuestion = getResources().getDrawable(textureArrayWin[currentIndex]);
                    questionTextView.setText(question);
                    questionImageView.setImageDrawable(imageQuestion);
                }
            }
        });

        organicWasteButton = (Button) findViewById(R.id.organicWasteButton);
        organicWasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex==(mQuestions.length-1)){
                    ((ViewGroup) questionImageView.getParent()).removeView(questionImageView);
                    allButtons.removeAllViews();
                    nameTags.removeAllViews();
                    questionTextView.setText("Your total score was: " + totalCorrect);
                    //Intent myIntent = new Intent(getBaseContext(),   Results.class);
                    //startActivity(myIntent);
                }
                else {
                    checkAnswer(3);
                    currentIndex = (currentIndex + 1);
                    int question = mQuestions[currentIndex].getQuestion();
                    Drawable imageQuestion = getResources().getDrawable(textureArrayWin[currentIndex]);
                    questionTextView.setText(question);
                    questionImageView.setImageDrawable(imageQuestion);
                }
            }
        });
    }

    private void checkAnswer(int userPressed){

        int answer = mQuestions[currentIndex].isAnswer();
        if (userPressed == answer){
            Toast.makeText(BinGame.this,R.string.correctMessage, Toast.LENGTH_SHORT).show();
            totalCorrect = totalCorrect + 1;
        }
        else{
            Toast.makeText(BinGame.this,R.string.incorrectMessage, Toast.LENGTH_SHORT).show();
        }
    }
}