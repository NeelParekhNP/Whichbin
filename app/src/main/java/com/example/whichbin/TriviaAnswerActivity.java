package com.example.whichbin;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TriviaAnswerActivity extends AppCompatActivity {

    private int triviaGameLevel;
    private TextView questionDisplay;
    private TextView answerDisplay;
    private TextView scoreDisplay;
    private Button nextButton;
    private Button prevButton;
    private Button mainMenuButton;

    private int questionIndex = 0;
    private String question;
    private String scoreString;
    private String answerText;
    private TriviaGameManager triviaGameManager;

    private TriviaAnswerParcel answerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_answer);

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

        // Get the parcelable with the player's input information
        Intent intent = getIntent();
        answerInfo = intent.getParcelableExtra("answerInfo");

        // Set up TextViews and buttons
        questionDisplay = (TextView) findViewById(R.id.question_info);
        answerDisplay = (TextView) findViewById(R.id.answer_info);
        scoreDisplay = (TextView) findViewById(R.id.score_info);
        nextButton = (Button) findViewById(R.id.next_answer_button);
        prevButton = (Button) findViewById(R.id.prev_answer_button);
        mainMenuButton = (Button) findViewById(R.id.main_menu_button);

        loadData();
        triviaGameManager = new TriviaGameManager(triviaGameLevel);

        displayAnsweredQuestion(questionIndex);
        displayAnswerFeedback(questionIndex);
        displayScoreInfo();

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(questionIndex +1 < answerInfo.getNumberOfQsAnswered()){
                    questionIndex++;
                    displayAnsweredQuestion(questionIndex);
                    displayAnswerFeedback(questionIndex);
                }
                else{
                    //Do nothing as you have reached the last question answered-no subsequent questions to view
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(questionIndex > 0){
                    questionIndex--;
                    displayAnsweredQuestion(questionIndex);
                    displayAnswerFeedback(questionIndex);
                }
                else{
                    //Do nothing as you have reached the first question answered-no preceding questions to view
                }
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMainMenu();
            }
        });
    }

    private void displayScoreInfo(){
        if(answerInfo.getNumberOfQsAnswered() > 0) {
            scoreString = "You answered " + answerInfo.getNumberOfQsAnswered() + " questions and got " + answerInfo.getScore() + " correct.";
        }
        // Else statement in case no questions are answered during the 2 minutes
        else{
            scoreString= "Unfortunately you didn't get any points.";
        }
        scoreDisplay.setText(scoreString);
    }

    private void displayAnsweredQuestion(int qNo){
        if(answerInfo.getNumberOfQsAnswered() > 0) {
            question = answerInfo.getAnsweredQuestion(questionIndex);
        }
        // Else statement in case no questions are answered during the 2 minutes
        else{
            question = "You didn't answer any questions, why not have another go!";
        }
        questionDisplay.setText(question);
    }

    private void displayAnswerFeedback(int qNo){
        if(answerInfo.getNumberOfQsAnswered() > 0) {
            int answerGiven = answerInfo.getInputAnsweredByQNo(questionIndex);
            boolean trueFalse = triviaGameManager.checkTriviaQuestionAnswer(questionIndex);

            answerText = generateAnswerResponse(answerGiven, trueFalse);
        }
        // This else replaces the answer feedback if no questions are answered.
        else{
            answerText = "You never lose when you learn!";
        }
        answerDisplay.setText(answerText);
    }

    private String generateAnswerResponse(int answerGiven, boolean trueFalse){
        String extraInfo = answerInfo.getExtraAnswerInfo(questionIndex);

        if(answerGiven == 0 && trueFalse == true){
            return "You said this statement is true-you're correct. " + extraInfo;
        }
        if(answerGiven == 1 && trueFalse == false){
            return "You said this statement is false-you're correct. "+ extraInfo;
        }
        if(answerGiven == 0 && trueFalse == false){
            return "You said this statement is true-actually it's false. "+ extraInfo;
        }
        if(answerGiven == 1 && trueFalse == true){
            return "You answered false-but actually this statement is true! Who knew! "+ extraInfo;
        }
        // This else statement should never be used
        else{
            return "Something's gone wrong here...";
        }
    }

    public void openMainMenu(){
        if(triviaGameLevel == 1){
            Intent intent = new Intent(this, LevelSelectionWorldOne.class);
            startActivity(intent);
        }
        if(triviaGameLevel == 2){
            Intent intent = new Intent(this, LevelSelectionWorldTwo.class);
            startActivity(intent);
        }
        if(triviaGameLevel == 3){
            Intent intent = new Intent(this, LevelSelectionWorldThree.class);
            startActivity(intent);
        }
    }

    // Checks the world theme so that the questions can be set accordingly
    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        triviaGameLevel = sharedPreferences.getInt("triviaGameTheme", 0);
    }
}
