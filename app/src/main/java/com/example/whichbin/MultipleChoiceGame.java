package com.example.whichbin;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MultipleChoiceGame extends AppCompatActivity {

    private TextView questionView;
    private Button optionOne, optionTwo, optionThree, optionFour;
    private LinearLayout layerOne, layerTwo;
    private ConstraintLayout layout;

    private int scoreCounter;
    private int questionNumber;

    private MultiplayerGameQuestions[] questions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.m_question_one, 2, "Excess of nitrogen in the atmosphere", "Excess of carbon dioxide in the atmosphere", "Heat from cooking fires of ever-increasing population",
                    "None of the above" ),
            new MultiplayerGameQuestions(R.string.m_question_two, 3, "Sea levels rising", "Flooding in coastal cities", "Expanding glaciers", "Extreme weather"),
            new MultiplayerGameQuestions(R.string.m_question_three, 3, "The name of climate change legislation that passed by congress", "When you paint your house green to become an environmentalist", "When the gasses in our atmosphere trap heat and block it from escaping our planet", "When you build a greenhouse"),
            new MultiplayerGameQuestions(R.string.m_question_four, 4, "Divest from fossil fuel companies", "Engage yourself in the science behind climate change", "Vote for political candidates who will advocate for climate-related legislation and policy improvements", "All of the above"),
            new MultiplayerGameQuestions(R.string.m_question_five, 1, "China", "USA", "UK", "Russia"),
            new MultiplayerGameQuestions(R.string.m_question_six, 2, "1%", "14%", "33%", "70%"),
            new MultiplayerGameQuestions(R.string.m_question_seven, 4, "Transportation", "Buildings", "Industry", " Electricity and heat production"),
            new MultiplayerGameQuestions(R.string.m_question_eight, 2, "0.53 degrees", "0.94 degrees", "1.34 degrees", "0.21 degrees"),
            new MultiplayerGameQuestions(R.string.m_question_nine, 3, "Jupiter", "Mars", "Venus", "Earth"),
            new MultiplayerGameQuestions(R.string.m_question_ten, 3, "10,500", "1,500,000","150,000", "1,500")

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_game);

        questionView = (TextView) findViewById(R.id.textView_MCQ);
        optionOne = (Button) findViewById(R.id.button_MCQ_1);
        optionTwo = (Button) findViewById(R.id.button_MCQ_2);
        optionThree = (Button) findViewById(R.id.button_MCQ_3);
        optionFour = (Button) findViewById(R.id.button_MCQ_4);
        layerOne = (LinearLayout) findViewById(R.id.linearLayout_MCQ_1);
        layerTwo = (LinearLayout) findViewById(R.id.linearLayout_MCQ_2);
        layout = (ConstraintLayout) findViewById(R.id.constraintLayout_MCQ);

        optionOne.setOnClickListener(clickListener);
        optionTwo.setOnClickListener(clickListener);
        optionThree.setOnClickListener(clickListener);
        optionFour.setOnClickListener(clickListener);

        showNextQuestion();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkAnswer(view);
            questionNumber++;
            if(questionNumber < questions.length){
                showNextQuestion();
            }
            else {
                for ( int i = 0; i < layerOne.getChildCount();  i++ ){
                    View v = layerOne.getChildAt(i);
                    v.setEnabled(false);
                }
                for ( int i = 0; i < layerTwo.getChildCount();  i++ ){
                    View v = layerTwo.getChildAt(i);
                    v.setEnabled(false);
                }
                questionView.setText("Your final score is " + scoreCounter + "!");
            }
        }
    };

    private void showNextQuestion(){
        questionView.setText(questions[questionNumber].getQuestion());
        optionOne.setText(questions[questionNumber].getOption1());
        optionTwo.setText(questions[questionNumber].getOption2());
        optionThree.setText(questions[questionNumber].getOption3());
        optionFour.setText(questions[questionNumber].getOption4());
    }

    private void checkAnswer(View clicked){
        int selectedAnswer = Integer.parseInt((String)clicked.getTag());
        if(selectedAnswer == questions[questionNumber].getAnswer()){
            scoreCounter++;
        }
    }
}
