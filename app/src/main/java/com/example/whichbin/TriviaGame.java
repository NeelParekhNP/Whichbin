package com.example.whichbin;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaGame extends AppCompatActivity {

    private TriviaGameManager triviaGameManager = new TriviaGameManager();
    private TextView questionDisplay;
    private TextView scoreDisplay;
    private TextView timeDisplay;

    private boolean inputAnswer;
    private Integer nextQuestionNumber = 0;
    private Button trueButton;
    private Button falseButton;
    private String displayQuestion;
    private String responseToast;
    private String triviaQuestion;
    private int score = 0;
    private CountDownTimer clock;
    private long timeRemaining = 120000;

    //public TriviaAnswerParcel answerInfo;
    public ArrayList<String> answeredQuestionsList;
    public ArrayList<Integer> inputAnswersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);


        questionDisplay = (TextView) findViewById(R.id.trivia_question);
        scoreDisplay = (TextView) findViewById(R.id.score_int);
        timeDisplay = (TextView) findViewById(R.id.timer_time);
        trueButton = (Button) findViewById(R.id.trivia_true_button);
        falseButton = (Button) findViewById(R.id.trivia_false_button);

        answeredQuestionsList = new ArrayList<>();
        inputAnswersList= new ArrayList<>();

        // answerInfo = new TriviaAnswerParcel();

        displayNextQuestion();
        incrementScore();
        startTimer();

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = true;
                responseToast = checkIfCorrect(nextQuestionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_SHORT).show();

                //answerInfo.addQuestion(triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber-1), 0);
                answeredQuestionsList.add(triviaQuestion);
                inputAnswersList.add(0);

                displayNextQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = false;
                String responseToast = checkIfCorrect(nextQuestionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_SHORT).show();

                //answerInfo.addQuestion(triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber-1), 1);
                answeredQuestionsList.add(triviaQuestion);
                inputAnswersList.add(1);

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

            /**
            nextQuestionNumber = 0;
            triviaQuestion = triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber);
            questionDisplay.setText(triviaQuestion);
            nextQuestionNumber++;
            */

            openAnswerActivity();
        }
    }

    private String checkIfCorrect(Integer qNo, boolean inputAnswer){
        boolean actualAnswer = getAnswer(qNo);
        if(actualAnswer == inputAnswer){
            incrementScore();
            return "That's the right answer!";
        }
        else{
            return "That's the wrong answer...";
        }
    }

    private boolean getAnswer(Integer qNo){
        int qNoLessOne = qNo-1;
        boolean answer = triviaGameManager.checkTriviaQuestionAnswer(qNoLessOne);
        return answer;
    }

    private void startTimer(){
        clock = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                getTimeString();
            }

            @Override
            public void onFinish() {
                openAnswerActivity();
            }
        }.start();
    }

    private void getTimeString(){
        int mins = (int) timeRemaining / 60000;
        int secs = (int) timeRemaining % 60000 /1000;

        String timeString;

        timeString = "" + mins;
        timeString += ":";
        if(secs < 10) {
            timeString += "0";
        }
        timeString += secs;

        timeDisplay.setText(timeString);
    }

    private void incrementScore(){
        scoreDisplay.setText(String.valueOf(score));
        score++;
    }

    public void openAnswerActivity(){
        TriviaAnswerParcel answerInfo = new TriviaAnswerParcel(answeredQuestionsList, inputAnswersList);
        Intent intent = new Intent(this, TriviaAnswerActivity.class);
        intent.putExtra("answerInfo", answerInfo);
        startActivity(intent);
    }
}




