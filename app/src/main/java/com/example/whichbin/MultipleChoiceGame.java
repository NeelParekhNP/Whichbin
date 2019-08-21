package com.example.whichbin;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MultipleChoiceGame extends AppCompatActivity {

    private TextView questionView, feedbackView;
    private Button optionOne, optionTwo, optionThree, optionFour, mainMenu, nextChevron, previousChevron;
    private LinearLayout layerOne, layerTwo;

    private int scoreCounter;
    private int questionNumber;
    private int levelTheme;
    private int screenWidth;
    private int screenHeight;
    private int currentFeedbackNumber;
    private MultipleChoiceQuestions[] questionSet;
    private String[] feedbackSet;
    private String[] userAnswer = new String[10];
    private Intent myIntent;

    public static final String LEVEL_TWO_WORLD_ONE_STATUS = "levelTwoWorldOneStatus";
    public static final String LEVEL_TWO_WORLD_TWO_STATUS = "levelTwoWorldTwoStatus";
    public static final String LEVEL_TWO_WORLD_THREE_STATUS = "levelTwoWorldThreeStatus";

    private MultipleChoiceQuestions[] scienceQuestions = new MultipleChoiceQuestions[]{
            new MultipleChoiceQuestions(R.string.s_mcq_question_one, 2,"Carbon Dioxide", "Nitrogen", "Water Vapour", "Methane"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_two, 1,"17 to 22℃ cooler", "17 to 22℃ warmer", "6 to 11℃ cooler", "6 to 11℃ warmer"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_three, 4, "Using remote sensing from space with satellites", "By ground-based measurements of surface temperature, carbon dioxide concentration and sea level", "By collecting \"proxy data\" from tree rings, ice cores and historical records", "All of the above"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_four, 4,"Carbon Dioxide", "Methane", "Water Vapour", "All of the above"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_six, 2,"A bicycle made of carbon fibre", "A cycle in which carbon flows between the atmosphere, land, and ocean", "the journey water takes as it moves from the land to the sky and back again. ", "None of the above"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_five, 1,"Forests serve as a sink in the carbon cycle", "Trees provide building materials", "Trees are an important food source", "Leaves of trees reflect all sunlight away from the Earth"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_seven, 1,"10%", "30%", "50%", "75%"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_eight, 4, "Protein", "Zinc", "Iron", "All of the above"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_nine, 1,"Heat waves", "Hurricanes", "Floods", "Blizzards"),
            new MultipleChoiceQuestions(R.string.s_mcq_question_ten, 3,"USA", "India", "China", "UK")
    };
    
    private MultipleChoiceQuestions[] natureQuestions = new MultipleChoiceQuestions[]{
            new MultipleChoiceQuestions(R.string.n_mcq_question_one, 3,"They aren’t as hungry because it’s so hot", "Warmer water makes them sleepy", "Their migratory patterns are changing", "Summer seems longer so fish are schooling less"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_two, 4,"Water shortages", "Power outages", "Higher electricity and gas prices", "All of the above"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_three, 3, "Too many people with the same name slows emergency response", "There are not enough celebrities focused on climate issues", "The need to upgrade aging infrastructure", "People can’t identify the needed solutions so take no action"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_four, 3,"Increasing extreme precipitation everywhere", "Increasing length of droughts everywhere", "Increase in both wet and dry extremes", "Climate change doesn’t alter precipitation"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_five, 2,"Heavy rain increases the risk of animals transmitting rabies", "Milder winters increase the tick density & risk of lyme", "Dry summers & drought increase the risk of West Nile virus", "Snow storms increase mosquito density & risk of malaria"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_six,4,"400 thousand", "4 million", "40 million", "4 billion"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_seven, 2,"6%", "11%", "17%", "23%"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_eight, 3, "Oceans", "Rainforest's", "Ozone layer", "Carbon Dioxide"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_nine, 3,"40%", "50%", "60%", "70%"),
            new MultipleChoiceQuestions(R.string.n_mcq_question_ten, 1,"Coral", "Plankton", "Fish", "Whales")
    };


    private MultipleChoiceQuestions[] reduceImpactQuestions = new MultipleChoiceQuestions[]{
            new MultipleChoiceQuestions(R.string.a_mcq_question_one, 1, "Solar power", "Coal", "Fossil fuels", "None of the above" ),
            new MultipleChoiceQuestions(R.string.a_mcq_question_two, 2, "100%", "15-20%", "0%", "10-15%"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_three, 4, "Divest from fossil fuel companies", "Engage yourself in the science behind climate change", "Vote for political candidates who will advocate for climate-related legislation and policy improvements", "All of the above"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_four, 3, "Car", "Bus", "Bicycle", "Airplane"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_five, 2, "Use more cars", "Plant 1 trillion trees", "Travel in public transport", "Do nothing"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_six, 3, "Vegetarian", "Non-vegetarian", "Vegan", "Pescatarian"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_seven, 3, "Recycle", "Dump in river", "Send all trash to dumping sites", "Do nothing and hope for the best"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_eight, 4, "Insulate your walls", "Get double glazed windows", "Use less hot water", "All of the above"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_nine, 3, "Turn off your lights more often", "Drive a hybrid car", "Eat a plant-based diet", "Hang your clothes to dry"),
            new MultipleChoiceQuestions(R.string.a_mcq_question_ten, 1, "LED bulbs", "Halogen bulbs","Incandescent bulbs", "Fluorescent bulbs")
    };


    private String[] scienceFeedback = new String[]{
            "Nitrogen gas isn't a greenhouse gas, because it's transparent to infrared light.",
            "The greenhouse effect traps heat inside the atmosphere. Therefore, getting rid of the greenhouse effect would lead to a cooler earth.",
            "Scientists use several techniques to collect evidence about climate including - using remote sensing from space with satellites, by ground-based measurements of surface temperature, carbon dioxide concentration and sea level, by collecting \"proxy data\" from tree rings, ice cores and historical records and many more.",
            "Carbon dioxide, water vapour and methane are all greenhouse gases as they trap heat in the atmosphere.",
            "There are four main steps in the carbon cycle - 1) Carbon enters the atmosphere in the form of carbon dioxide 2) Carbon is absorbed by producers for photosynthesis 3) Animals eat these producers and release most of the carbon dioxide via respiration and eventually die 4) Decomposers break down the dead animals and release the leftover carbon into the atmosphere leading back to the first step of the carbon cycle",
            "As forests have many plants and trees they are able to remove a large potion of carbon dioxide from the atmosphere.",
            "Humans are responsible for a 10% increase in global average surface temperature.",
            "High C02 levels will decrease the concentration of protein, zinc and iron because higher levels of CO2 will increase the synthesis of carbohydrates and in turn reduce the synthesis of other nutrients.",
            "Heatwaves cause more deaths annually than hurricanes, floods and blizzards",
            "China produces the has the highest per capita CO2 emissions in the world."
    };

    private String[] natureFeedback = new String[]{
            "Fishes, whales and other marine mammals are changing their migratory patterns and moving to colder areas of the ocean.",
            "Extreme events like hurricanes, droughts, and wildfires can cause water shortages, power outages, higher electricity prices and higher gas prices.",
            "Cities and rural communities need to upgrade aging infrastructure to adapt to the current climate change status",
            "Climate change is altering precipitation in the US by increasing in both wet and dry extremes ",
            "Climate factors such as milder winters increase the tick density & risk of lyme",
            "4 billion people are vulnerable to the effects of climate change. That is more than 50% of the world population.",
            "Deforestation is responsible for 11% of all global greenhouse gas emissions caused by humans",
            "The ozone layer protects us from ultraviolet radiation. The increase in air pollution is damaging the ozone layer, resulting in an increase of ultraviolet radiation on earth.",
            "The wildlife population has dropped by 60% in the last 40 years due to human activity such as deforestation and hunting.",
            "Corals are Often called \"rainforests of the sea\", shallow coral reefs form some of Earth's most diverse ecosystems. The rising acidity of the oceans threatens coral reefs by making it harder for corals to build their skeletons"
    };

    private String[] reduceImpactFeedback = new String[]{
            "Solar power is environmentally friendly. Solar power uses energy from the sunlight therefore, it doesn't release and carbon dioxide into the atmosphere, unlike coal and fossil fuels.",
            "A little as 1.5°C increase in temperature can put 15-20% species at risk of extinction.",
            "There are many things a person can do to help flight climate change, some of them are - divesting from using fossil fuel companies, engage themselves in the science behind climate change (a bit like you're doing right now) and vote for political candidates who will advocate for climate-related legislation and policy improvements.",
            "Using vehicles that do not require fossil fuels to run are the best vehicles to reduce the impact on climate change, such as bicycles.",
            "According to scientists the best solution to tackle climate change is by planting 1 trillion trees.",
            "Adopting a vegan diet means animal products are not consumed. This means it will have a low impact on climate change.",
            "Household waste that is recyclable should be recycled so that there's less waste in dumping sites and rivers.",
            "Insulating walls, getting double glazed windows and using less hot water are all different ways to reduce impact on climate change. Insulating walls and getting double windows will keep your house warmer and therefore reducing the need for heating.",
            "Eating a plant-based diet has higher impact on reducing climate change than turning off lights more often, driving a hybrid car and hanging your clothes to dry rather than using a tumble dryer. However, all those things help to reduce climate change some just more than the other.",
            "LED bulbs use the lease electricity to light up and therefore they have the least impact on climate change."

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_game);
        
        loadData();

        /** Get's the physical size of phone screen */

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        final ImageView backgroundZero = (ImageView) findViewById(R.id.background_0);
        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_1);
        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_2);
        final ImageView backgroundThree = (ImageView) findViewById(R.id.background_3);

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

        questionView = (TextView) findViewById(R.id.textView_MCQ);
        feedbackView = (TextView) findViewById(R.id.MCQ_feedback_textView);
        optionOne = (Button) findViewById(R.id.button_MCQ_1);
        optionTwo = (Button) findViewById(R.id.button_MCQ_2);
        optionThree = (Button) findViewById(R.id.button_MCQ_3);
        optionFour = (Button) findViewById(R.id.button_MCQ_4);
        mainMenu = (Button) findViewById(R.id.button_MCQ_menu);
        nextChevron = (Button) findViewById(R.id.MCQ_next_feedback);
        previousChevron = (Button) findViewById(R.id.MCQ_previous_feedback);
        layerOne = (LinearLayout) findViewById(R.id.linearLayout_MCQ_1);
        layerTwo = (LinearLayout) findViewById(R.id.linearLayout_MCQ_2);

        optionOne.setOnClickListener(clickListener);
        optionTwo.setOnClickListener(clickListener);
        optionThree.setOnClickListener(clickListener);
        optionFour.setOnClickListener(clickListener);
        previousChevron.setOnClickListener(feedbackClickListener);
        nextChevron.setOnClickListener(feedbackClickListener);

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                startActivity(myIntent);
            }
        });

        switch (levelTheme){
            case 1 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldOne.class);
                questionSet = scienceQuestions;
                feedbackSet = scienceFeedback;
                break;
            case 2 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldTwo.class);
                questionSet = natureQuestions;
                feedbackSet = natureFeedback;
                break;
            case 3 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldThree.class);
                questionSet = reduceImpactQuestions;
                feedbackSet = reduceImpactFeedback;
                break;
            default:
                questionSet = scienceQuestions;
        }

        View currentButton;
        int imageViewWidth = screenWidth/2;
        int imageViewHeight = screenHeight/5;

        for(int i=0; i<layerOne.getChildCount(); i++) {
            currentButton = layerOne.getChildAt(i);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(imageViewWidth,imageViewHeight);
            currentButton.setLayoutParams(parms);
        }

        for(int i=0; i<layerTwo.getChildCount(); i++) {
            currentButton = layerTwo.getChildAt(i);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(imageViewWidth,imageViewHeight);
            currentButton.setLayoutParams(parms);
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
                feedbackView.setVisibility(View.VISIBLE);
                mainMenu.setVisibility(View.VISIBLE);
                nextChevron.setVisibility(View.VISIBLE);
                layerOne.setVisibility(View.GONE);
                layerTwo.setVisibility(View.GONE);
                questionView.setText("Your final score is " + scoreCounter + "!");
                feedbackView.setText("" + (currentFeedbackNumber+1)+". " + userAnswer[currentFeedbackNumber] + " - " + feedbackSet[currentFeedbackNumber]);
            }

        }
    };

    View.OnClickListener feedbackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.MCQ_previous_feedback){
                if(currentFeedbackNumber == 0){
                    previousChevron.setVisibility(View.INVISIBLE);
                }
                else{
                    currentFeedbackNumber--;
                    feedbackView.setText("" + (currentFeedbackNumber+1)+". " + userAnswer[currentFeedbackNumber] + " - " + feedbackSet[currentFeedbackNumber]);
                    nextChevron.setVisibility(View.VISIBLE);
                }
            }
            if(view.getId() == R.id.MCQ_next_feedback){
                if (currentFeedbackNumber == (userAnswer.length-1)){
                    nextChevron.setVisibility(View.INVISIBLE);
                }
                else {
                    currentFeedbackNumber++;
                    feedbackView.setText("" + (currentFeedbackNumber+1)+". " + userAnswer[currentFeedbackNumber] + " - " + feedbackSet[currentFeedbackNumber]);
                    previousChevron.setVisibility(View.VISIBLE);
                }
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
            userAnswer[questionNumber] = "Correct!";
            scoreCounter++;
        }
        else {
            userAnswer[questionNumber] = "Incorrect!";
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
