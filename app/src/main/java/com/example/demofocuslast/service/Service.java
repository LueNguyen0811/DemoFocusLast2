package com.example.demofocuslast.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.demofocuslast.MainActivity;
import com.example.demofocuslast.R;
import com.example.demofocuslast.fragment.TaskFragment;

import static com.example.demofocuslast.service.App.CHANNEL_ID;

public class Service extends android.app.Service {
    public static String MY_TAG = "MyTAG";
    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";

    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";

    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
//
//        Intent intentHide = new Intent(this, StopServiceReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intentHide, PendingIntent.FLAG_CANCEL_CURRENT);
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("example service")
//                .setContentText(input)
//                .setSmallIcon(R.drawable.logo)
//                .setContentIntent(pendingIntent).build();
        showNotification();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(MY_TAG, "run: starting download ");
//
//                int i = 0;
//                while (i <= 30) {
//                    Log.d(MY_TAG, "run: Prigress is: " + (i + 1));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    i++;
//                }
//                Log.d(MY_TAG, "run: download completed ");
//
//                stopForeground(true);
//                stopSelf();
//            }
//        });
//        thread.start();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("example service")
                .setContentText("hello")
                .setSmallIcon(R.drawable.ic_plants)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        if (intent != null){
            String action = intent.getAction();
            switch (action){
                case ACTION_START_FOREGROUND_SERVICE:
                    startForeground(1,notification);
                    break;
                case ACTION_STOP_FOREGROUND_SERVICE:
                    stopForeground(true);
                    stopSelf();
                    break;
            }
        }
        return START_STICKY;
    }

    private void showNotification() {



//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle("example service")
//                .setContentText("hello")
//                .setSmallIcon(R.drawable.ic_plants)
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .build();
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(0,notification);
//        startForeground(123,notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MY_TAG, "destroy ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
