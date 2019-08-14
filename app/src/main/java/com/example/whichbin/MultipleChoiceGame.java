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
            new MultiplayerGameQuestions(R.string.a_mcq_questions_nine, 3, "Turn off your lights more often", "Drive a hybrid car", "Eat a plant-based diet", "Hang your clothes to dry"),
            new MultiplayerGameQuestions(R.string.m_question_ten, 3, "10,500", "1,500,000","150,000", "1,500")
    };

    private MultiplayerGameQuestions[] scienceQuestions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.s_mcq_question_one, 2,"Carbon Dioxide", "Nitrogen", "Water Vapour", "Methane"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_two, 1,"17 to 22℃ cooler", "17 to 22℃ warmer", "6 to 11℃ cooler", "6 to 11℃ warmer"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_three, 4, "Using remote sensing from space with satellites", "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level", "By collecting \"proxy data\" from tree rings, ice cores and historical records", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_four, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_five, 2,"A bicycle made of carbon fibre", "A cycle in which carbon flows between the atmosphere, land, and ocean", "the journey water takes as it moves from the land to the sky and back again. ", "None of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_six, 1,"Forests serve as a sink in the carbon cycle", "Trees provide building materials", "Trees are an important food source", "Leaves of trees reflect all sunlight away from the Earth"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_seven, 1,"17 to 22℃ cooler", "17 to 22℃ warmer", "6 to 11℃ cooler", "6 to 11℃ warmer"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_eight, 4, "Using remote sensing from space with satellites", "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level", "By collecting \"proxy data\" from tree rings, ice cores and historical records", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_nine, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_ten, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above")
    };

    private MultiplayerGameQuestions[] natureQuestions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.n_mcq_question_one, 1,"They aren’t as hungry because it’s so hot", "Warmer water makes them sleepy", "Their migratory patterns are changing", "Summer seems longer so fish are schooling less"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_two, 4,"Water shortages", "Power outages", "Higher electricity and gas prices", "All of the above"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_three, 3, "Too many people with the same name slows emergency response", "There are not enough celebrities focused on climate issues", "The need to upgrade aging infrastructure", "People can’t identify the needed solutions so take no action"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_four, 3,"Increasing extreme precipitation everywhere", "Increasing length of droughts everywhere", "Increase in both wet and dry extremes", "Climate change doesn’t alter precipitation"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_five, 2,"Heavy rain increases the risk of animals transmitting rabies", "Milder winters increase the tick density & risk of lyme", "Dry summers & drought increase the risk of West Nile virus", "Snow storms increase mosquito density & risk of malaria"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_six, 2,"Carbon Dioxide", "Nitrogen", "Water Vapour", "Methane"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_seven, 1,"17 to 22℃ cooler", "17 to 22℃ warmer", "6 to 11℃ cooler", "6 to 11℃ warmer"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_eight, 4, "Using remote sensing from space with satellites", "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level", "By collecting \"proxy data\" from tree rings, ice cores and historical records", "All of the above"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_nine, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_ten, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above")
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
