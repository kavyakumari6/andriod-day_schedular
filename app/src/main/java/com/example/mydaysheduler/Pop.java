package com.example.mydaysheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Pop extends AppCompatActivity  implements View.OnClickListener{
    private  int notificationid=1;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        findViewById(R.id.setbtr).setOnClickListener(this);
        findViewById(R.id.canclebtr).setOnClickListener(this);

        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int hight=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(hight*.8));

        WindowManager.LayoutParams params =getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=20;
        getWindow().setAttributes(params);

        preferences = getSharedPreferences("shareprefkey", Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    @Override
    public void onClick(View v) {

        TimePicker timePicker = findViewById(R.id.timepicker);
        Intent intent = new Intent(Pop.this,AlarmRec.class);
        intent.putExtra("notificationId",notificationid);
        intent.putExtra("todo",preferences.getString("data1",""));
        PendingIntent alarmIntent = PendingIntent.getBroadcast(Pop.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        switch(v.getId()){
            case R.id.setbtr: {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String timesave=hour+":"+minute;
                editor.putString("timesav",timesave);
                editor.commit();
                Calendar startTime= Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);
                Toast.makeText(this, "done!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Pop.this,MainActivity.class).putExtra("frag","myfrag"));
                break;
            }


            case R.id.canclebtr:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "cancled", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
