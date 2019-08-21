package com.example.whichbin;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TriviaGame extends AppCompatActivity {

    private TriviaGameManager triviaGameManager;
    private TextView questionDisplay;
    private TextView scoreDisplay;
    private TextView timeDisplay;

    private int triviaGameLevel;
    private boolean inputAnswer;
    private Integer nextQuestionNumber = 0;
    private Button trueButton;
    private Button falseButton;
    private String responseToast;
    private String triviaQuestion;
    private String answerExplanation;
    private int score = 0;
    private CountDownTimer clock;
    private long timeRemaining = 120000;
    private boolean stopped;

    public ArrayList<String> answeredQuestionsList;
    public ArrayList<String> extraAnswerInfoList;
    public ArrayList<Integer> inputAnswersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        loadData();
        triviaGameManager = new TriviaGameManager(triviaGameLevel);

        // Hide battery bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Background imageViews for cloud animation
        final ImageView backgroundZero = (ImageView) findViewById(R.id.background_0);
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_1);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_2);
        final ImageView backgroundThree = (ImageView) findViewById(R.id.background_3);

        /**
         * The below code runs a background animation which I've set to clouds moving across the sky,
         * this was based on a stack overflow answer which can be found here:
         * https://stackoverflow.com/questions/36894384/android-move-background-continuously-with-animation
         * I did adjust it however as it was only for two images but this caused stutter so I sandwiched my
         * cloud frames between two identical blank blue backgrounds (adjusting the corresponding xml code).
         * There was also a slight gap between the frames but I hid this by matching the general background's
         * rgb value to the blue of the clouds.
         */
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(30000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundZero.getWidth();
                final float translationX = 3 * width * progress;
                backgroundZero.setTranslationX(translationX);
                backgroundOne.setTranslationX(translationX - width);
                backgroundTwo.setTranslationX(translationX - (width * 2));
                backgroundThree.setTranslationX(translationX - (width * 3));
            }
        });
        animator.start();

        // Set up TextViews and buttons
        questionDisplay = (TextView) findViewById(R.id.trivia_question);
        scoreDisplay = (TextView) findViewById(R.id.score_int);
        timeDisplay = (TextView) findViewById(R.id.timer_time);
        trueButton = (Button) findViewById(R.id.trivia_true_button);
        falseButton = (Button) findViewById(R.id.trivia_false_button);

        // ArrayLists to hold answer information
        answeredQuestionsList = new ArrayList<>();
        extraAnswerInfoList = new ArrayList<>();
        inputAnswersList= new ArrayList<>();

        stopped = false;
        displayNextQuestion();
        scoreDisplay.setText(String.valueOf(score));
        startTimer();

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputAnswer = true;
                responseToast = checkIfCorrect(nextQuestionNumber, inputAnswer);
                Toast.makeText(getApplicationContext(), responseToast, Toast.LENGTH_SHORT).show();

                //answerInfo.addQuestion(triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber-1), 0);
                answeredQuestionsList.add(triviaQuestion);
                extraAnswerInfoList.add(answerExplanation);
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
                extraAnswerInfoList.add(answerExplanation);
                inputAnswersList.add(1);

                displayNextQuestion();
            }
        });

    }



    // Method to increment the question
    private void displayNextQuestion(){
        if(nextQuestionNumber < 10){
            triviaQuestion = triviaGameManager.getTriviaQuestionByNo(nextQuestionNumber);
            questionDisplay.setText(triviaQuestion);
            answerExplanation = triviaGameManager.getExtraAnswerInfo(nextQuestionNumber);
            nextQuestionNumber++;
        }
        // If all ten questions are answered the next activity opens
        else{
            stopped = true;
            openAnswerActivity();
        }
    }

    // Creates the String to be shown in the feedback toast
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
            // If the player runs out of time without answering all questions the next activity is opened
            @Override
            public void onFinish() {
                // Check stopped is false so the activity isn't opened twice
                if(stopped == false) {
                    openAnswerActivity();
                }
            }
        }.start();
    }

    // Converts the time remaining into a string in minutes:seconds format
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
        score++;
        scoreDisplay.setText(String.valueOf(score));
        // Make sure the last button click doesn't register twice-hopefully unnecessary but had some problems previously.
        if(score > 10){
            score = 10;
        }
    }

    // Opens the feedback activity with the answers and passes it a parcelable containing the input information
    public void openAnswerActivity(){
        TriviaAnswerParcel answerInfo = new TriviaAnswerParcel(answeredQuestionsList, extraAnswerInfoList, inputAnswersList, score);
        Intent intent = new Intent(this, TriviaAnswerActivity.class);
        intent.putExtra("answerInfo", answerInfo);
        startActivity(intent);
    }

    // Checks the world theme so that the questions can be set accordingly
    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        triviaGameLevel = sharedPreferences.getInt("triviaGameTheme", 0);
    }
}




