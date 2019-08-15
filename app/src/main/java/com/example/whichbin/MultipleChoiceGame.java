package com.example.whichbin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MultipleChoiceGame extends AppCompatActivity {

    private TextView questionView;
    private Button optionOne, optionTwo, optionThree, optionFour, mainMenu;
    private LinearLayout layerOne, layerTwo;

    private int scoreCounter;
    private int questionNumber;
    private int levelTheme;
    private MultiplayerGameQuestions[] questionSet;

    public static final String LEVEL_TWO_WORLD_ONE_STATUS = "levelTwoWorldOneStatus";
    public static final String LEVEL_TWO_WORLD_TWO_STATUS = "levelTwoWorldTwoStatus";
    public static final String LEVEL_TWO_WORLD_THREE_STATUS = "levelTwoWorldThreeStatus";

    private MultiplayerGameQuestions[] scienceQuestions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.s_mcq_question_one, 2,"Carbon Dioxide", "Nitrogen", "Water Vapour", "Methane"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_two, 1,"17 to 22℃ cooler", "17 to 22℃ warmer", "6 to 11℃ cooler", "6 to 11℃ warmer"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_three, 4, "Using remote sensing from space with satellites", "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level", "By collecting \"proxy data\" from tree rings, ice cores and historical records", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_four, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_six, 2,"A bicycle made of carbon fibre", "A cycle in which carbon flows between the atmosphere, land, and ocean", "the journey water takes as it moves from the land to the sky and back again. ", "None of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_five, 1,"Forests serve as a sink in the carbon cycle", "Trees provide building materials", "Trees are an important food source", "Leaves of trees reflect all sunlight away from the Earth"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_seven, 1,"10%", "30%", "50%", "75%"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_eight, 4, "Protein", "Zinc", "Iron", "All of the above"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_nine, 1,"Heat waves", "Hurricanes", "Floods", "Blizzards"),
            new MultiplayerGameQuestions(R.string.s_mcq_question_ten, 3,"USA", "India", "China", "UK")
    };
    
    private MultiplayerGameQuestions[] natureQuestions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.n_mcq_question_one, 1,"They aren’t as hungry because it’s so hot", "Warmer water makes them sleepy", "Their migratory patterns are changing", "Summer seems longer so fish are schooling less"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_two, 4,"Water shortages", "Power outages", "Higher electricity and gas prices", "All of the above"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_three, 3, "Too many people with the same name slows emergency response", "There are not enough celebrities focused on climate issues", "The need to upgrade aging infrastructure", "People can’t identify the needed solutions so take no action"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_four, 3,"Increasing extreme precipitation everywhere", "Increasing length of droughts everywhere", "Increase in both wet and dry extremes", "Climate change doesn’t alter precipitation"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_five, 2,"Heavy rain increases the risk of animals transmitting rabies", "Milder winters increase the tick density & risk of lyme", "Dry summers & drought increase the risk of West Nile virus", "Snow storms increase mosquito density & risk of malaria"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_six,4,"400 thousand", "4 million", "40 million", "4 billion"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_seven, 2,"6%", "11%", "17%", "23%"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_eight, 3, "Oceans", "Rainforest's", "Ozone layer", "Carbon Dioxide"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_nine, 3,"40%", "50%", "60%", "70%"),
            new MultiplayerGameQuestions(R.string.n_mcq_question_ten, 1,"Coral", "Plankton", "Fish", "Whales")
    };

    private MultiplayerGameQuestions[] reduceImpactQuestions = new MultiplayerGameQuestions[]{
            new MultiplayerGameQuestions(R.string.a_mcq_question_one, 1, "Solar power", "Coal", "Fossil fuels", "None of the above" ),
            new MultiplayerGameQuestions(R.string.a_mcq_question_two, 2, "100%", "15-20%", "0%", "10-15%"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_three, 4, "Divest from fossil fuel companies", "Engage yourself in the science behind climate change", "Vote for political candidates who will advocate for climate-related legislation and policy improvements", "All of the above"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_four, 3, "Car", "Bus", "Bicycle", "Airplane"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_five, 2, "Use more cars", "Plant 1 trillion trees", "Travel in public transport", "Do nothing"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_six, 3, "Vegetarian", "Non-vegetarian", "Vegan", "Pescatarian"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_seven, 3, "Recycle", "Dump in river", "Send all trash to dumping sites", "Do nothing and hope for the best"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_eight, 4, "Insulate your walls", "Get double glazed windows", "Use less hot water", "All of the above"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_nine, 3, "Turn off your lights more often", "Drive a hybrid car", "Eat a plant-based diet", "Hang your clothes to dry"),
            new MultiplayerGameQuestions(R.string.a_mcq_question_ten, 1, "LED bulbs", "Halogen bulbs","Incandescent bulbs", "Fluorescent bulbs")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_game);
        
        loadData();

        questionView = (TextView) findViewById(R.id.textView_MCQ);
        optionOne = (Button) findViewById(R.id.button_MCQ_1);
        optionTwo = (Button) findViewById(R.id.button_MCQ_2);
        optionThree = (Button) findViewById(R.id.button_MCQ_3);
        optionFour = (Button) findViewById(R.id.button_MCQ_4);
        mainMenu = (Button) findViewById(R.id.button_MCQ_menu);
        layerOne = (LinearLayout) findViewById(R.id.linearLayout_MCQ_1);
        layerTwo = (LinearLayout) findViewById(R.id.linearLayout_MCQ_2);

        optionOne.setOnClickListener(clickListener);
        optionTwo.setOnClickListener(clickListener);
        optionThree.setOnClickListener(clickListener);
        optionFour.setOnClickListener(clickListener);

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent myIntent = new Intent(getBaseContext(), LevelSelection.class);
                startActivity(myIntent);
            }
        });

        switch (levelTheme){
            case 1 :
                questionSet = scienceQuestions;
                break;
            case 2 :
                questionSet = natureQuestions;
                break;
            case 3 :
                questionSet = reduceImpactQuestions;
                break;
            default:
                questionSet = scienceQuestions;
        }

        mainMenu.setVisibility(View.INVISIBLE);

        showNextQuestion();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkAnswer(view);
            questionNumber++;
            if(questionNumber < questionSet.length){
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
                mainMenu.setVisibility(View.VISIBLE);
                layerOne.setVisibility(View.GONE);
                layerTwo.setVisibility(View.GONE);
            }
        }
    };

    private void showNextQuestion(){
        questionView.setText(questionSet[questionNumber].getQuestion());
        optionOne.setText(questionSet[questionNumber].getOption1());
        optionTwo.setText(questionSet[questionNumber].getOption2());
        optionThree.setText(questionSet[questionNumber].getOption3());
        optionFour.setText(questionSet[questionNumber].getOption4());
    }

    private void checkAnswer(View clicked){
        int selectedAnswer = Integer.parseInt((String)clicked.getTag());
        if(selectedAnswer == questionSet[questionNumber].getAnswer()){
            scoreCounter++;
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        levelTheme = sharedPreferences.getInt("multipleChoiceGameTheme", 0);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(scoreCounter >= 5) {
            switch (levelTheme){
                case 1 :
                    editor.putBoolean(LEVEL_TWO_WORLD_ONE_STATUS, true);
                    break;
                case 2 :
                    editor.putBoolean(LEVEL_TWO_WORLD_TWO_STATUS,true);
                    break;
                case 3:
                    editor.putBoolean(LEVEL_TWO_WORLD_THREE_STATUS,true);
            }

        }
        editor.commit();
    }
}
