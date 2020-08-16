package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.demofocuslast.Interface.SendTextListenner;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class StatsFragment extends Fragment {
    ImageView imgMenu;
    private TextView txtCalendar, txtTaskCompleted, txtMinutesSpent;
    CalendarView calendarView;
    String taskNumber = "0";
    String taskMinutes = "0";
    String taskSharf;
    String minutesSharf;
    int totalTask = 0;
    int totalMinutes = 0;
    int totalTasks = 0;
    int totalMinutess = 0;
    int getTotalTask = 0;
    int getTotalMinutes = 0;
    ArrayList<Integer> arrayTask = new ArrayList<>();
    ArrayList<Integer> arrayMinutes = new ArrayList<>();

    SendTextListenner sendTextListenner;
    SharedPreferences sharedPreferences;
    String key;

    public StatsFragment() {
    }


    public void setSendTextListenner(SendTextListenner sendTextListenner) {
        this.sendTextListenner = sendTextListenner;
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
        if (taskNumber != null && taskMinutes != null) {

            txtTaskCompleted.setText(taskNumber);
            txtMinutesSpent.setText(taskMinutes);
        }



        getDataCalenda();
        return view;
    }

    private void getDataCalenda() {
        sharedPreferences = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DATE);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.YEAR);

        final SimpleDateFormat dinhDangNgay = new SimpleDateFormat("yyyyMd");
        key = dinhDangNgay.format(calendar.getTime());
        setData();
        loadData();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                key = (i + "" + (i1 + 1) + "" + i2);
                loadData();
            }
        });


//        get all
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String test = String.valueOf(entry.getKey());
            test = String.valueOf(test.length());
            if (Integer.parseInt(test) < 9) {
                arrayTask.add(Integer.parseInt(String.valueOf(entry.getValue())));
            } else {
                arrayMinutes.add(Integer.parseInt(String.valueOf(entry.getValue())));
            }
        }

        for (int i = 0; i < arrayTask.size(); i++) {
            totalTasks = totalTasks + arrayTask.get(i);
        }
        for (int i = 0; i < arrayMinutes.size(); i++) {
            totalMinutess = totalMinutess + arrayMinutes.get(i);
        }
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("BadgesSave",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("keyTask", String.valueOf(totalTasks));
        editor.putString("keyMinutes", String.valueOf(totalMinutess));
        editor.commit();
        sendTextListenner.sendTotal(String.valueOf(totalTasks),String.valueOf(totalMinutess));
        Toast.makeText(getActivity(),totalMinutess + " " + totalTasks,Toast.LENGTH_SHORT).show();
    }

    public void sendText(String text, String text2) {
        taskNumber = text;
        taskMinutes = text2;
        if (txtTaskCompleted != null && taskMinutes != null) {
            txtTaskCompleted.setText(taskNumber);
            txtMinutesSpent.setText(taskMinutes);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        taskMinutes = "0";
        taskNumber ="0";
    }

    @Override
    public void onPause() {
        super.onPause();
        arrayTask.clear();
        arrayMinutes.clear();
        totalMinutess = 0;
        totalTasks = 0;

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void addControlers(View view) {
        imgMenu = view.findViewById(R.id.imgMenu);
        txtCalendar = view.findViewById(R.id.txtCalendar);
        txtMinutesSpent = view.findViewById(R.id.txtMinutesCompleted);
        txtTaskCompleted = view.findViewById(R.id.txtTaskCompleted);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarView.setDate(System.currentTimeMillis(), false, true);

    }

    private void setData() {
        if (sharedPreferences.getString(key, "") != null) {
            taskSharf = sharedPreferences.getString(key, "0");
            minutesSharf = sharedPreferences.getString(key + "minutes", "0");
            totalTask = Integer.parseInt(taskNumber) + Integer.parseInt(taskSharf);
            totalMinutes = Integer.parseInt(taskMinutes) + Integer.parseInt(minutesSharf);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, String.valueOf(totalTask));
            editor.putString(key + "minutes", String.valueOf(totalMinutes));
            editor.commit();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, taskNumber);
            editor.putString(key + "minutes", taskMinutes);
            editor.commit();
        }
    }

    private void loadData() {
        txtTaskCompleted.setText(sharedPreferences.getString(key, "0"));
        txtMinutesSpent.setText(sharedPreferences.getString(key + "minutes", "0"));
    }


}