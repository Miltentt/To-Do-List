package com.example.xxx.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class NotificationActivity extends BroadcastReceiver {
    private static final String CHANNEL_ID = "Idunno";
    @Override
    public void onReceive(Context context, Intent intent) {
Intent backtomain = new Intent(context,MainActivity.class);
        TaskStackBuilder stack = TaskStackBuilder.create(context);
        stack.addParentStack(MainActivity.class);
        stack.addNextIntent(backtomain);
        Bundle NameData = intent.getExtras();

        PendingIntent pendingIntent = stack.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        Notification notification = builder.setContentTitle("You've got something to do!")
                .setContentText(NameData.getString("name"))
                .setTicker("leeeel")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "NotificationDemo",
                    IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);



    }



}
