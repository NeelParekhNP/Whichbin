package com.example.whichbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TriviaGame extends AppCompatActivity {

    private TriviaGameManager triviaGameManager = new TriviaGameManager();
    private TextView questionDisplay;
    private boolean inputAnswer;
    private int questionNumber = 0;
    private Button trueButton;
    private Button falseButton;
    private String responseToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        trueButton = (Button) findViewById(R.id.triviaTrueButton);
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = true;
                responseToast = checkIfCorrect(questionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_LONG).show();
            }
        });

        falseButton = (Button) findViewById(R.id.triviaFalseButton);
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = false;
                String responseToast = checkIfCorrect(questionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void displayNextQuestion(int currentQuestion){
        int nextQuestion = currentQuestion + 1;
        formatQuestion(nextQuestion);

    }




    public String formatQuestion(int qNo){
        String formattedQuestion = "q" + qNo;
        return formattedQuestion;
    }

    public String checkIfCorrect(int qNo, boolean inputAnswer){
        triviaGameManager.getTriviaQuestionByNo(qNo);
        boolean actualAnswer = triviaGameManager.checkTriviaQuestionAnswer(qNo);
        if(actualAnswer = inputAnswer){
            return "That's the right answer!";
        }
        else{
            return "That's the wrong answer...";
        }
    }
}




