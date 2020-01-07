package com.example.mydaysheduler;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapt extends RecyclerView.Adapter<HistoryAdapt.HistViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    public HistoryAdapt(Context context, Cursor cursor){
        mContext=context;
        mCursor=cursor;
    }
    public class HistViewHolder extends RecyclerView.ViewHolder{
        public TextView date,time1,ename;
        LinearLayout carhll;
        public HistViewHolder(@NonNull View itemView) {
            super(itemView);
            carhll = itemView.findViewById(R.id.cardListLL1);
            ename = itemView.findViewById(R.id.textViewHis);
            date = itemView.findViewById(R.id.textViewone);
            time1 = itemView.findViewById(R.id.textViewtwo);
        }
    }

    @NonNull
    @Override
    public HistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.mycard2,parent,false);
        return new HistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        final String name = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_EVENT));
        final String date = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_DATE));
        final String time = mCursor.getString(mCursor.getColumnIndex(Eventlisting.Evententr.COLOUM_TIME));
        holder.ename.setText(name);
        holder.date.setText(date);
        holder.time1.setText(time);
       // holder.itemView.setTag(name);

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
