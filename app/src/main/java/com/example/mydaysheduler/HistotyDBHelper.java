package com.example.mydaysheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.mydaysheduler.HistoryList.*;

@SuppressWarnings("ALL")
public class HistotyDBHelper extends SQLiteOpenHelper {
    public  static final String DATABASE_NAME="daysDSEC.db";
    public static final int DATABASE_VERSION=1;

    public HistotyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_His_EVENT_TABLE="CREATE TABLE IF NOT EXISTS " +HisEvententr.TABLE_NAME+"(name TEXT NOT NULL, date TEXT NOT NULL , time TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_His_EVENT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+HisEvententr.TABLE_NAME);
    }
}
