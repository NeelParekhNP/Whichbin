package com.example.whichbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button trashButton;
    private Button recycleButton;
    private Button nextButton;

    private int currentIndex = 0;
    private Questions [] mQuestions = new Questions[]{
            new Questions(R.string.question_zero, false),
            new Questions(R.string.question_one, true),
            new Questions(R.string.question_two, false),
            new Questions(R.string.question_three, true),
            new Questions(R.string.question_four, false),
            new Questions(R.string.question_five, false),
            new Questions(R.string.question_six, false),
            new Questions(R.string.question_seven, false),
            new Questions(R.string.question_eight, false),
            new Questions(R.string.question_nine, false),
            new Questions(R.string.question_ten, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        final int Question = mQuestions[currentIndex].getQuestion();
        questionTextView.setText(Question);

        trashButton = (Button) findViewById(R.id.trashButton);
        trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        recycleButton = (Button) findViewById(R.id.recycleButton);
        recycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % mQuestions.length;
                int question =  mQuestions[currentIndex].getQuestion();
                questionTextView.setText(question);
            }
        });

    }

    private void checkAnswer(boolean userPressed){

        boolean answer = mQuestions[currentIndex].isAnswer();
        if (userPressed == answer){
            Toast.makeText(MainActivity.this,R.string.correctMessage, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,R.string.incorrectMessage, Toast.LENGTH_SHORT).show();
        }
    }
}