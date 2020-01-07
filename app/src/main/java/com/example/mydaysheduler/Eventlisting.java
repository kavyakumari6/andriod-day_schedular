package com.example.mydaysheduler;

import android.provider.BaseColumns;

public class Eventlisting {
    private Eventlisting(){}

    public static final class Evententr implements BaseColumns{

        public static final String TABLE_NAME="eventst";
        public static final String COLOUM_EVENT="name";
        public static final String COLOUM_DATE="date";
        public static final String COLOUM_TIME="time";

    }
}
