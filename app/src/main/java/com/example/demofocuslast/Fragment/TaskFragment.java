package com.example.demofocuslast.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demofocuslast.Interface.SendTextListenner;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;

import java.util.Locale;

public class TaskFragment extends Fragment {
    TextView txtCoundown;
    TextView txtCentTask;
    ImageView imgUp, imgDown, imgMenu, imgIsland;
    Animation animIsland;
    Button btnStart;

    private SendTextListenner sendTextListenner;
    private CountDownTimer countDownTimer;
    private boolean mTimeRunning = false;
    private long along;
    String timeSet = "";
    int taskComplete = 0;
    int taskMinutesComplete = 0;
    int centTask = 0;
    int image = 0;
    int minutes = 0;
    private SharedPreferences sharedPreferences;

    public TaskFragment() {

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
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        //mapping
        txtCentTask = view.findViewById(R.id.txtCentTask);
        txtCoundown = view.findViewById(R.id.txtCoundown);
        imgDown = view.findViewById(R.id.imgDown);
        imgIsland = view.findViewById(R.id.imgIsland);
        imgUp = view.findViewById(R.id.imgUp);
        imgMenu = view.findViewById(R.id.imgMenu);
        btnStart = view.findViewById(R.id.btnStart);
        timeSet = txtCoundown.getText().toString();

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
//        sharedPreferences = this.getActivity().getSharedPreferences("coundown",Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("key", String.valueOf(txtCoundown.getText()));

        imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textTime = txtCoundown.getText().toString();
                final String[] splitTime = textTime.split(":");
                int time = Integer.parseInt(splitTime[1]);
                int hour = 0;
                if (time <= 55) {
                    time = time + 5;
                    int second = 0;
                    String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, time, second);
//                    editor.putString("key", dis);
                    txtCoundown.setText(dis);
                    timeSet = (String) txtCoundown.getText();
                }
            }
        });
        final String textTime = txtCoundown.getText().toString();
        final String[] splitTime = textTime.split(":");
        imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] splitTime = textTime.split(":");
                int time = Integer.parseInt(splitTime[1]);
                int second = Integer.parseInt(splitTime[2]);
                int hour = 0;
                if (time > 0) {
                    time = time - 5;
                    int seconds = 0;
                    String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, time, second);
                    txtCoundown.setText(dis);
                    timeSet = (String) txtCoundown.getText();
                }
            }
        });

        //countdown
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String [] split = timeSet.split(":");
                minutes = Integer.parseInt(split[1]);
                taskMinutesComplete = taskMinutesComplete + minutes;
                taskComplete = taskComplete + 1;
                centTask = centTask + 1;
                txtCentTask.setText(String.valueOf(centTask));
                sendTextListenner.sendCent(String.valueOf(centTask));
                sendTextListenner.sendText(String.valueOf(taskComplete), String.valueOf(taskMinutesComplete));
                if (mTimeRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        if(image != 0){
            imgIsland.setImageResource(image);
        }
        return view;
    }


    private void startTimer() {
        mTimeRunning = true;
        String textTime = txtCoundown.getText().toString();
        final String[] splitTime = textTime.split(":");
        int h = Integer.parseInt(splitTime[0]);
        final int m = Integer.parseInt(splitTime[1]) * 60000;
        countDownTimer = new CountDownTimer(m, 1000) {
            @Override
            public void onTick(long l) {
                along = l;

                int hours = 0; // I don't know solving problems
                int minutes = (int) ((along / 1000) % 3600) / 60;
                int seconds = (int) ((along / 1000) % 60);

                String dis = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                txtCoundown.setText(dis);
                btnStart.setText("Stop");
                btnStart.setBackgroundResource(R.drawable.mybuttonpause);
            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                btnStart.setText("Start");
                btnStart.setBackgroundResource(R.drawable.mybutton);


            }
        }.start();
        animationImage();

    }

    @Override
    public void onPause() {
        super.onPause();
        taskComplete = 0;
        taskMinutesComplete = 0;
        minutes = 0;
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("controlTime",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key",timeSet);
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        txtCoundown.setText(timeSet);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("controlTime",Context.MODE_PRIVATE);
        timeSet = sharedPreferences.getString("key","");
        if(timeSet != null) {
            txtCoundown.setText(timeSet);
        }
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        mTimeRunning = false;
        btnStart.setText("Start");
        txtCoundown.setText(timeSet);
        btnStart.setBackgroundResource(R.drawable.mybutton);
        imgIsland.setAnimation(null);
    }

    private void animationImage() {
        animIsland = AnimationUtils.loadAnimation(getContext(), R.anim.anim_img_island);
        imgIsland.setAnimation(animIsland);

    }

    public void sendImage(int imgImage) {
        image =imgImage;
        if(imgIsland != null) {
            imgIsland.setImageResource(image);
        }
    }
}