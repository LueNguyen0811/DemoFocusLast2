package com.example.demofocuslast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demofocuslast.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.fist_slide,
            R.drawable.second_slider,
            R.drawable.second,
            R.drawable.four_slide
    };
    int heading[] = {
            R.string.fist_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title
    };
    int descriptron[] = {
            R.string.fist_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc
    };

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slides_layout,container,false);

        ImageView imgSloder = view.findViewById(R.id.imgSlider);
        TextView txtHead = view.findViewById(R.id.txtSliderHeading);
        TextView txtDecs = view.findViewById(R.id.txtDescription);

        imgSloder.setImageResource(images[position]);
        txtHead.setText(heading[position]);
        txtDecs.setText(descriptron[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
