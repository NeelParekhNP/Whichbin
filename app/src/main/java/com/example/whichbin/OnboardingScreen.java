package com.example.whichbin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingScreen extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout linearLayout;
    private Button nextButton, backButton, finishButton;

    private TextView[] dots;

    private SliderAdapter sliderAdapter;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        linearLayout = (LinearLayout) findViewById(R.id.onBoardLinearLayout);
        nextButton = (Button) findViewById(R.id.button_slide_next);
        backButton = (Button) findViewById(R.id.button_slide_back);
        finishButton = (Button) findViewById(R.id.button_slide_finish);

        sliderAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        //OnClickListeners

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage+1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage-1);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readMore = new Intent(view.getContext(), LevelSelectionWorldOne.class);
                view.getContext().startActivity(readMore);
            }
        });
    }

    public void addDotsIndicator(int position){

        dots = new TextView[3];
        linearLayout.removeAllViews();

        for (int i = 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.WHITE);

            linearLayout.addView(dots[i]);
        }

        if (dots.length>0){
            dots[position].setTextColor(Color.BLACK);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if (i == 0){
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                finishButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);
                finishButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);

                nextButton.setText("Next");
                backButton.setText("");
            }

            else if (i==dots.length-1){
                nextButton.setEnabled(false);
                backButton.setEnabled(true);
                finishButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                finishButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.INVISIBLE);

                backButton.setText("Back");
            }

            else {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                finishButton.setEnabled(false);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                finishButton.setVisibility(View.INVISIBLE);

                nextButton.setText("Next");
                backButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
