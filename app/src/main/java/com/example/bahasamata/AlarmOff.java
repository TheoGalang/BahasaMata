package com.example.bahasamata;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AlarmOff extends BroadcastReceiver {

    Uri alarmoff;

    @Override
    public void onReceive(Context context, Intent intent) {
        MyBroadcastReceiver.r.stop();
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,24444,i,0);
        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
        alarmoff = intent.getParcelableExtra("alarm off");

    }
}