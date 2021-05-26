package com.example.bahasamata;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
    static Ringtone r;
    PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        r = RingtoneManager.getRingtone(context,notification);
        Intent i = new Intent(context,AlarmOff.class);

        pendingIntent = PendingIntent.getBroadcast(context,11111,i,0);
        NotificationCompat.Builder notif =  new NotificationCompat.Builder(context,"Alarm")
                .setContentTitle("Alarm is On")
                .setContentText("You had setup the alarm")
                .addAction(R.drawable.ic_baseline_alarm_off_24,"Alarm off", pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_alarm_24);
        NotificationManager manager =  (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("Alarm","oreo",NotificationManager.IMPORTANCE_DEFAULT);
            notif.setChannelId("Alarm");
            manager.createNotificationChannel(notificationChannel);
        }
        Notification notification1 = notif.build();
//        notif.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notification1);


        r.play();

    }
}
