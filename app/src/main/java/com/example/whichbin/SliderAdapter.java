package com.example.whichbin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int[] slideImages = {
            R.drawable.icon_one,
            R.drawable.icon_two,
            R.drawable.icon_three
    };

    public String[] slideHeadings = {
            "Step 1",
            "Step 2",
            "Step 3"
    };
    public String[] slideInformations = {
            "Press the arrow buttons on the game screen to navigate through the different worlds.",
            "Get 5 or more points in each level to unlock the next level.",
            "Complete all levels to get rid of Dodogho the annoying Dodo bird and save the planet!"
    };

    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_imageView);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading_textView);
        TextView slideInformation = (TextView) view.findViewById(R.id.slide_information_textView);

        slideImageView.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);
        slideInformation.setText(slideInformations[position]);

        Typeface face = ResourcesCompat.getFont(context, R.font.bubblegum_sans);
        slideHeading.setTypeface(face);
        slideInformation.setTypeface(face);

        slideHeading.setTextColor(Color.WHITE);
        slideInformation.setTextColor(Color.WHITE);

        slideHeading.setTextSize(40);
        slideInformation.setTextSize(20);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);

    }
}
