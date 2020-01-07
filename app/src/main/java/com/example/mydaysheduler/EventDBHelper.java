package com.example.mydaysheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;
import com.example.mydaysheduler.Eventlisting.*;

import androidx.annotation.Nullable;

@SuppressWarnings("ALL")
public class EventDBHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="daysD.db";
    public static final int DATABASE_VERSION=1;
    public EventDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // final String SQL_CREATE_EventList_TABLE = "CREATE TABLE " + Evententr.TABLE_NAME + "(" + Evententr.COLOUM_EVENT + "TEXT NOT NULL, "+Evententr.COLOUM_DATE+"TEXT NOT NULL, "+Evententr.COLOUM_TIME+"TEXT NOT NULL"+");";
        final String SQL_CREATE_EVENT_TABLE="CREATE TABLE IF NOT EXISTS " +Evententr.TABLE_NAME+"(name TEXT NOT NULL, date TEXT NOT NULL , time TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_EVENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Evententr.TABLE_NAME);
    }
}
