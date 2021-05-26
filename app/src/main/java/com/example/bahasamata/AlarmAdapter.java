package com.example.bahasamata;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarmlist> alarmlists;
    private Context aContext;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    public AlarmAdapter(List<Alarmlist> myDataset, Context aContext) {
        this.alarmlists= myDataset;
        this.aContext = aContext;

    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder{
        TimePicker timePicker;
        TextView keterangan,waktualarm;
        int mHour, mMinutes;



        private CardView viewlistalarm;
        public ImageButton ib_settings_alarm,ib_delete_alarm,ib_setTimer;
        public AlarmViewHolder(View view){
            super(view);
            waktualarm = view.findViewById(R.id.tv_waktu_alarm);
            ib_delete_alarm = view.findViewById(R.id.btn_deletealarm);
            ib_settings_alarm = view.findViewById(R.id.btn_settings_alarm);
            ib_setTimer = view.findViewById(R.id.btn_set_alarm);
            viewlistalarm = view.findViewById(R.id.cv_listalarm);
            keterangan= view.findViewById(R.id.tv_ket_alarm);
            timePicker = view.findViewById(R.id.tp_setAlarm);
            keterangan = view.findViewById(R.id.tv_ket_alarm);

            timePicker.setVisibility(View.GONE);
            waktualarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timePicker.setVisibility(View.VISIBLE);
                    waktualarm.setVisibility(View.GONE);
                }
            });
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinutes = minute;
                }
            });
            ib_setTimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alarmManager = (AlarmManager)aContext.getSystemService(Context.ALARM_SERVICE);
                    Date date = new Date();
                    Calendar cal_alarm = Calendar.getInstance();
                    Calendar cal_now = Calendar.getInstance();
                    cal_now.setTime(date);
                    cal_alarm.setTime(date);

                    cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
                    cal_alarm.set(Calendar.MINUTE,mMinutes);
                    cal_alarm.set(Calendar.SECOND,0);

                    if (cal_alarm.before(cal_now)){
                        cal_alarm.add(Calendar.DATE,1);
                    }
                    Intent i = new Intent(aContext,MyBroadcastReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(aContext,mHour+mMinutes,i,0);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);
                    Toast.makeText(aContext,"Alarm berhasil di set",Toast.LENGTH_SHORT).show();
                    waktualarm.setText(mHour + " : " + mMinutes);
                    timePicker.setVisibility(View.GONE);
                    waktualarm.setVisibility(View.VISIBLE);


                }
            });

        }


    }

    @NonNull
    @Override
    public AlarmAdapter.AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(aContext).inflate(R.layout.viewlistalarm, parent, false);
        final AlarmAdapter.AlarmViewHolder vHolder = new AlarmAdapter.AlarmViewHolder(v);


        return vHolder;
    }

    void remove(int position) {
        int temp = position;
        alarmlists.remove(position);
        notifyItemChanged(position);
        notifyItemRangeRemoved(position, 1);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void onBindViewHolder(@NonNull  AlarmAdapter.AlarmViewHolder holder, int position) {
        holder.keterangan.setText(alarmlists.get(position).keterangan);
        holder.ib_delete_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
            }
        });

//        holder.viewlistalarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int temp = position;
//                dialogdetailpasien.show();
//                namapasiendialog.setText(listPasien.get(temp).namapasien);
//                idpasiendialog.setText(String.valueOf(listPasien.get(temp).idpasien));
//                btn_simpan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        listPasien.set(temp,new Pasienlist(namapasiendialog.getText().toString(), nomoracak.nextInt()));
//                        dialogdetailpasien.hide();
//                        notifyDataSetChanged();
//                    }
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return alarmlists.size();
    }
}
