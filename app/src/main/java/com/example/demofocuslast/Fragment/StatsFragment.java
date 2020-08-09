package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.demofocuslast.Adapter.SendTextListenner;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

public class StatsFragment extends Fragment implements SendTextListenner {
    ImageView imgMenu;
    private TextView txtCalendar,txtTaskCompleted, txtMinutesSpent;
    CalendarView calendarView;
    SendTextListenner sendTextListenner;

    private static StatsFragment instance;
    String texts = " ";
    public StatsFragment() {
    }
    public SendTextListenner getSendTextListenner(){
        return sendTextListenner;
    }


    public static StatsFragment getInstance() {
        if(instance == null){
            instance = new StatsFragment();

        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        //mapping
        addControlers(view);


        //events
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    private void addControlers(View view){
        imgMenu = view.findViewById(R.id.imgMenu);
        txtCalendar = view.findViewById(R.id.txtCalendar);
        txtMinutesSpent = view.findViewById(R.id.txtMinutesCompleted);
        txtTaskCompleted = view.findViewById(R.id.txtTaskCompleted);
        calendarView = view.findViewById(R.id.calendar_view);


    }

    @Override
    public void sendText(String text) {
        txtTaskCompleted.setText(text);
    }
}