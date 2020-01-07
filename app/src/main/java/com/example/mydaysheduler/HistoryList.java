package com.example.mydaysheduler;

import android.provider.BaseColumns;

public class HistoryList {

    private HistoryList(){

    }


    public static final class HisEvententr implements BaseColumns {

        public static final String TABLE_NAME="eventst";
        public static final String COLOUM_EVENT="name";
        public static final String COLOUM_DATE="date";
        public static final String COLOUM_TIME="time";

    }
}
