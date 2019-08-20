package com.example.whichbin;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BinGame extends AppCompatActivity {

    private ImageView question, option1, option2, option3;
    private TextView questionTextView, resultsView;
    private LinearLayout allOptions;
    private int currentIndex = 0;
    private int totalCorrect = 0;
    private String header;

    private int levelTheme;
    private int screenWidth;
    private int screenHeight;
    private BinGameQuestions[] questionSet;
    private int [] userAnswers = new int[10];

    private Intent myIntent;

    public static final String LEVEL_ONE_WORLD_ONE_STATUS = "levelOneWorldOneStatus";
    public static final String LEVEL_ONE_WORLD_TWO_STATUS = "levelOneWorldTwoStatus";
    public static final String LEVEL_ONE_WORLD_THREE_STATUS = "levelOneWorldThreeStatus";

    /** Arrays of different sets of questions */

    private BinGameQuestions[] recycleQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.question_one, 3, R.drawable.carrot_organic, "Carrots are organic waste"),
            new BinGameQuestions(R.string.question_two, 1, R.drawable.styrofoam_trash, "Styrofoam isn't currently recyclable."),
            new BinGameQuestions(R.string.question_three, 3, R.drawable.strawberry_organic, "Strawberries are organic waste"),
            new BinGameQuestions(R.string.question_four, 2, R.drawable.newspaper_recycle, "Newspaper can be recycled"),
            new BinGameQuestions(R.string.question_five, 2, R.drawable.can_recycle, "Cans are normally made of aluminium which can be recycled"),
            new BinGameQuestions(R.string.question_six, 1, R.drawable.crisps_packet_trash, "Crisp packets are made of a \"metallised plastic\" that can't be recycled"),
            new BinGameQuestions(R.string.question_seven, 2, R.drawable.bottle_recycle, "Plastic bottles can be recycled"),
            new BinGameQuestions(R.string.question_eight, 1, R.drawable.plate_trash, "Plates are made of ceramic which can't be recycled"),
            new BinGameQuestions(R.string.question_nine, 2, R.drawable.paper_bag_recycle, "Paper bags can be recycled"),
            new BinGameQuestions(R.string.question_ten, 3, R.drawable.apple_oraganic, "Apples are organic waste")
    };

    private BinGameQuestions[] conservationStatusQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.animal_question_one,1,R.drawable.leatherback_turtle_vulnerable, "Leatherback turtles are considered vulnerable. There are only 34,000 - 36,000 nesting females left worldwide."),
            new BinGameQuestions(R.string.animal_question_two,1,R.drawable.white_rhino_vulnerable, "White rhinos are considered vulnerable. However, they are a conservation success story because a few years ago they were nearly instinct."),
            new BinGameQuestions(R.string.animal_question_three,3,R.drawable.sumatran_orangutan_ce, "Sumatran Orangutan are critically endangered. There are only approximately 14,613 left in the wild. "),
            new BinGameQuestions(R.string.animal_question_four,2,R.drawable.white_tiger_endangered, "White tigers are endangered. There are none left in wild and only approximately 200 in captivity."),
            new BinGameQuestions(R.string.animal_question_five,3,R.drawable.amsterdam_albatross_ce, "Amsterdam albatross are critically endangered. There are only approximately 130 left. "),
            new BinGameQuestions(R.string.animal_question_six,2,R.drawable.beluga_whale_endangered, "Beluga whales are endangered. Their population size is between 279 and 386 animals"),
            new BinGameQuestions(R.string.animal_question_seven,2,R.drawable.asian_elephant_endangered, "Asian elephants are endangered. There are only 35,000 - 40,000 left in the wild."),
            new BinGameQuestions(R.string.animal_question_eight,1,R.drawable.giant_panda_vulnerable, "Giant pandas are vulnerable. Like the white rhino, giant pandas are a success story as their numbers have increased by 17% since the 1970's."),
            new BinGameQuestions(R.string.animal_question_nine,3,R.drawable.nubian_giraffe_ce, "Nubian giraffes are critically endangered. It's estiamted that there are only 2,150 left in the wild."),
            new BinGameQuestions(R.string.animal_question_ten,3,R.drawable.adriatic_sturgeon_ce, "Adritaic sturgeon are critically endangered. They're functionally extinct in the wild, but they do bredd in captivity.")
    };

    private BinGameQuestions[] impactQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.impact_question_one,1,R.drawable.gasoline_high, "Fossil fuels release carbon dioxide, a greenhouse gas into the atmosphere. Therefore, it has a high impact on climate change."),
            new BinGameQuestions(R.string.impact_question_two,3,R.drawable.vegan_low, "Being vegan means animal products are not consumed. This means it will have a low impact on climate change."),
            new BinGameQuestions(R.string.impact_question_three,1,R.drawable.deforestation_high, "Trees remove carbon dioxide from the atmosphere and convert it into oxygen. Therefore, deforestation will have a large impact because less carbon dioxide will be removed from the atmosphere. "),
            new BinGameQuestions(R.string.impact_question_four,2,R.drawable.light_medium, "Leaving lights on means electricity is wasted. Therefore, it has a medium impact on climate change."),
            new BinGameQuestions(R.string.impact_question_five,3,R.drawable.bicycle_low, "Riding bikes have a low impact on climate change because they don't use any sort of fuel."),
            new BinGameQuestions(R.string.impact_question_six,1,R.drawable.meat_high, "Eating meat has a high impact because animals need to be raised for food. Animals produce a lot of methane which is a greenhouse gas."),
            new BinGameQuestions(R.string.impact_question_seven,3,R.drawable.solar_power_low, "Solar power is a low impact method of producing electricity because it uses renewable resources."),
            new BinGameQuestions(R.string.impact_question_eight,2,R.drawable.vegeterian_medium, "Being vegetarian has a medium impact because animals don't need to be raised for food, but some animals still need to be raised for milk."),
            new BinGameQuestions(R.string.impact_question_nine,2,R.drawable.bus_medium, "Travelling on buses have a medium impact because even though they burn fuels, they carry more people so it's not as wasteful."),
            new BinGameQuestions(R.string.impact_question_ten,1,R.drawable.car_high, "Cars have a huge impact on climate change. On average cars only carry five people and burn a lot of fuel."),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_game);

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

        allOptions = (LinearLayout) findViewById((R.id.optionsLayout));
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        question = (ImageView) findViewById(R.id.qImageView);
        option1 = (ImageView) findViewById(R.id.gWBinImageView);
        option2 = (ImageView) findViewById(R.id.rBImageView);
        option3 = (ImageView) findViewById(R.id.oWImageView);
        resultsView = (TextView) findViewById(R.id.textView_DND_game_results);

        View theImageViews = null;
        int imageViewWidth = screenWidth/3;
        int imageViewHeight = screenHeight/5;

        for(int i=0; i<allOptions.getChildCount(); i++) {
            theImageViews = allOptions.getChildAt(i);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(imageViewWidth,imageViewHeight);
            theImageViews.setLayoutParams(parms);
        }


        /** The questions and containers will be setup according to which button was pressed in the selection screen */

        switch (levelTheme){
            case 1 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldOne.class);
                questionSet = recycleQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.general_waste));
                option2.setImageDrawable(getDrawable(R.drawable.recycle_bin));
                option3.setImageDrawable(getDrawable(R.drawable.food_waste));
                header = "Which bin does this " + getString(questionSet[currentIndex].getQuestion()) + " belong in?";
                resultsView.setTextSize(18);
                break;
            case 2 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldTwo.class);
                questionSet = conservationStatusQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.vulnerable_box));
                option2.setImageDrawable(getDrawable(R.drawable.endangered_box));
                option3.setImageDrawable(getDrawable(R.drawable.critically_endangered_box));
                header = "Which box does this " + getString(questionSet[currentIndex].getQuestion()) + " belong in?";
                resultsView.setTextSize(16);
                break;
            case 3 :
                myIntent = new Intent(getBaseContext(), LevelSelectionWorldThree.class);
                questionSet = impactQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.high_impact_sign));
                option2.setImageDrawable(getDrawable(R.drawable.medium_impact_sign));
                option3.setImageDrawable(getDrawable(R.drawable.low_impact_sign));
                header = "How much does " + getString(questionSet[currentIndex].getQuestion()) + " impact climate change?";
                resultsView.setTextSize(14);
                break;
            default:
                    questionSet = recycleQuestions;
        }
        questionTextView.setText(header);

        question.setImageDrawable(getDrawable(questionSet[currentIndex].getImage()));

        resultsView.setVisibility(View.INVISIBLE);

        option1.setOnDragListener(dragListener);
        option2.setOnDragListener(dragListener);
        option3.setOnDragListener(dragListener);

        question.setOnTouchListener(touchListener);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveData();
            startActivity(myIntent);
        }
    };

    /** Makes the item image view into an draggable shadow */

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, myShadowBuilder, v, 0);
                return true;
            }
            return false;
        }
    };

    /** Carries out a set of instructions when the image shadow is dropped inside a container */

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent){

                case DragEvent.ACTION_DROP:
                    //final View view = (View) event.getLocalState();
                    //view.setVisibility(View.INVISIBLE);

                    ImageView dropTarget = (ImageView) v;

                    int answer = questionSet[currentIndex].isAnswer();
                    int tagDropTarget = Integer.parseInt((String)dropTarget.getTag());
                    userAnswers[currentIndex] = tagDropTarget;

                    if (answer == tagDropTarget ){
                        Toast.makeText(BinGame.this,R.string.correctMessage, Toast.LENGTH_SHORT).show();
                        totalCorrect = (totalCorrect +1);
                    }
                    else {
                        Toast.makeText(BinGame.this,R.string.incorrectMessage, Toast.LENGTH_SHORT).show();
                    }
                    if(currentIndex==(questionSet.length-1)){
                        ((ViewGroup) question.getParent()).removeView(question);
                        allOptions.removeAllViews();
                        questionTextView.setText("Your total score was: " + totalCorrect);

                        Button menuButton = new Button(BinGame.this);
                        menuButton.setText("Return to Main Menu");
                        LinearLayout ll = (LinearLayout)findViewById(R.id.optionsLayout);
                        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        ll.addView(menuButton, lp);

                        displayAnswers();

                        menuButton.setOnClickListener(clickListener);
                    }
                    else {
                        currentIndex = (currentIndex + 1);
                        Drawable imageQuestion = getDrawable(questionSet[currentIndex].getImage());
                        question.setImageDrawable(imageQuestion);
                        int question = questionSet[currentIndex].getQuestion();
                        String header = "";
                        switch (levelTheme){
                            case 1 :
                                header = "Which bin does this " + getString(question) + " belong in?";
                                break;
                            case 2 :
                                header = "Which box does this " + getString(question) + " belong in?";
                                break;
                            case 3:
                                header ="How much does " + getString(question) + " impact climate change?";
                        }
                        questionTextView.setText(header);
                    }
                    break;
            }
            return true;
        }
    };


    private void displayAnswers(){
        String results = "";
        for(int i = 0; i < userAnswers.length; i++){
            if(userAnswers[i] == questionSet[i].isAnswer()){
                results += "" + (i+1) + ")" + " Correct! - " + questionSet[i].getAnswer()+"\n";
            }
            else {
                results += "" + (i+1) + ")" + " Incorrect! - " + questionSet[i].getAnswer()+"\n";
            }
        }

        resultsView.setVisibility(View.VISIBLE);
        resultsView.setText(results);
    }

    /** Checks whether user has scored sufficient enough points to move onto the next level */

    private void saveData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(totalCorrect >= 5) {
            switch (levelTheme){
                case 1 :
                    editor.putBoolean(LEVEL_ONE_WORLD_ONE_STATUS, true);
                    break;
                case 2 :
                    editor.putBoolean(LEVEL_ONE_WORLD_TWO_STATUS,true);
                    break;
                case 3:
                    editor.putBoolean(LEVEL_ONE_WORLD_THREE_STATUS,true);
            }

        }
        editor.commit();
    }

    /** Loads up information on which level was selected in the level selection screen */

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        levelTheme = sharedPreferences.getInt("dragDropGameTheme", 0);
    }
}
