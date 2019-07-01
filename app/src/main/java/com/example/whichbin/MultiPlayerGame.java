package com.example.whichbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiPlayerGame extends AppCompatActivity {

    private TextView player1View, player2View;
    private Button p1Option1, p1Option2, p1Option3, p1Option4, p2Option1, p2Option2, p2Option3, p2Option4, nextButton;
    private int currentIndex = 0, player1Score = 0, player2Score = 0;

    private MultiplayerGameQuestions[] questions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.m_question_one, "Excess of carbon dioxide in the atmosphere", "Excess of nitrogen in the atmosphere", "Excess of carbon dioxide in the atmosphere", "Heat from cooking fires \n of ever-increasing population", " \n" +
                    "None of the above" ),
            new MultiplayerGameQuestions(R.string.m_question_two, "Expanding glaciers", "Sea levels rising", "Flooding in coastal cities", "Expanding glaciers", "Extreme weather")

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_game);

        player1View = findViewById(R.id.p1TextView);
        player2View = findViewById(R.id.p2TextView);
        final int question = questions[currentIndex].getQuestion();
        player1View.setText(question);
        player2View.setText(question);

        p1Option1 = findViewById(R.id.p1Button1);
        p2Option1 = findViewById(R.id.p2Button1);
        final String option1Text = questions[currentIndex].getOption1();
        p1Option1.setText(option1Text);
        p2Option1.setText(option1Text);
        p1Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP1(questions[currentIndex].getOption1());
            }
        });

        p2Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP2(questions[currentIndex].getOption1());
            }
        });

        p1Option2 = findViewById(R.id.p1Button2);
        p2Option2 = findViewById(R.id.p2Button2);
        final String option2Text = questions[currentIndex].getOption2();
        p1Option2.setText(option2Text);
        p2Option2.setText(option2Text);
        p1Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP1(questions[currentIndex].getOption2());
            }
        });

        p2Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP2(questions[currentIndex].getOption2());
            }
        });

        p1Option3 = findViewById(R.id.p1Button3);
        p2Option3 = findViewById(R.id.p2Button3);
        final String option3Text = questions[currentIndex].getOption3();
        p1Option3.setText(option3Text);
        p2Option3.setText(option3Text);
        p1Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP1(questions[currentIndex].getOption3());
            }
        });

        p2Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP2(questions[currentIndex].getOption3());
            }
        });

        p1Option4 = findViewById(R.id.p1Button4);
        p2Option4 = findViewById(R.id.p2Button4);
        final String option4Text = questions[currentIndex].getOption4();
        p1Option4.setText(option4Text);
        p2Option4.setText(option4Text);
        p1Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP1(questions[currentIndex].getOption4());
            }
        });
        p2Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerP2(questions[currentIndex].getOption4());
            }
        });

        nextButton = findViewById(R.id.mpNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1);
                if ((currentIndex + 1) <= questions.length){
                    int nextQuestion = questions[currentIndex].getQuestion();
                    final String nextOption1Text = questions[currentIndex].getOption1();
                    final String nextOption2Text = questions[currentIndex].getOption2();
                    final String nextOption3Text = questions[currentIndex].getOption3();
                    final String nextOption4Text = questions[currentIndex].getOption4();
                    player1View.setText(nextQuestion);
                    player2View.setText(nextQuestion);
                    p1Option1.setText(nextOption1Text);
                    p2Option1.setText(nextOption1Text);
                    p1Option2.setText(nextOption2Text);
                    p2Option2.setText(nextOption2Text);
                    p1Option3.setText(nextOption3Text);
                    p2Option3.setText(nextOption3Text);
                    p1Option4.setText(nextOption4Text);
                    p2Option4.setText(nextOption4Text);
                }

                else{
                    String player1 = "Your score is " + player1Score + "!";
                    String player2 = "Your score is " + player2Score + "!";
                    player1View.setText(player1);
                    player2View.setText(player2);
                }

            }
        });
    }

    private void checkAnswerP1 (String userPressed){
        String answer = questions[currentIndex].getAnswer();
        if (userPressed.equals(answer)){
            player1Score++;
        }
    }

    private void checkAnswerP2 (String userPressed){
        String answer = questions[currentIndex].getAnswer();
        if (userPressed.equals(answer)){
            player2Score++;
        }
    }
}
