package com.example.demofocuslast.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofocuslast.adapter.RecycleViewSettingAdapter;
import com.example.demofocuslast.adapter.RecyclerItemClickListener;
import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.model.SettingModel;
import com.example.demofocuslast.R;
import com.example.demofocuslast.service.Service;

import java.util.ArrayList;

import static com.example.demofocuslast.service.App.CHANNEL_ID;

public class SettingsFragment extends Fragment {
    private ImageView imgMenu;
    private TextView txtContent,txtDescription;
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
        txtDescription = view.findViewById(R.id.txtDecs);
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

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                txtContent = view.findViewById(R.id.txtContent);
                txtContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (position) {
                            case 0:
                                dialogFocusMode();
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            default:

                        }

                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(recycleViewSettingAdapter);
        return view;
    }

    private void dialogFocusMode() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_design);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txtImmediately = dialog.findViewById(R.id.txtImmediately);
        TextView txtAfter30s = dialog.findViewById(R.id.txtAfter30s);
        TextView txtNever = dialog.findViewById(R.id.never);
        txtImmediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setImmediatelyMode();
               dialog.dismiss();
            }
        });
        txtAfter30s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAfter30sMode();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setAfter30sMode() {

        Intent serviceIntent = new Intent(getContext(), Service.class);
        serviceIntent.setAction(Service.ACTION_STOP_FOREGROUND_SERVICE);

//        ContextCompat.startForegroundService(getContext(),serviceIntent);
    }


    private void setImmediatelyMode() {
        Intent notificationIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), (int) System.currentTimeMillis(),notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setContentTitle("example service")
                .setContentText("hello")
                .setSmallIcon(R.drawable.ic_plants)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(1,notification);
    }
    private void createContentList() {
        settingModelArrayList.add(new SettingModel("Focus Mode","Task will be interrupted immediately if switch to another app or return to home screen"));
        settingModelArrayList.add(new SettingModel("Rate Us", "Please give us 5 stars if you like our app"));
        settingModelArrayList.add(new SettingModel("Share", "Share with your friends and love ones"));
        settingModelArrayList.add(new SettingModel("Language", ""));
        settingModelArrayList.add(new SettingModel("About",""));
    }

}