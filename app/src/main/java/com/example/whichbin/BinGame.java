package com.example.whichbin;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BinGame extends AppCompatActivity {

    private ImageView question, option1, option2, option3;
    private TextView questionTextView, option1Label, option2Label, option3Label;
    private LinearLayout allOptions, nameTags;
    private int currentIndex = 0;
    private int totalCorrect = 0;
    private String header;

    private int levelTheme;
    private BinGameQuestions[] questionSet;

    public static final String LEVEL_ONE_WORLD_ONE_STATUS = "levelOneWorldOneStatus";
    public static final String LEVEL_ONE_WORLD_TWO_STATUS = "levelOneWorldTwoStatus";
    public static final String LEVEL_ONE_WORLD_THREE_STATUS = "levelOneWorldThreeStatus";

    private BinGameQuestions[] recycleQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.question_one, 3, R.drawable.carrot_organic),
            new BinGameQuestions(R.string.question_two, 1, R.drawable.styrofoam_trash),
            new BinGameQuestions(R.string.question_three, 3, R.drawable.strawberry_organic),
            new BinGameQuestions(R.string.question_four, 2, R.drawable.newspaper_recycle),
            new BinGameQuestions(R.string.question_five, 2, R.drawable.can_recycle),
            new BinGameQuestions(R.string.question_six, 1, R.drawable.crisps_packet_trash),
            new BinGameQuestions(R.string.question_seven, 2, R.drawable.bottle_recycle),
            new BinGameQuestions(R.string.question_eight, 1, R.drawable.plate_trash),
            new BinGameQuestions(R.string.question_nine, 2, R.drawable.paper_bag_recycle),
            new BinGameQuestions(R.string.question_ten, 3, R.drawable.apple_oraganic)
    };

    private BinGameQuestions[] conservationStatusQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.animal_question_one,1,R.drawable.leatherback_turtle_vulnerable),
            new BinGameQuestions(R.string.animal_question_two,1,R.drawable.white_rhino_vulnerable),
            new BinGameQuestions(R.string.animal_question_three,3,R.drawable.sumatran_orangutan_ce),
            new BinGameQuestions(R.string.animal_question_four,2,R.drawable.white_tiger_endangered),
            new BinGameQuestions(R.string.animal_question_five,3,R.drawable.amsterdam_albatross_ce),
            new BinGameQuestions(R.string.animal_question_six,2,R.drawable.beluga_whale_endangered),
            new BinGameQuestions(R.string.animal_question_seven,2,R.drawable.asian_elephant_endangered),
            new BinGameQuestions(R.string.animal_question_eight,1,R.drawable.giant_panda_vulnerable),
            new BinGameQuestions(R.string.animal_question_nine,3,R.drawable.nubian_giraffe_ce),
            new BinGameQuestions(R.string.animal_question_ten,3,R.drawable.adriatic_sturgeon_ce)
    };

    private BinGameQuestions[] impactQuestions = new BinGameQuestions[]{
            new BinGameQuestions(R.string.impact_question_one,1,R.drawable.gasoline_high),
            new BinGameQuestions(R.string.impact_question_two,3,R.drawable.vegan_low),
            new BinGameQuestions(R.string.impact_question_three,1,R.drawable.deforestation_high),
            new BinGameQuestions(R.string.impact_question_four,2,R.drawable.light_medium),
            new BinGameQuestions(R.string.impact_question_five,3,R.drawable.bicycle_low),
            new BinGameQuestions(R.string.impact_question_six,1,R.drawable.meat_high),
            new BinGameQuestions(R.string.impact_question_seven,3,R.drawable.solar_power_low),
            new BinGameQuestions(R.string.impact_question_eight,2,R.drawable.vegeterian_medium),
            new BinGameQuestions(R.string.impact_question_nine,2,R.drawable.bus_medium),
            new BinGameQuestions(R.string.impact_question_ten,1,R.drawable.car_high),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_game);

        loadData();

        nameTags = (LinearLayout) findViewById(R.id.optionTagsLayout);
        allOptions = (LinearLayout) findViewById((R.id.optionsLayout));
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        question = (ImageView) findViewById(R.id.qImageView);
        option1 = (ImageView) findViewById(R.id.gWBinImageView);
        option2 = (ImageView) findViewById(R.id.rBImageView);
        option3 = (ImageView) findViewById(R.id.oWImageView);
        option1Label = (TextView) findViewById(R.id.textView_option1_tag);
        option2Label = (TextView) findViewById(R.id.textView_option2_tag);
        option3Label = (TextView) findViewById(R.id.textView_option3_tag);

        switch (levelTheme){
            case 1 :
                questionSet = recycleQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.general_waste));
                option2.setImageDrawable(getDrawable(R.drawable.recycle_bin));
                option3.setImageDrawable(getDrawable(R.drawable.food_waste));
                header = "Which bin does this " + getString(questionSet[currentIndex].getQuestion()) + " belong in?";
                option1Label.setText("General Waste");
                option2Label.setText("Recycle");
                option3Label.setText("Organic Waste");
                break;
            case 2 :
                questionSet = conservationStatusQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.vulnerable_box));
                option2.setImageDrawable(getDrawable(R.drawable.endangered_box));
                option3.setImageDrawable(getDrawable(R.drawable.critically_endangered_box));
                header = "Which box does this " + getString(questionSet[currentIndex].getQuestion()) + " belong in?";
                nameTags.setVisibility(View.GONE);
/*                option1Label.setText("Vulnerable Species");
                option2Label.setText("Endangered Species");
                option3Label.setText("Critically Endangered Species");*/
                break;
            case 3 :
                questionSet = impactQuestions;
                option1.setImageDrawable(getDrawable(R.drawable.high_impact_sign));
                option2.setImageDrawable(getDrawable(R.drawable.medium_impact_sign));
                option3.setImageDrawable(getDrawable(R.drawable.low_impact_sign));
                header = "How much does " + getString(questionSet[currentIndex].getQuestion()) + " impact climate change?";
                nameTags.setVisibility(View.GONE);
                break;
            default:
                    questionSet = recycleQuestions;
        }
        questionTextView.setText(header);

        question.setImageDrawable(getDrawable(questionSet[currentIndex].getImage()));

        option1.setOnDragListener(dragListener);
        option2.setOnDragListener(dragListener);
        option3.setOnDragListener(dragListener);

        question.setOnTouchListener(touchListener);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveData();
            Intent myIntent = new Intent(getBaseContext(), LevelSelection.class);
            startActivity(myIntent);
        }
    };

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
                        nameTags.removeAllViews();
                        questionTextView.setText("Your total score was: " + totalCorrect);

                        Button menuButton = new Button(BinGame.this);
                        menuButton.setText("Return to Main Menu");
                        LinearLayout ll = (LinearLayout)findViewById(R.id.optionsLayout);
                        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        ll.addView(menuButton, lp);

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

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        levelTheme = sharedPreferences.getInt("dragDropGameTheme", 0);
    }
}
