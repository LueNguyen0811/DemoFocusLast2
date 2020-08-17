package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demofocuslast.Interface.SendImageListenner;
import com.example.demofocuslast.Model.ViewPageModel;
import com.example.demofocuslast.R;

public class PlantsFragment extends Fragment {

    private TextView txtHomePirce;
    private Button btnGet;
    private ViewPageModel viewPageModel;
    private SendImageListenner sendImageListenner;
    private HomeFragment.UpdateUi updateUi;
    private int id;

    PlantsFragment(int id, ViewPageModel viewPageModel, SendImageListenner sendImageListenner, HomeFragment.UpdateUi updateUi) {
        this.viewPageModel = viewPageModel;
        this.sendImageListenner = sendImageListenner;
        this.updateUi = updateUi;
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_content, container, false);
        final ImageView imgHome = view.findViewById(R.id.imgHome);
        TextView homeName = view.findViewById(R.id.nameHome);
        TextView dec = view.findViewById(R.id.decs);
        btnGet = view.findViewById(R.id.btnGet);
        txtHomePirce = view.findViewById(R.id.txtCentHomePrice);

        homeName.setText(viewPageModel.getHomeName());
        dec.setText(viewPageModel.getDesc());
        imgHome.setImageResource(viewPageModel.getImageHome());
        txtHomePirce.setText(String.valueOf(viewPageModel.getHomePrice()));

        SharedPreferences sharedPreferencesCent = getContext().getSharedPreferences("centData", Context.MODE_PRIVATE);
        final String cent = sharedPreferencesCent.getString("keyCent", "200");

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("imageSave", Context.MODE_PRIVATE);
        if (sharedPreferences.getInt("keyImg", R.drawable.island) == viewPageModel.getImageHome()) {
            btnGet.setBackgroundResource(R.drawable.mbuttoncurrent);
            btnGet.setText("Current");
            btnGet.setTextColor(Color.WHITE);
        }
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPageModel.getHomePrice() <= Integer.parseInt(cent)) {
                    //Save Image
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("imageSave", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("keyImg", viewPageModel.getImageHome());
//                    editor.commit();
                    editor.apply();

                    sendImageListenner.sendImage(viewPageModel.getImageHome());
                    //Save Button
                    btnGet.setBackgroundResource(R.drawable.mbuttoncurrent);
                    btnGet.setText("Current");
                    btnGet.setTextColor(Color.WHITE);

                    updateUi.updateUi(id);
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

    public void updateUi() {
        btnGet.setBackgroundResource(R.drawable.button_selector);
        btnGet.setText("Select");
        btnGet.setTextColor(Color.parseColor("#E76F52"));
    }
}