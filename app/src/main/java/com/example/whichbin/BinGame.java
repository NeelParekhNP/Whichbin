package com.example.whichbin;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    private TextView questionTextView;
    private LinearLayout allOptions, nameTags;
    private int currentIndex = 0;
    private int totalCorrect = 0;

    private Questions [] mQuestions = new Questions[]{
            new Questions(R.string.question_zero, 1, R.drawable.image_0),
            new Questions(R.string.question_one, 2, R.drawable.image_1),
            new Questions(R.string.question_two, 3, R.drawable.image_2),
            new Questions(R.string.question_three, 2, R.drawable.image_3),
            new Questions(R.string.question_four, 3, R.drawable.image_4),
            new Questions(R.string.question_five, 3, R.drawable.image_5),
            new Questions(R.string.question_six, 1, R.drawable.image_6),
            new Questions(R.string.question_seven, 1, R.drawable.image_7),
            new Questions(R.string.question_eight, 1, R.drawable.image_8),
            new Questions(R.string.question_nine, 1, R.drawable.image_9),
            new Questions(R.string.question_ten, 1, R.drawable.image_10)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_game);

        nameTags = (LinearLayout) findViewById(R.id.optionTagsLayout);
        allOptions = (LinearLayout) findViewById((R.id.optionsLayout));

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        final int Question = mQuestions[currentIndex].getQuestion();
        String header = getString(R.string.questionHeader1) + " " + getString(Question) + " " + getString(R.string.questionHeader2);
        questionTextView.setText(header);


        question = (ImageView) findViewById(R.id.qImageView);
        question.setImageDrawable(getDrawable(mQuestions[currentIndex].getImage()));
        option1 = (ImageView) findViewById(R.id.gWBinImageView);
        option2 = (ImageView) findViewById(R.id.rBImageView);
        option3 = (ImageView) findViewById(R.id.oWImageView);

        option1.setOnDragListener(dragListener);
        option2.setOnDragListener(dragListener);
        option3.setOnDragListener(dragListener);

        question.setOnTouchListener(touchListener);

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getBaseContext(), MainMenu.class);
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

                    int answer = mQuestions[currentIndex].isAnswer();
                    int tagDropTarget = Integer.parseInt((String)dropTarget.getTag());

                    if (answer == tagDropTarget ){
                        Toast.makeText(BinGame.this,R.string.correctMessage, Toast.LENGTH_SHORT).show();
                        totalCorrect = (totalCorrect +1);
                    }
                    else {
                        Toast.makeText(BinGame.this,R.string.incorrectMessage, Toast.LENGTH_SHORT).show();
                    }
                    if(currentIndex==(mQuestions.length-1)){
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
                        Drawable imageQuestion = getDrawable(mQuestions[currentIndex].getImage());
                        question.setImageDrawable(imageQuestion);
                        int question = mQuestions[currentIndex].getQuestion();
                        String header = getString(R.string.questionHeader1) + " " + getString(question) + " " + getString(R.string.questionHeader2);
                        questionTextView.setText(header);
                    }
                    break;
            }
            return true;
        }
    };
}
