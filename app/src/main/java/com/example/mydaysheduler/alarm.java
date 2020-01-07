package com.example.mydaysheduler;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

@SuppressWarnings("ALL")
public class alarm extends Fragment {
    private SQLiteDatabase mdatabase,mdatabasetwo;
    private  int notificationid=1;
    AlarmManager alarm;
    PendingIntent alarmIntent;
    Calendar startTime;
    int hours;
    int minutes;


    ImageView img_date ,img_time,img_events;
    Button save;
    EditText event_write , date_write,time_write;
    Calendar c;
    DatePickerDialog dp;
    SharedPreferences preferences;

    String updatename, updateDate, updateTime;

    public alarm() { }

    public alarm(String name, String date, String time) {
        Log.d("ALARM", name);
        this.updatename = name;
        this.updateDate = date;
        this.updateTime = time;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myview = inflater.inflate(R.layout.alarm,container,false);
        event_write=myview.findViewById(R.id.editText);
        date_write=myview.findViewById(R.id.Date_select);
        time_write=myview.findViewById(R.id.Time_select);
        img_events= myview.findViewById(R.id.eventss);
        img_date=myview.findViewById(R.id.datess);
        img_time=myview.findViewById(R.id.timeeView);
        save=myview.findViewById(R.id.save);


        preferences = myview.getContext().getSharedPreferences("shareprefkey", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor =preferences.edit();


        HistotyDBHelper hmhelper=new HistotyDBHelper(getContext());
        EventDBHelper mhelper= new EventDBHelper(getContext());
        mdatabasetwo=hmhelper.getWritableDatabase();
        mdatabase = mhelper.getWritableDatabase();

        if (updatename != null) {
//            event_write.getText().clear();
//            time_write.getText().clear();
//            date_write.getText().clear();
            editor.putString("data1s", updateTime);
            editor.putString("data3s", updateDate);
            editor.putString("timesavs", updateTime);
            event_write.setText(updatename);
            date_write.setText(updateDate);
            time_write.setText(updateTime);
        }




        event_write.setText(preferences.getString("data1",""));
        date_write.setText(preferences.getString("data3",""));
        time_write.setText(preferences.getString("timesav",""));



        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dp=new DatePickerDialog(myview.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date_write.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },day,month,year);
                dp.getDatePicker().setMinDate(System.currentTimeMillis()+1000);
                dp.show();


            }
        });







        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evn=event_write.getText().toString().trim();
                String datTim = time_write.getText().toString().trim();
                String datdate = date_write.getText().toString().trim();
                editor.putString("data1",evn);
               // editor.putString("data2",datTim);
                editor.putString("data3",datdate);
                editor.commit();

//                event_write.setText(preferences.getString("data1",""));
//                date_write.setText(preferences.getString("data3",""));
//                time_write.setText(preferences.getString("timesav",""));

                startActivity(new Intent(getContext(),Pop.class));
            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evn=event_write.getText().toString().trim();
                String datTim = time_write.getText().toString().trim();
                String datdate = date_write.getText().toString().trim();
//                editor.putString("data1",evn);
//                editor.putString("data2",datTim);
//                editor.putString("data3",datdate);
//                editor.commit();





                try {
                    if (evn.length()==0||datdate.length()==0||datTim.length()==0){
                        Toast.makeText(getContext(), " Please enter the values correctly", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ContentValues cv = new ContentValues();
                        cv.put(Eventlisting.Evententr.COLOUM_EVENT, evn);
                        cv.put(Eventlisting.Evententr.COLOUM_DATE, datdate);
                        cv.put(Eventlisting.Evententr.COLOUM_TIME, datTim);
                        cv.put(HistoryList.HisEvententr.COLOUM_EVENT, evn);
                        cv.put(HistoryList.HisEvententr.COLOUM_DATE, datdate);
                        cv.put(HistoryList.HisEvententr.COLOUM_TIME, datTim);

                        if (updatename == null) {
                            mdatabase.insert(Eventlisting.Evententr.TABLE_NAME, null, cv);
                            mdatabasetwo.insert(HistoryList.HisEvententr.TABLE_NAME,null,cv);
                            Toast.makeText(getContext(), "Inserted Hist", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String[] args = {evn};
                            mdatabase.update(Eventlisting.Evententr.TABLE_NAME,cv,"name=?",args);
                           event_write.getText().clear();
                           time_write.getText().clear();
                           date_write.getText().clear();

                        }


                        Toast.makeText(getContext(), "inserted succesfully", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "some problem", Toast.LENGTH_SHORT).show();
                    Log.d("ALARM", e.getMessage());
                }

            }
        });


        return  myview;
    }

}
