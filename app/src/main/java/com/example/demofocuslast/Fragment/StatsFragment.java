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

import androidx.fragment.app.Fragment;

import com.example.demofocuslast.Adapter.CommnuDraw;
import com.example.demofocuslast.Adapter.CommunicationInterface;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

public class StatsFragment extends Fragment implements CommnuDraw {
    ImageView imgMenu;
    private TextView txtCalendar,txtTaskCompleted, txtMinutesSpent;
    CalendarView calendarView;

    String numberTask = "";
    private CommunicationInterface Listener;


    public StatsFragment(CommunicationInterface listener) {
        if (listener != null) {
            Listener = listener;
        }
    }

    public static StatsFragment newInstance(CommunicationInterface listener) {
        return new StatsFragment(listener);
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
        imgMenu = view.findViewById(R.id.imgMenu);
        txtCalendar = view.findViewById(R.id.txtCalendar);
        txtMinutesSpent = view.findViewById(R.id.txtMinutesCompleted);
        txtTaskCompleted = view.findViewById(R.id.txtTaskCompleted);
        calendarView = view.findViewById(R.id.calendar_view);


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
    public void onReceive(Object text) {
        txtTaskCompleted.setText((String) text);
    }
}