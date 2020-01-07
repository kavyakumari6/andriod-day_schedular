package com.example.mydaysheduler;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("ALL")
public class history extends Fragment {
    private SQLiteDatabase mdatabase;
    private HistoryAdapt madapter;

    HistotyDBHelper mhelper;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View myview2= inflater.inflate(R.layout.history,container,false);

        mhelper = new HistotyDBHelper(getContext());
        mdatabase = mhelper.getWritableDatabase();
        recyclerView = myview2.findViewById(R.id.recycletwo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        madapter = new HistoryAdapt(getContext(), getallitem());
        recyclerView.setAdapter(madapter);


        return  myview2;
    }

    private Cursor getallitem(){
        return mdatabase.query(HistoryList.HisEvententr.TABLE_NAME,null,null,null,null,null,null );
    }

}
