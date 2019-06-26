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
    private TextView scoreDisplay;
    private boolean inputAnswer;
    private int nextQuestionNumber = 0;
    private Button trueButton;
    private Button falseButton;
    private String displayQuestion;
    private String responseToast;
    private String triviaQuestion;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);


        questionDisplay = (TextView) findViewById(R.id.trivia_question);
        scoreDisplay = (TextView) findViewById(R.id.score_int);
        trueButton = (Button) findViewById(R.id.trivia_true_button);
        falseButton = (Button) findViewById(R.id.trivia_false_button);

        displayNextQuestion();
        incrementScore();

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = true;
                responseToast = checkIfCorrect(nextQuestionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_SHORT).show();
                displayNextQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = false;
                String responseToast = checkIfCorrect(nextQuestionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_SHORT).show();
                displayNextQuestion();
            }
        });

    }



    /** Method to increment the question-currently cycles back to the beginning after 10 questions.*/
    private void displayNextQuestion(){
        if(nextQuestionNumber < 10){
            triviaQuestion = triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber);
            questionDisplay.setText(triviaQuestion);
            nextQuestionNumber++;
        }
        // For now I'll just have the questions loop back to the beginning instead of the app crashing hence resetting questionNumber
        else{
            nextQuestionNumber = 0;
            triviaQuestion = triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber);
            questionDisplay.setText(triviaQuestion);
            nextQuestionNumber++;
        }
    }

    private String checkIfCorrect(int qNo, boolean inputAnswer){
        boolean actualAnswer = getAnswer(qNo);
        if(actualAnswer == inputAnswer){
            incrementScore();
            return "That's the right answer!";
        }
        else{
            return "That's the wrong answer...";
        }
    }

    private boolean getAnswer(int qNo){
        int qNoLessOne = qNo-1;
        boolean answer = triviaGameManager.checkTriviaQuestionAnswer(qNoLessOne);
        return answer;
    }

    private void incrementScore(){
        scoreDisplay.setText(String.valueOf(score));
        score++;
    }
}




