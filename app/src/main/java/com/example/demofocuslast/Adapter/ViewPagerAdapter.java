package com.example.demofocuslast.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demofocuslast.Interface.SendImageListenner;
import com.example.demofocuslast.Model.ViewPageModel;
import com.example.demofocuslast.R;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private TextView txtHomePirce;
    private Button btnGet;
    private SendImageListenner sendImageListenner;
    private Context context;
    private List<ViewPageModel> modelList;

    public ViewPagerAdapter(List<ViewPageModel> list, Context context, SendImageListenner sendImageListenner) {
        this.context = context;
        this.sendImageListenner = sendImageListenner;
        this.modelList = list;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.viewpager_content, container, false);
        container.addView(view);

        final ImageView imgHome = view.findViewById(R.id.imgHome);
        TextView homeName = view.findViewById(R.id.nameHome);
        TextView dec = view.findViewById(R.id.decs);
        btnGet = view.findViewById(R.id.btnGet);
        txtHomePirce = view.findViewById(R.id.txtCentHomePrice);

        homeName.setText(modelList.get(position).getHomeName());
        dec.setText(modelList.get(position).getDesc());
        imgHome.setImageResource(modelList.get(position).getImageHome());
        txtHomePirce.setText(String.valueOf(modelList.get(position).getHomePrice()));

        SharedPreferences sharedPreferencesCent = context.getSharedPreferences("centData", Context.MODE_PRIVATE);
        final String cent = sharedPreferencesCent.getString("keyCent", "");

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (modelList.get(position).getHomePrice() <= Integer.parseInt(cent)) {
                    //Save Image
                    SharedPreferences sharedPreferences = context.getSharedPreferences("imageSave", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("keyImg", modelList.get(position).getImageHome());
                    editor.commit();

                    sendImageListenner.sendImage(modelList.get(position).getImageHome());
                    //Save Button
                    btnGet.setBackgroundResource(R.drawable.mbuttoncurrent);
                    btnGet.setText("Current");
                    btnGet.setTextColor(Color.WHITE);
                } else {

                }


//                //Save Button
//                btnGet.setBackgroundResource(R.drawable.mbuttoncurrent);
//                btnGet.setText("Current");
//                btnGet.setTextColor(Color.WHITE);
            }
        });


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
