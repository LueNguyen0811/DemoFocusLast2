package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

import me.itangqi.waveloadingview.WaveLoadingView;

import static java.lang.Integer.parseInt;

public class BadgesFragment extends Fragment {
    WaveLoadingView waveLoadingViewHours, waveLoadingViewTask;
    ImageView imgMenuBadges;
    String totalTaskNumber = null;
    String totalMinutes = null;

    public BadgesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //mapping
        View view = inflater.inflate(R.layout.fragment_badges, container, false);
        waveLoadingViewHours = view.findViewById(R.id.waveLoadingViewHours);
        waveLoadingViewTask = view.findViewById(R.id.waveLoadingViewTask);
        imgMenuBadges = view.findViewById(R.id.imgMenuBadges);
        //events
        imgMenuBadges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        controlsWave();
        return view;
    }

    private void controlsWave() {
        if (totalTaskNumber != null && totalMinutes != null) {
            waveLoadingViewTask.setCenterTitle(totalTaskNumber);
            waveLoadingViewHours.setCenterTitle(totalMinutes);
            if (Integer.parseInt(totalTaskNumber) < 30 && Integer.parseInt(totalMinutes) < 1000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(totalMinutes) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(totalTaskNumber)/5);
            } else if (Integer.parseInt(totalTaskNumber) < 50 && Integer.parseInt(totalMinutes) < 5000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(totalMinutes) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(totalTaskNumber)/5);
            } else if (Integer.parseInt(totalTaskNumber) < 70 && Integer.parseInt(totalMinutes) < 8000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(totalMinutes) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(totalTaskNumber)/5);
            } else if (Integer.parseInt(totalTaskNumber) < 100 && Integer.parseInt(totalMinutes) < 100000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(totalMinutes) / 1000);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(totalTaskNumber)/5);
            }
        }
    }

    public void recieveText(String text1, String text2) {
        totalTaskNumber = text1;
        totalMinutes = text2;
        if (waveLoadingViewTask != null && waveLoadingViewHours != null) {
            waveLoadingViewTask.setCenterTitle(totalTaskNumber);
            waveLoadingViewHours.setCenterTitle(totalMinutes);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("BadgesSave",Context.MODE_PRIVATE);
        String taskSave = sharedPreferences.getString("keyTask","");
        String minutesSave = sharedPreferences.getString("keyMinutes","");
            waveLoadingViewTask.setCenterTitle(taskSave);
            waveLoadingViewHours.setCenterTitle(minutesSave);
        if (taskSave != null && minutesSave != null) {
            waveLoadingViewTask.setCenterTitle(taskSave);
            waveLoadingViewHours.setCenterTitle(minutesSave);
            if (Integer.parseInt(taskSave) < 30 && Integer.parseInt(minutesSave) < 1000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(minutesSave) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(taskSave)/5);
            } else if (Integer.parseInt(taskSave) < 50 && Integer.parseInt(minutesSave) < 5000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(minutesSave) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(taskSave)/5);
            } else if (Integer.parseInt(taskSave) < 70 && Integer.parseInt(minutesSave) < 8000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(minutesSave) / 100);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(taskSave)/5);
            } else if (Integer.parseInt(taskSave) < 100 && Integer.parseInt(minutesSave) < 100000) {
                waveLoadingViewHours.setProgressValue(Integer.parseInt(minutesSave) / 1000);
                waveLoadingViewTask.setProgressValue(Integer.parseInt(taskSave)/5);
            }
        }
    }

}