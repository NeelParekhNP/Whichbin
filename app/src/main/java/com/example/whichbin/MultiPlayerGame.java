package com.example.whichbin;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MultiPlayerGame extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 20000;
    private TextView player1View, player2View,countdown1,countdown2, player1Score, player2Score, player1QuestionNumber, player2QuestionNumber, winnerTextView;
    private RadioButton player1RB1, player1RB2, player1RB3, player1RB4;
    private RadioButton player2RB1, player2RB2, player2RB3, player2RB4;
    private Button mainMenu;
    private RadioGroup player1Group, player2Group;
    private int questionCounter;
    private int currentQuestion;
    private int p1Score, p2Score;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    /** Array of questions */
    private MultipleChoiceQuestions[] questions = new MultipleChoiceQuestions[]{
            new MultipleChoiceQuestions(R.string.m_question_one, 2, "Excess of nitrogen in the atmosphere", "Excess of carbon dioxide in the atmosphere", "Heat from cooking fires of ever-increasing population",
                    "None of the above" ),
            new MultipleChoiceQuestions(R.string.m_question_two, 3, "Sea levels rising", "Flooding in coastal cities", "Expanding glaciers", "Extreme weather"),
            new MultipleChoiceQuestions(R.string.m_question_three, 3, "The name of climate change legislation that passed by congress", "When you paint your house green to become an environmentalist", "When the gasses in our atmosphere trap heat and block it from escaping our planet", "When you build a greenhouse"),
            new MultipleChoiceQuestions(R.string.m_question_four, 4, "Divest from fossil fuel companies", "Engage yourself in the science behind climate change", "Vote for political candidates who will advocate for climate-related legislation and policy improvements", "All of the above"),
            new MultipleChoiceQuestions(R.string.m_question_five, 1, "China", "USA", "UK", "Russia"),
            new MultipleChoiceQuestions(R.string.m_question_six, 2, "1%", "14%", "33%", "70%"),
            new MultipleChoiceQuestions(R.string.m_question_seven, 4, "Transportation", "Buildings", "Industry", " Electricity and heat production"),
            new MultipleChoiceQuestions(R.string.m_question_eight, 2, "0.53 degrees", "0.94 degrees", "1.34 degrees", "0.21 degrees"),
            new MultipleChoiceQuestions(R.string.m_question_nine, 3, "Jupiter", "Mars", "Venus", "Earth"),
            new MultipleChoiceQuestions(R.string.m_question_ten, 3, "10,500", "1,500,000","150,000", "1,500")

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_multi_player_game);

        mainMenu = (Button) findViewById(R.id.button_menu);
        winnerTextView= (TextView) findViewById(R.id.textView_winner_text);
        player1View = (TextView) findViewById(R.id.p1TextView);
        player2View = (TextView) findViewById(R.id.p2TextView);
        player1Score = (TextView) findViewById(R.id.textView_player1_score);
        player2Score = (TextView) findViewById(R.id.textView_player2_score);
        player1QuestionNumber =(TextView)  findViewById(R.id.textView_player1_question_number);
        player2QuestionNumber = (TextView) findViewById(R.id.textView_player2_question_number);
        countdown1 = (TextView) findViewById(R.id.textview_countdown_1);
        countdown2 = (TextView) findViewById(R.id.textview_countdown_2);
        player1Group= (RadioGroup) findViewById(R.id.radio_group1);
        player2Group= (RadioGroup) findViewById(R.id.radio_group2);
        player1RB1 = (RadioButton) findViewById(R.id.radio_button1_option1);
        player1RB2 = (RadioButton) findViewById(R.id.radio_button1_option2);
        player1RB3 = (RadioButton) findViewById(R.id.radio_button1_option3);
        player1RB4 = (RadioButton) findViewById(R.id.radio_button1_option4);
        player2RB1 = (RadioButton) findViewById(R.id.radio_button2_option1);
        player2RB2 = (RadioButton) findViewById(R.id.radio_button2_option2);
        player2RB3 = (RadioButton) findViewById(R.id.radio_button2_option3);
        player2RB4 = (RadioButton) findViewById(R.id.radio_button2_option4);
        mainMenu.setOnClickListener(clickListener);

        showNextQuestion();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getBaseContext(), MainMenu.class);
            startActivity(myIntent);
        }
    };

    /** Sets up the next question */
    private void showNextQuestion(){
        player1Score.setVisibility(View.GONE);
        player2Score.setVisibility(View.GONE);
        winnerTextView.setVisibility(View.GONE);
        mainMenu.setVisibility(View.GONE);
        currentQuestion = questions[questionCounter].getQuestion();
        player1View.setText(currentQuestion);
        player2View.setText(currentQuestion);
        player1RB1.setText(questions[questionCounter].getOption1());
        player1RB2.setText(questions[questionCounter].getOption2());
        player1RB3.setText(questions[questionCounter].getOption3());
        player1RB4.setText(questions[questionCounter].getOption4());
        player2RB1.setText(questions[questionCounter].getOption1());
        player2RB2.setText(questions[questionCounter].getOption2());
        player2RB3.setText(questions[questionCounter].getOption3());
        player2RB4.setText(questions[questionCounter].getOption4());
        for (int i = 0; i < player1Group.getChildCount(); i++) {
            RadioButton player1Button = findViewById(player1Group.getChildAt(i).getId());
            RadioButton player2Button = findViewById(player2Group.getChildAt(i).getId());
            player1Button.setTextColor(Color.BLACK);
            player2Button.setTextColor(Color.BLACK);
        }
        questionCounter++;
        String questionNumber = "Question " + questionCounter;
        player1QuestionNumber.setText(questionNumber);
        player2QuestionNumber.setText(questionNumber);
        timeLeftInMillis =COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    /** Starts a countdown in the background */
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                for (int i = 0; i < player1Group.getChildCount(); i++) {
                    player1Group.getChildAt(i).setEnabled(false);
                    player2Group.getChildAt(i).setEnabled(false);
                }
                checkAnswer();
            }
        }.start();
    }

    /** Displays the countdown */
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdown1.setText(timeFormatted);
        //countdown1.setTextColor(Color.WHITE);
        countdown2.setText(timeFormatted);
        //countdown2.setTextColor(Color.WHITE);
    }

    /** Checks both players answers and checks if there's anymore questions left. If no questions are left it moves on to the winner declaration screen */
    private void checkAnswer(){
        countDownTimer.cancel();
        new CountDownTimer(5000,1000){
            public void onTick(long millisUntilFinished){
                RadioButton rbSelectedPlayer1 = findViewById(player1Group.getCheckedRadioButtonId());
                RadioButton rbSelectedPlayer2 = findViewById(player2Group.getCheckedRadioButtonId());
                int player1Pressed = player1Group.indexOfChild(rbSelectedPlayer1) + 1;
                int player2Pressed = player2Group.indexOfChild(rbSelectedPlayer2) + 1;
                int answer = questions[questionCounter-1].getAnswer();
                RadioButton p1CorrectAnswer = (RadioButton) player1Group.getChildAt(answer-1);
                RadioButton p2CorrectAnswer = (RadioButton) player2Group.getChildAt(answer-1);
                p1CorrectAnswer.setTextColor(Color.GREEN);
                p2CorrectAnswer.setTextColor(Color.GREEN);

                if(player1Pressed == answer){
                    p1Score++;
                }
                else if (player1Pressed == 0){
                    /*player1RB1.setTextColor(Color.RED);
                    player1RB2.setTextColor(Color.RED);
                    player1RB3.setTextColor(Color.RED);
                    player1RB4.setTextColor(Color.RED);
                    p1CorrectAnswer.setTextColor(Color.GREEN);*/
                }
                else{
                    rbSelectedPlayer1.setTextColor(Color.RED);
                }
                if(player2Pressed == answer){
                    p2Score++;
                }
                else if (player2Pressed == 0){
                    /*player2RB1.setTextColor(Color.RED);
                    player2RB2.setTextColor(Color.RED);
                    player2RB3.setTextColor(Color.RED);
                    player2RB4.setTextColor(Color.RED);
                    p2CorrectAnswer.setTextColor(Color.GREEN);*/
                }
                else{
                    rbSelectedPlayer2.setTextColor(Color.RED);
                }
            }

            /** Changes the layout of the activity to show the winner screen */
            public  void onFinish() {
                player1Group.clearCheck();
                player2Group.clearCheck();
                for (int i = 0; i < player1Group.getChildCount(); i++) {
                    player1Group.getChildAt(i).setEnabled(true);
                    player2Group.getChildAt(i).setEnabled(true);
                }
                if (questionCounter < questions.length) {
                    showNextQuestion();
                } else {
                    player1View.setVisibility(View.GONE);
                    player2View.setVisibility(View.GONE);
                    countdown1.setVisibility(View.GONE);
                    countdown2.setVisibility(View.GONE);
                    player1Group.setVisibility(View.GONE);
                    player2Group.setVisibility(View.GONE);
                    player1QuestionNumber.setVisibility(View.GONE);
                    player2QuestionNumber.setVisibility(View.GONE);
                    player1Score.setText("Player 1 score: " + p1Score/5);
                    player2Score.setText("Player 2 score: " + p2Score/5);
                    winnerTextView.setText(winnerChecker());
                    player1Score.setVisibility(View.VISIBLE);
                    player2Score.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);
                }
            }
        }.start();
    }

    /** Compares the players scores and declares a winner */
    private String winnerChecker(){
        if(p1Score>p2Score){
            return "Player 1 is the winner!";
        }
        else if(p2Score>p1Score){
            return "Player 2 is the winner!";
        }
        else{
            return "It's a tie!";
        }
    }
}
