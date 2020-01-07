package com.example.mydaysheduler;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EventsAdap extends RecyclerView.Adapter<EventsAdap.EventViewHolder>{
    private Context  mContext;
    private Cursor mCursor;
    public EventsAdap(Context context, Cursor cursor){
        mContext=context;
        mCursor=cursor;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{
     public TextView  date ,time;
     public CheckBox ename;
     LinearLayout cardListLL;



        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            cardListLL = itemView.findViewById(R.id.cardListLL1);
            ename = itemView.findViewById(R.id.checkBox2);
            date = itemView.findViewById(R.id.textView4);
            time = itemView.findViewById(R.id.textView5);

        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.mycard,parent,false);
           return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        final String name = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_EVENT));
        final String date = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_DATE));
        final String time = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_TIME));
         holder.ename.setText(name);
         holder.date.setText(date);
         holder.time.setText(time);
         holder.itemView.setTag(name);


         holder.cardListLL.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                         .replace(R.id.fragment_container, new alarm(name, date, time)).commit();

             }
         });

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor!=null){
         mCursor.close();
        }
        mCursor= newCursor;
        if (newCursor !=null){
            notifyDataSetChanged();

        }
    }
}
