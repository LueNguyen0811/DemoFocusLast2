package com.example.demofocuslast.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofocuslast.Adapter.RecycleViewSettingAdapter;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.Model.SettingModel;
import com.example.demofocuslast.R;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {
    private ImageView imgMenu;
    private ArrayList<SettingModel> settingModelArrayList;
    private RecyclerView recyclerView;
    private RecycleViewSettingAdapter recycleViewSettingAdapter;


    public SettingsFragment() {
        // Required empty public constructor
    }


    public static SettingsFragment newInstance() {
      return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        imgMenu = view.findViewById(R.id.imgMenuSetting);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
        settingModelArrayList = new ArrayList<>();
        createContentList();
        recycleViewSettingAdapter = new RecycleViewSettingAdapter(getContext().getApplicationContext(),settingModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewSettingAdapter);

        return view;
    }

    private void createContentList() {
        settingModelArrayList.add(new SettingModel("Focus Mode","Task will be interrupted immediately if switch to another app or return to home screen"));
        settingModelArrayList.add(new SettingModel("Rate Us", "Please give us 5 stars if you like our app"));
        settingModelArrayList.add(new SettingModel("Share", "Share with your friends and love ones"));
        settingModelArrayList.add(new SettingModel("Language", ""));
        settingModelArrayList.add(new SettingModel("About",""));


    }
}